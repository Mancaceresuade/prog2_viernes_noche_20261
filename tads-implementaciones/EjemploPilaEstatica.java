package clase4;

public class EjemploPilaEstatica {
    public static void main(String[] args) {
        Pila<String> pila = new Pila<>(5);
        pila.apilar("Hola");
        pila.apilar("Mundo");
        pila.apilar("Java");
        System.out.println(pila.cima());        
        System.out.println(pila.desapilar());
        System.out.println(pila.cima());
    }
}

class Pila<T> {
    private T[] elementos;
    private int contador = 0;

    @SuppressWarnings("unchecked")
    public Pila(int capacidad) {
        elementos = (T[]) new Object[capacidad];
    }

    public void apilar(T elemento) {
        if (contador < elementos.length) {
            elementos[contador++] = elemento;
        } else {
            System.out.println("Pila llena");
        }
    }

    public T desapilar() {
        if (contador > 0) {
            return elementos[--contador];
        } else {
            System.out.println("Pila vacia");
            return null;
        }
    }

    public boolean esVacia() {
        return contador == 0;
    }

    public int tamano() {
        return contador;
    }

    public T cima() {
        if (contador > 0) {
            return elementos[contador - 1];
        } else {
            System.out.println("Pila vacia");
            return null;
        }
    }

}
