package estatico;

public class Main {
    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico();

        // Agregar vértices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        
        // Agregar aristas
        grafo.agregarArista(1, 2, 5);
        grafo.agregarArista(1, 3, 10);
        grafo.agregarArista(2, 3, 15);

        grafo.mostrarGrafo();
    }
}
