package clase4;

class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fondo;

    public Cola() {
        this.frente = null;
        this.fondo = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (estaVacia()) {
            frente = nuevoNodo;
            fondo = nuevoNodo;
        } else {
            fondo.siguiente = nuevoNodo;
            fondo = nuevoNodo;
        }
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fondo = null;
        }
        return dato;
    }

    public T obtenerFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return frente.dato;
    }
}

public class EjemploColaDinamica {
    public static void main(String[] args) {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);

        System.out.println("Frente de la cola: " + cola.obtenerFrente());
        System.out.println("Desencolando: " + cola.desencolar());
        System.out.println("Frente de la cola: " + cola.obtenerFrente());
    }
}