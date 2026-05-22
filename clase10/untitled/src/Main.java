import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Arbol<Equipo> arbol = new Arbol<>();
        
        // Cargamos equipos del mundial para que el árbol se vea genial
        arbol.insertar(new Equipo("Argentina", 1200));
        arbol.insertar(new Equipo("Francia", 1150));
        arbol.insertar(new Equipo("Brasil", 1250));
        arbol.insertar(new Equipo("España", 1100));
        arbol.insertar(new Equipo("Inglaterra", 1180));
        arbol.insertar(new Equipo("Alemania", 1220));
        arbol.insertar(new Equipo("Portugal", 1300));
        arbol.insertar(new Equipo("Uruguay", 1050));
        arbol.insertar(new Equipo("Croacia", 1120));

        System.out.println("Árbol generado (Pre-order):");
        arbol.preOrder();

        // Iniciamos el servidor web
        try {
            WebServer server = new WebServer(arbol);
            server.start(8080);
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
