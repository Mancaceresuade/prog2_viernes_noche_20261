package dinamico;

public class Main {
    public static void main(String[] args) {
        GrafoDinamico grafo = new GrafoDinamico();
        
        // Agregar vértices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        
        // Agregar aristas
        grafo.agregarArista(1, 2, 5);
        grafo.agregarArista(1, 3, 10);
        grafo.agregarArista(2, 3, 15);
        grafo.agregarArista(3, 1, 10);
        
        // Imprimir el grafo
        grafo.imprimirGrafo();
    }    
}
