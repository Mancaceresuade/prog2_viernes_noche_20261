package clase4;

public class EjemploPilaDinamica {

    private static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cima;

    public EjemploPilaDinamica() {
        this.cima = null;
    }

    public void apilar(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
    }

    public int desapilar() throws Exception {
        if (cima == null) {
            throw new Exception("La pila está vacía");
        }
        int dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public int cima() throws Exception {
        if (cima == null) {
            throw new Exception("La pila está vacía");
        }
        return cima.dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}
