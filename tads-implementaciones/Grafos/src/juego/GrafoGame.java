package juego;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║  GRAFO GAME — AyED 2 UADE                                      ║
 * ║  Juego competitivo multijugador de grafos con BFS/DFS          ║
 * ║  Arquitectura idéntica al Prode: HTTP server + HTML embebido   ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * CONCEPTOS PEDAGÓGICOS que cubre este juego:
 *  - Representación de grafos con MATRIZ DE ADYACENCIA (int[][])
 *  - BFS (Búsqueda en Anchura) — camino más corto
 *  - DFS (Búsqueda en Profundidad) — exploración exhaustiva
 *  - Comparación de algoritmos en tiempo real
 *  - Grafos ponderados y no ponderados
 *
 * MECÁNICA: El profe crea un grafo. Los alumnos eligen el camino
 * que creen que BFS/DFS recorrería. Gana quien acierta más rápido.
 * Ranking en vivo visible para todos (como el Prode).
 */
public class GrafoGame {

    // ╔══════════════════════════════════════════════════════════╗
    // ║  GRAFO CON MATRIZ DE ADYACENCIA                         ║
    // ╚══════════════════════════════════════════════════════════╝
    static class Grafo {
        int n;          // número de vértices
        int[][] mat;    // matriz de adyacencia (0 = sin arista, >0 = peso)
        String[] nombres; // etiquetas de nodos (ciudades del Mundial)

        Grafo(int n, String[] nombres) {
            this.n = n;
            this.mat = new int[n][n];
            this.nombres = nombres;
        }

        void agregarArista(int u, int v, int peso) {
            mat[u][v] = peso;
            mat[v][u] = peso; // no dirigido
        }

        void quitarArista(int u, int v) {
            mat[u][v] = 0;
            mat[v][u] = 0;
        }

        // BFS desde origen — devuelve orden de visita
        List<Integer> bfs(int origen) {
            List<Integer> orden = new ArrayList<>();
            boolean[] vis = new boolean[n];
            Queue<Integer> cola = new LinkedList<>();
            cola.add(origen);
            vis[origen] = true;
            while (!cola.isEmpty()) {
                int u = cola.poll();
                orden.add(u);
                for (int v = 0; v < n; v++) {
                    if (mat[u][v] != 0 && !vis[v]) {
                        vis[v] = true;
                        cola.add(v);
                    }
                }
            }
            return orden;
        }

        // BFS camino más corto entre origen y destino
        List<Integer> bfsCamino(int origen, int destino) {
            int[] padre = new int[n];
            Arrays.fill(padre, -1);
            boolean[] vis = new boolean[n];
            Queue<Integer> cola = new LinkedList<>();
            cola.add(origen);
            vis[origen] = true;
            while (!cola.isEmpty()) {
                int u = cola.poll();
                if (u == destino) break;
                for (int v = 0; v < n; v++) {
                    if (mat[u][v] != 0 && !vis[v]) {
                        vis[v] = true;
                        padre[v] = u;
                        cola.add(v);
                    }
                }
            }
            // reconstruir camino
            List<Integer> camino = new ArrayList<>();
            if (padre[destino] == -1 && destino != origen) return camino;
            for (int x = destino; x != -1; x = padre[x]) camino.add(0, x);
            return camino;
        }

        // DFS desde origen — devuelve orden de visita
        List<Integer> dfs(int origen) {
            List<Integer> orden = new ArrayList<>();
            boolean[] vis = new boolean[n];
            dfsRec(origen, vis, orden);
            return orden;
        }
        private void dfsRec(int u, boolean[] vis, List<Integer> orden) {
            vis[u] = true;
            orden.add(u);
            for (int v = 0; v < n; v++) {
                if (mat[u][v] != 0 && !vis[v]) dfsRec(v, vis, orden);
            }
        }

