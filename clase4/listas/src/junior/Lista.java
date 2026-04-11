package junior;

public class Lista {
    private Tupla[] elementos;
    private int cantidad;
    private static final int CAPACIDAD = 10;

    public Lista() {
        this.elementos = new Tupla[CAPACIDAD];
        this.cantidad = 0;
    }

    public void agregar(Tupla t) {
        if (cantidad >= CAPACIDAD) {
            System.out.println("Lista llena, no se puede agregar mas.");
            return;
        }
        elementos[cantidad] = t;
        cantidad++;
    }

    public Tupla obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }
        return elementos[indice];
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}
