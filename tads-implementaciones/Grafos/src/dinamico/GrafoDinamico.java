package dinamico;

public class GrafoDinamico {
    NodoGrafo origen;
    public GrafoDinamico() {
        origen = null;
    }
    public void agregarVertice(int v) {
        NodoGrafo nuevoNodo = new NodoGrafo();
        nuevoNodo.nodo = v;
        nuevoNodo.nodSig = origen;
        origen = nuevoNodo;
    }    
    public void agregarArista(int v1, int v2, int peso) {
        NodoGrafo nodo1 = buscarVertice(v1);
        NodoGrafo nodo2 = buscarVertice(v2);
        if (nodo1 != null && nodo2 != null) {
            NodoArista nuevaArista = new NodoArista();
            nuevaArista.etiqueta = peso;
            nuevaArista.NodoGrafo = nodo2;
            nuevaArista.sigArista = nodo1.arista;
            // Insertar la nueva arista al inicio de la lista de aristas del nodo1
            nodo1.arista = nuevaArista;
        }
    }
    private NodoGrafo buscarVertice(int v1) {
        NodoGrafo actual = origen;
        while (actual != null) {
            if (actual.nodo == v1) {
                return actual;
            }
            actual = actual.nodSig;
        }
        return null; // Si no se encuentra el vértice
    }

    public void imprimirGrafo() {
        System.out.print("--- Grafo Dinámico ---\n");
        NodoGrafo actual = origen;
        while (actual != null) {
            System.out.print("Nodo " + actual.nodo + ": ");
            NodoArista aristaActual = actual.arista;
            while (aristaActual != null) {
                System.out.print(" -> " + aristaActual.NodoGrafo.nodo + "(" + aristaActual.etiqueta + ")");
                aristaActual = aristaActual.sigArista;
            }
            System.out.println();
            actual = actual.nodSig;
        }
    }
}