        // Serializa la matriz a JSON para el frontend
        String matrizToJson() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(",");
                sb.append("[");
                for (int j = 0; j < n; j++) {
                    if (j > 0) sb.append(",");
                    sb.append(mat[i][j]);
                }
                sb.append("]");
            }
            return sb.append("]").toString();
        }

        String nombresJson() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(",");
                sb.append("\"").append(esc(nombres[i])).append("\"");
            }
            return sb.append("]").toString();
        }

        String listaToJson(List<Integer> lista) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < lista.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(lista.get(i));
            }
            return sb.append("]").toString();
        }
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  DOMINIO DEL JUEGO                                      ║
    // ╚══════════════════════════════════════════════════════════╝
    static class Jugador {
        String nombre;
        int puntaje = 0;
        int racha = 0;       // respuestas correctas consecutivas
        int tiempoTotal = 0; // ms acumulados respondiendo
        Map<Integer, RespuestaJugador> respuestas = new HashMap<>();

        Jugador(String n) { nombre = n; }
    }

    static class RespuestaJugador {
        int preguntaId;
        List<Integer> camino; // camino elegido por el jugador
        long tiempoMs;        // tiempo en responder
        boolean correcta;
        int puntosObtenidos;
        RespuestaJugador(int pid, List<Integer> c, long t) {
            preguntaId = pid; camino = c; tiempoMs = t;
        }
    }

    static class Pregunta {
        int id;
        String tipo;          // "BFS_ORDEN", "BFS_CAMINO", "DFS_ORDEN", "MATRIZ_QUERY"
        String enunciado;
        int origen, destino;  // nodos relevantes
        List<Integer> respuestaCorrecta;
        boolean activa = false;
        long iniciadaEn = 0;
        int tiempoLimite = 45; // segundos

        Pregunta(int id, String tipo, String enunciado, int origen, int destino, List<Integer> resp) {
            this.id = id; this.tipo = tipo; this.enunciado = enunciado;
            this.origen = origen; this.destino = destino; this.respuestaCorrecta = resp;
        }
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  ESTADO GLOBAL                                          ║
    // ╚══════════════════════════════════════════════════════════╝
    // Grafo del Mundial 2026: ciudades sede como nodos
    static final String[] CIUDADES = {
        "NYC","LA","Chicago","Dallas","Houston",
        "Miami","Seattle","Boston","SF","Atlanta",
        "CDMX","Guadalajara","Monterrey","Toronto","Vancouver"
    };

    static Grafo grafo = crearGrafoInicial();
    static final Map<String, Jugador> jugadores = new LinkedHashMap<>();
    static final List<Pregunta> preguntas = new ArrayList<>();
    static int preguntaActualId = -1;
    static int nextPregId = 1;
    static final String SAVE = "grafogame_data.txt";
    static final String PASS = "profe2026";

    static Grafo crearGrafoInicial() {
        Grafo g = new Grafo(15, CIUDADES);
        // Conexiones geográficas/logísticas entre sedes
        g.agregarArista(0, 1, 4); // NYC - LA
        g.agregarArista(0, 2, 2); // NYC - Chicago
        g.agregarArista(0, 4, 3); // NYC - Houston (via Delta)
        g.agregarArista(1, 8, 1); // LA - SF
        g.agregarArista(1, 9, 5); // LA - Atlanta
        g.agregarArista(2, 3, 2); // Chicago - Dallas
        g.agregarArista(2, 6, 3); // Chicago - Seattle
        g.agregarArista(3, 4, 1); // Dallas - Houston
        g.agregarArista(3, 10,3); // Dallas - CDMX
        g.agregarArista(4, 5, 3); // Houston - Miami
        g.agregarArista(5, 9, 2); // Miami - Atlanta
        g.agregarArista(6, 14,4); // Seattle - Vancouver
        g.agregarArista(7, 0, 1); // Boston - NYC
        g.agregarArista(7, 13,5); // Boston - Toronto
        g.agregarArista(8, 6, 2); // SF - Seattle
        g.agregarArista(9, 5, 2); // Atlanta - Miami
        g.agregarArista(10,11,1); // CDMX - Guadalajara
        g.agregarArista(10,12,2); // CDMX - Monterrey
        g.agregarArista(11,12,2); // Guadalajara - Monterrey
        g.agregarArista(12,3, 3); // Monterrey - Dallas
        g.agregarArista(13,2, 2); // Toronto - Chicago
        g.agregarArista(13,14,2); // Toronto - Vancouver
        // (Seattle-Vancouver ya fue agregada arriba con peso 4)
        return g;
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  MAIN                                                   ║
    // ╚══════════════════════════════════════════════════════════╝
    public static void main(String[] args) throws IOException {
        cargar();
        HTML = cargarHtml();
        // Precalcular preguntas base
        if (preguntas.isEmpty()) generarPreguntas();

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8081"));
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.setExecutor(Executors.newFixedThreadPool(4));

        server.createContext("/",                   ex -> wrap(ex, "GET",  GrafoGame::root));
        server.createContext("/api/state",          ex -> wrap(ex, "GET",  GrafoGame::apiState));
        server.createContext("/api/unirse",         ex -> wrap(ex, "POST", GrafoGame::apiUnirse));
        server.createContext("/api/responder",      ex -> wrap(ex, "POST", GrafoGame::apiResponder));
        server.createContext("/api/activar",        ex -> wrap(ex, "POST", GrafoGame::apiActivar));
        server.createContext("/api/nueva_pregunta", ex -> wrap(ex, "POST", GrafoGame::apiNuevaPregunta));
        server.createContext("/api/editar_arista",  ex -> wrap(ex, "POST", GrafoGame::apiEditarArista));
        server.createContext("/api/reiniciar",      ex -> wrap(ex, "POST", GrafoGame::apiReiniciar));
        server.createContext("/api/resolver",       ex -> wrap(ex, "GET",  GrafoGame::apiResolver));

        server.start();
        System.out.println("🎮 GrafoGame iniciado en puerto " + port);
        System.out.println("   Abrí http://localhost:" + port + " en el navegador");
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  HANDLERS HTTP                                          ║
    // ╚══════════════════════════════════════════════════════════╝
    @FunctionalInterface interface H { void run(HttpExchange ex) throws IOException; }

    static void wrap(HttpExchange ex, String method, H h) throws IOException {
        ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equals(ex.getRequestMethod())) { respond(ex, 200, ""); return; }
        if (!method.equals(ex.getRequestMethod())) { respond(ex, 405, "{\"error\":\"Method not allowed\"}"); return; }
        try { h.run(ex); }
        catch (Exception e) {
            e.printStackTrace();
            try { respond(ex, 500, "{\"error\":\"" + esc(e.getMessage()) + "\"}"); } catch (Exception ignored) {}
        }
    }

    static void root(HttpExchange ex) throws IOException { respondHtml(ex, HTML); }

    static synchronized void apiState(HttpExchange ex) throws IOException {
        StringBuilder sb = new StringBuilder("{");
        // Grafo
        sb.append("\"nodos\":").append(grafo.nombresJson());
        sb.append(",\"matriz\":").append(grafo.matrizToJson());
        // Pregunta actual
        Pregunta pActual = preguntas.stream().filter(p -> p.id == preguntaActualId).findFirst().orElse(null);
        if (pActual != null) {
            sb.append(",\"pregunta\":{");
            sb.append("\"id\":").append(pActual.id);
            sb.append(",\"tipo\":\"").append(pActual.tipo).append("\"");
            sb.append(",\"enunciado\":\"").append(esc(pActual.enunciado)).append("\"");
            sb.append(",\"origen\":").append(pActual.origen);
            sb.append(",\"destino\":").append(pActual.destino);
            sb.append(",\"activa\":").append(pActual.activa);
            sb.append(",\"tiempoLimite\":").append(pActual.tiempoLimite);
            long transcurrido = pActual.activa ? (System.currentTimeMillis() - pActual.iniciadaEn) / 1000 : 0;
            sb.append(",\"transcurrido\":").append(transcurrido);
            sb.append("}");
        } else {
            sb.append(",\"pregunta\":null");
        }
        // Ranking
        sb.append(",\"ranking\":[");
        List<Jugador> ranked = new ArrayList<>(jugadores.values());
        ranked.sort((a, b) -> b.puntaje != a.puntaje ? b.puntaje - a.puntaje : a.tiempoTotal - b.tiempoTotal);
        for (int i = 0; i < ranked.size(); i++) {
            if (i > 0) sb.append(",");
            Jugador j = ranked.get(i);
            sb.append("{\"nombre\":\"").append(esc(j.nombre)).append("\"");
            sb.append(",\"puntaje\":").append(j.puntaje);
            sb.append(",\"racha\":").append(j.racha);
            // estado en pregunta actual
            if (pActual != null && j.respuestas.containsKey(pActual.id)) {
                RespuestaJugador r = j.respuestas.get(pActual.id);
                sb.append(",\"respondio\":true");
                sb.append(",\"correcto\":").append(r.correcta);
                sb.append(",\"puntos\":").append(r.puntosObtenidos);
            } else {
                sb.append(",\"respondio\":false");
            }
            sb.append("}");
        }
        sb.append("],\"preguntas\":[");
        for (int i = 0; i < preguntas.size(); i++) {
            if (i > 0) sb.append(",");
            Pregunta pr = preguntas.get(i);
            sb.append("{\"id\":").append(pr.id);
            sb.append(",\"tipo\":\"").append(pr.tipo).append("\"");
            sb.append(",\"origen\":").append(pr.origen);
            sb.append(",\"destino\":").append(pr.destino);
            sb.append(",\"activa\":").append(pr.activa);
            sb.append(",\"etiqueta\":\"").append(esc(etiquetaPregunta(pr))).append("\"");
            sb.append("}");
        }
        sb.append("],\"totalPreguntas\":").append(preguntas.size()).append("}");
        respondJson(ex, sb.toString());
    }

    static String etiquetaPregunta(Pregunta p) {
        switch (p.tipo) {
            case "BFS_CAMINO":
                return "#" + p.id + " · BFS camino: " + CIUDADES[p.origen] + " → " + CIUDADES[p.destino];
            case "BFS_ORDEN":
                return "#" + p.id + " · BFS orden desde " + CIUDADES[p.origen];
            case "DFS_ORDEN":
                return "#" + p.id + " · DFS orden desde " + CIUDADES[p.origen];
            case "MATRIZ_QUERY":
                return "#" + p.id + " · Matriz: " + CIUDADES[p.origen] + " ↔ " + CIUDADES[p.destino];
            default:
                return "#" + p.id + " · " + p.tipo;
        }
    }

    static synchronized void apiUnirse(HttpExchange ex) throws IOException {
        Map<String, String> b = parseJson(body(ex));
        String nombre = b.get("nombre");
        if (nombre == null || nombre.isBlank()) { respondJson(ex, "{\"error\":\"Nombre requerido\"}"); return; }
        nombre = nombre.trim();
        jugadores.putIfAbsent(nombre, new Jugador(nombre));
        guardar();
        respondJson(ex, "{\"ok\":true,\"nombre\":\"" + esc(nombre) + "\"}");
    }

    static synchronized void apiResponder(HttpExchange ex) throws IOException {
        Map<String, String> b = parseJson(body(ex));
        String nombre = b.get("nombre");
        String caminoStr = b.get("camino"); // ej: "0,2,3,4"
        Jugador j = nombre != null ? jugadores.get(nombre.trim()) : null;
        if (j == null) { respondJson(ex, "{\"error\":\"Jugador no registrado\"}"); return; }

        Pregunta p = preguntas.stream().filter(pr -> pr.id == preguntaActualId).findFirst().orElse(null);
        if (p == null || !p.activa) { respondJson(ex, "{\"error\":\"No hay pregunta activa\"}"); return; }
        if (j.respuestas.containsKey(p.id)) { respondJson(ex, "{\"error\":\"Ya respondiste esta pregunta\"}"); return; }

        // parsear camino del alumno
        List<Integer> camino = new ArrayList<>();
        if (caminoStr != null && !caminoStr.isBlank()) {
            for (String s : caminoStr.split(",")) {
                try { camino.add(Integer.parseInt(s.trim())); } catch (NumberFormatException ignored) {}
            }
        }

        long tiempoMs = System.currentTimeMillis() - p.iniciadaEn;
        RespuestaJugador resp = new RespuestaJugador(p.id, camino, tiempoMs);

        // calcular puntaje
        // Para BFS_CAMINO aceptamos CUALQUIER camino más corto válido,
        // no solo el que devolvió la implementación de referencia.
        // Para el resto exigimos el orden exacto.
        boolean correcta;
        if ("BFS_CAMINO".equals(p.tipo)) {
            correcta = esCaminoOptimoValido(camino, p.origen, p.destino, p.respuestaCorrecta.size());
        } else {
            correcta = camino.equals(p.respuestaCorrecta);
        }
        resp.correcta = correcta;
        int pts = 0;
        if (correcta) {
            // bonus por velocidad: más puntos si responde antes
            long seg = tiempoMs / 1000;
            pts = Math.max(10, 100 - (int)(seg * 2));
            j.racha++;
            if (j.racha >= 3) pts += 20; // bonus racha
        } else {
            // puntaje parcial: cuántos nodos del camino coinciden en orden
            int coincide = 0;
            List<Integer> correcto = p.respuestaCorrecta;
            for (int i = 0; i < Math.min(camino.size(), correcto.size()); i++) {
                if (camino.get(i).equals(correcto.get(i))) coincide++;
                else break;
            }
            pts = coincide * 3;
            j.racha = 0;
        }
        resp.puntosObtenidos = pts;
        j.puntaje += pts;
        j.tiempoTotal += (int)tiempoMs;
        j.respuestas.put(p.id, resp);
        guardar();
        respondJson(ex, "{\"ok\":true,\"correcta\":" + correcta + ",\"puntos\":" + pts
                + ",\"tiempoMs\":" + tiempoMs + "}");
    }

    static synchronized void apiActivar(HttpExchange ex) throws IOException {
        Map<String, String> b = parseJson(body(ex));
        if (!PASS.equals(b.get("pass"))) { respondJson(ex, "{\"error\":\"Contraseña incorrecta\"}"); return; }
        String idStr = b.get("id");
        int id = idStr != null ? Integer.parseInt(idStr.trim()) : -1;
        Pregunta p = preguntas.stream().filter(pr -> pr.id == id).findFirst().orElse(null);
        if (p == null) { respondJson(ex, "{\"error\":\"Pregunta no encontrada\"}"); return; }
        // desactivar anterior
        preguntas.forEach(pr -> pr.activa = false);
        p.activa = true;
        p.iniciadaEn = System.currentTimeMillis();
        preguntaActualId = p.id;
        respondJson(ex, "{\"ok\":true}");
    }

    static synchronized void apiNuevaPregunta(HttpExchange ex) throws IOException {
        Map<String, String> b = parseJson(body(ex));
        if (!PASS.equals(b.get("pass"))) { respondJson(ex, "{\"error\":\"Contraseña incorrecta\"}"); return; }
        String tipo = b.getOrDefault("tipo", "BFS_CAMINO");
        int origen  = Integer.parseInt(b.getOrDefault("origen",  "0"));
        int destino = Integer.parseInt(b.getOrDefault("destino", "5"));
        generarPregunta(tipo, origen, destino);
        Pregunta ultima = preguntas.get(preguntas.size() - 1);
        respondJson(ex, "{\"ok\":true,\"id\":" + ultima.id + "}");
    }

    static synchronized void apiEditarArista(HttpExchange ex) throws IOException {
        Map<String, String> b = parseJson(body(ex));
        if (!PASS.equals(b.get("pass"))) { respondJson(ex, "{\"error\":\"Contraseña incorrecta\"}"); return; }
        int u    = Integer.parseInt(b.getOrDefault("u",    "0"));
        int v    = Integer.parseInt(b.getOrDefault("v",    "1"));
        int peso = Integer.parseInt(b.getOrDefault("peso", "0"));
        if (peso <= 0) grafo.quitarArista(u, v);
        else           grafo.agregarArista(u, v, peso);
        // recalcular preguntas afectadas (simplificado: regenerar todas)
        preguntas.clear();
        nextPregId = 1;
        generarPreguntas();
        respondJson(ex, "{\"ok\":true}");
    }

    static synchronized void apiResolver(HttpExchange ex) throws IOException {
        // Endpoint educativo: muestra la solución de la pregunta activa
        Pregunta p = preguntas.stream().filter(pr -> pr.id == preguntaActualId).findFirst().orElse(null);
        if (p == null) { respondJson(ex, "{\"error\":\"Sin pregunta activa\"}"); return; }
        String resp = grafo.listaToJson(p.respuestaCorrecta);
        // también mostrar paso a paso según tipo
        respondJson(ex, "{\"respuesta\":" + resp
                + ",\"tipo\":\"" + p.tipo + "\""
                + ",\"origen\":" + p.origen
                + ",\"destino\":" + p.destino + "}");
    }

    static synchronized void apiReiniciar(HttpExchange ex) throws IOException {
        Map<String, String> b = parseJson(body(ex));
        if (!PASS.equals(b.get("pass"))) { respondJson(ex, "{\"error\":\"Contraseña incorrecta\"}"); return; }
        jugadores.clear();
        preguntas.clear();
        nextPregId = 1;
        preguntaActualId = -1;
        grafo = crearGrafoInicial();
        generarPreguntas();
        guardar();
        respondJson(ex, "{\"ok\":true}");
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  VALIDACIONES                                           ║
    // ╚══════════════════════════════════════════════════════════╝
    /**
     * Acepta cualquier camino del alumno que:
     *  - empiece en origen y termine en destino,
     *  - use solo aristas existentes en la matriz,
     *  - tenga exactamente la misma cantidad de nodos que el camino
     *    óptimo encontrado por BFS (= longitud mínima).
     * BFS no garantiza unicidad del camino, así que esto es lo correcto
     * para no marcar como incorrectas respuestas válidas.
     */
    static boolean esCaminoOptimoValido(List<Integer> camino, int origen, int destino, int longitudOptima) {
        if (camino == null || camino.size() != longitudOptima) return false;
        if (camino.isEmpty()) return false;
        if (camino.get(0) != origen) return false;
        if (camino.get(camino.size() - 1) != destino) return false;
        for (int i = 0; i < camino.size() - 1; i++) {
            int u = camino.get(i), v = camino.get(i + 1);
            if (u < 0 || u >= grafo.n || v < 0 || v >= grafo.n) return false;
            if (grafo.mat[u][v] == 0) return false; // no hay arista
        }
        return true;
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  LÓGICA DEL JUEGO                                       ║
    // ╚══════════════════════════════════════════════════════════╝
    static void generarPreguntas() {
        // BFS camino más corto
        generarPregunta("BFS_CAMINO", 7, 10);   // Boston → CDMX
        generarPregunta("BFS_CAMINO", 14, 5);   // Vancouver → Miami
        generarPregunta("BFS_CAMINO", 0, 11);   // NYC → Guadalajara
        // BFS orden de visita
        generarPregunta("BFS_ORDEN", 0, -1);    // BFS desde NYC
        generarPregunta("BFS_ORDEN", 10, -1);   // BFS desde CDMX
        // DFS orden
        generarPregunta("DFS_ORDEN", 7, -1);    // DFS desde Boston
        // NOTA: MATRIZ_QUERY queda disponible desde el panel Admin
        // pero no se predefine porque la UI actual no permite responder
        // (los alumnos clickean nodos, no escriben "1,peso").
    }

    static void generarPregunta(String tipo, int origen, int destino) {
        List<Integer> respuesta;
        String enunciado;
        switch (tipo) {
            case "BFS_CAMINO":
                respuesta = grafo.bfsCamino(origen, destino);
                enunciado = "🗺️ BFS: ¿Cuál es el camino más corto de "
                        + CIUDADES[origen] + " a " + CIUDADES[destino] + "?\n"
                        + "Ingresá los nodos en orden separados por coma (ej: 0,2,3)";
                break;
            case "BFS_ORDEN":
                respuesta = grafo.bfs(origen);
                enunciado = "🔍 BFS: ¿En qué orden visita BFS todos los nodos partiendo de "
                        + CIUDADES[origen] + " (nodo " + origen + ")?\n"
                        + "Ingresá el orden completo de visita";
                break;
            case "DFS_ORDEN":
                respuesta = grafo.dfs(origen);
                enunciado = "🌀 DFS: ¿En qué orden visita DFS todos los nodos partiendo de "
                        + CIUDADES[origen] + " (nodo " + origen + ")?\n"
                        + "Ingresá el orden completo de visita";
                break;
            case "MATRIZ_QUERY": {
                int peso = grafo.mat[origen][destino];
                if (peso > 0) {
                    respuesta = new ArrayList<>();
                    respuesta.add(1);
                    respuesta.add(peso);
                } else {
                    respuesta = new ArrayList<>();
                    respuesta.add(0);
                }
                enunciado = "📊 MATRIZ: ¿Están conectados " + CIUDADES[origen]
                        + " y " + CIUDADES[destino] + "? Si sí, ¿cuál es el peso?\n"
                        + "Respondé: '1,peso' si están conectados, '0' si no";
                break;
            }
            default:
                respuesta = new ArrayList<>();
                enunciado = "Pregunta desconocida";
                break;
        }
        preguntas.add(new Pregunta(nextPregId++, tipo, enunciado, origen, destino, respuesta));
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  PERSISTENCIA                                           ║
    // ╚══════════════════════════════════════════════════════════╝
    static void guardar() {
        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Path.of(SAVE), StandardCharsets.UTF_8))) {
            for (Jugador j : jugadores.values()) {
                pw.println("JUG|" + j.nombre + "|" + j.puntaje + "|" + j.racha + "|" + j.tiempoTotal);
            }
            pw.println("PREGACTUAL|" + preguntaActualId);
        } catch (IOException e) {
            System.err.println("Error guardando: " + e.getMessage());
        }
    }

    static void cargar() {
        if (!Files.exists(Path.of(SAVE))) return;
        try {
            for (String line : Files.readAllLines(Path.of(SAVE), StandardCharsets.UTF_8)) {
                String[] p = line.split("\\|", -1);
                if (p.length == 0) continue;
                switch (p[0]) {
                    case "JUG":
                        if (p.length >= 5) {
                            Jugador j = new Jugador(p[1]);
                            j.puntaje     = Integer.parseInt(p[2]);
                            j.racha       = Integer.parseInt(p[3]);
                            j.tiempoTotal = Integer.parseInt(p[4]);
                            jugadores.put(p[1], j);
                        }
                        break;
                    case "PREGACTUAL":
                        if (p.length >= 2) preguntaActualId = Integer.parseInt(p[1]);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando: " + e.getMessage());
        }
    }

    // ╔══════════════════════════════════════════════════════════╗
    // ║  UTILS                                                  ║
    // ╚══════════════════════════════════════════════════════════╝
    static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }
    static String body(HttpExchange ex) throws IOException {
        return new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }
    static Map<String, String> parseJson(String json) {
        Map<String, String> map = new HashMap<>();
        if (json == null || json.isBlank()) return map;
        json = json.trim();
        if (json.startsWith("{")) json = json.substring(1, json.endsWith("}") ? json.length() - 1 : json.length());
        int i = 0;
        while (i < json.length()) {
            while (i < json.length() && json.charAt(i) != '"') i++;
            if (i >= json.length()) break;
            int ks = i + 1, ke = json.indexOf('"', ks);
            if (ke < 0) break;
            String key = json.substring(ks, ke);
            i = ke + 1;
            while (i < json.length() && json.charAt(i) != ':') i++;
            i++;
            while (i < json.length() && Character.isWhitespace(json.charAt(i))) i++;
            String val;
            if (i < json.length() && json.charAt(i) == '"') {
                int vs = i + 1, ve = vs;
                while (ve < json.length()) {
                    if (json.charAt(ve) == '\\') ve++;
                    else if (json.charAt(ve) == '"') break;
                    ve++;
                }
                val = json.substring(vs, ve).replace("\\\"", "\"").replace("\\\\", "\\").replace("\\n", "\n");
                i = ve + 1;
            } else {
                int ve = i;
                while (ve < json.length() && json.charAt(ve) != ',' && json.charAt(ve) != '}') ve++;
                val = json.substring(i, ve).trim();
                i = ve;
            }
            map.put(key, val);
            while (i < json.length() && json.charAt(i) != '"' && json.charAt(i) != '}') i++;
        }
        return map;
    }
    static void respond(HttpExchange ex, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(bytes); }
    }
    static void respondJson(HttpExchange ex, String body) throws IOException { respond(ex, 200, body); }
    static void respondHtml(HttpExchange ex, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        ex.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(bytes); }
    }

    // HTML en grafo.html (compatible con javac sin text blocks)
    static String HTML;

    static String cargarHtml() throws IOException {
        String[] rutas = { "grafo.html", "src/juego/grafo.html", "../grafo.html" };
        for (String r : rutas) {
            Path p = Path.of(r);
            if (Files.isRegularFile(p)) {
                return Files.readString(p, StandardCharsets.UTF_8);
            }
        }
        try (InputStream in = GrafoGame.class.getResourceAsStream("grafo.html")) {
            if (in != null) {
                return new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
        }
        throw new IOException("No se encontro grafo.html. Debe estar en src/juego/grafo.html");
    }
}
