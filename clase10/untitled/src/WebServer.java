import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebServer {
    private Arbol<?> arbol;

    public WebServer(Arbol<?> arbol) {
        this.arbol = arbol;
    }

    public void start(int puerto) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(puerto), 0);
        
        // Endpoint para el JSON del árbol
        server.createContext("/api/arbol", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = arbol.toJson();
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Endpoint para la página web
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                byte[] response;
                try {
                    response = Files.readAllBytes(Paths.get("src/web/index.html"));
                    exchange.getResponseHeaders().set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, response.length);
                } catch (IOException e) {
                    String error = "Archivo index.html no encontrado en src/web/";
                    response = error.getBytes();
                    exchange.sendResponseHeaders(404, response.length);
                }
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            }
        });

        server.setExecutor(null); // default executor
        System.out.println("Servidor iniciado en http://localhost:" + puerto);
        server.start();
    }
}
