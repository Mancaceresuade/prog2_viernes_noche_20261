
public class App {
    public static void main(String[] args) throws Exception {
        Conjunto<Integer> c1 = new Conjunto<Integer>(5);
        c1.agregar(2);
        c1.agregar(3);
        c1.agregar(6);
        c1.mostrar();
        ConjuntoDinamico<Integer> c2 = new ConjuntoDinamico<Integer>();
        c2.agregar(10);
        c2.agregar(20);
        c2.agregar(5);       
        c2.mostrar();

    }
}


class Conjunto<T> {
    T[] elementos;
    int tam;
    @SuppressWarnings("unchecked")
    Conjunto(int tam) {
        this.tam = tam;
        elementos = (T[]) new Object[tam];        
    }
    public void agregar(T elemento) {
        for (int i = 0; i < tam; i++) {
            if (elementos[i] == null) {
                elementos[i] = elemento;
                break;
            }
        }
    }
    public boolean pertenece(T elemento) {
        for (int i = 0; i < tam; i++) {
            if (elementos[i] == elemento) {
                return true;
            }
        }
        return false;
    }
    public boolean esVacio() {
        for (int i = 0; i < tam; i++) {
            if (elementos[i] != null) {
                return false;
            }
        }
        return true;
    }
    public int cardinalidad() {
        int cont = 0;
        for (int i = 0; i < tam; i++) {
            if (elementos[i] != null) {
                cont++;
            }
        }
        return cont;
    }
    public Conjunto<T> union(Conjunto<T> otro) {
        Conjunto<T> nuevo = new Conjunto<T>(tam + otro.tam);
        for (int i = 0; i < tam; i++) {
            nuevo.agregar(elementos[i]);
        }
        for (int i = 0; i < otro.tam; i++) {
            nuevo.agregar(otro.elementos[i]);
        }
        return nuevo;
    }
    public Conjunto<T> interseccion(Conjunto<T> otro) {
        Conjunto<T> nuevo = new Conjunto<T>(tam);
        for (int i = 0; i < tam; i++) {
            if (otro.pertenece(elementos[i])) {
                nuevo.agregar(elementos[i]);
            }
        }
        return nuevo;
    }
    public Conjunto<T> diferencia(Conjunto<T> otro) {
        Conjunto<T> nuevo = new Conjunto<T>(tam);
        for (int i = 0; i < tam; i++) {
            if (!otro.pertenece(elementos[i])) {
                nuevo.agregar(elementos[i]);
            }
        }
        return nuevo;
    }
    public boolean esSubconjunto(Conjunto<T> otro) {
        for (int i = 0; i < tam; i++) {
            if (!otro.pertenece(elementos[i])) {
                return false;
            }
        }
        return true;
    }
    public void mostrar() {
        for (int i = 0; i < tam; i++) {
            if (elementos[i] != null) {
                System.out.println(elementos[i]);
            }
        }
    }
}


class ConjuntoDinamico<T> {
    
    Nodo<T> primero;    
    ConjuntoDinamico() {
        this.primero = null;
    }
    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<T>(elemento);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
    public void mostrar() {
        Nodo<T> actual = primero;
        while (actual != null) {
            System.out.println(actual.elemento);
            actual = actual.siguiente;
        }
    }
}


class Nodo<T> {
    T elemento;
    Nodo<T> siguiente;
    Nodo(T elemento) {
        this.elemento = elemento;
        this.siguiente = null;
    }
}