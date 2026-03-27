package clase4;

public class EjemploColaEstatica {
    private int[] cola;
    private int frente;
    private int fin;
    private int capacidad;

    public EjemploColaEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new int[capacidad];
        this.frente = -1;
        this.fin = -1;
    }

    public boolean estaVacia() {
        return frente == -1;
    }

    public boolean estaLlena() {
        return (fin + 1) % capacidad == frente;
    }

    public void encolar(int elemento) {
        if (estaLlena()) {
            throw new IllegalStateException("La cola está llena");
        }
        if (estaVacia()) {
            frente = 0;
        }
        fin = (fin + 1) % capacidad;
        cola[fin] = elemento;
    }

    public int desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        int elemento = cola[frente];
        if (frente == fin) {
            frente = -1;
            fin = -1;
        } else {
            frente = (frente + 1) % capacidad;
        }
        return elemento;
    }

    public int obtenerFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return cola[frente];
    }
}