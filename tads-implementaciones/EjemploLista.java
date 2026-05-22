package clase4;

import java.util.ArrayList;

public class EjemploLista {
    public static void main(String[] args) {

        ArrayList<String> lista = new ArrayList<>();
        lista.add("Hola");
        lista.add("Mundo");
        lista.add("Java");
        for (String elemento : lista) {
            if(elemento.equals("Mundo")) {
                lista.remove(elemento);
            }
        }
        
        // LinkedList<String> lista2 = new LinkedList<>();

        /*
        Lista<String> palabras = new Lista<>(10);
        palabras.insertar(0, "8");
        palabras.insertar(1, "10");
        palabras.insertar(0, "4");
        palabras.imprimirLista();
        */
        /*
        palabras.agregar("Hola");
        palabras.agregar("Mundo");
        palabras.agregar("Java");
        palabras.imprimirLista();

        ListaDinamica<String> palabrasDinamica = new ListaDinamica<>();
        palabrasDinamica.agregar("Hola");
        palabrasDinamica.agregar("Mundo");
        palabrasDinamica.agregar("Java");
        palabrasDinamica.imprimirLista();
        */

    }
    
}

class Lista<T> {
    private T[] elementos;
    private int contador = 0;

    @SuppressWarnings("unchecked")
    public Lista(int capacidad) {
        elementos = (T[]) new Object[capacidad];
    }

    public void insertar(int pos, T elemento) {
        for (int i = contador; i > pos; i--) { // 1 
            elementos[i] = elementos[i - 1];
        }
        elementos[pos] = elemento; // 1
        contador++; // 1
    }

    public void agregar(T elemento) {
        elementos[contador++] = elemento;
    }

    public T obtener(int indice) {
        return elementos[indice];
    }

    public void imprimirLista() {
        for (int i = 0; i < contador; i++) {
            System.out.println(elementos[i]);
        }
    }

}


class ListaDinamica<T> {  // linked list

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int contador = 0;

    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        contador++;
    }

    public void imprimirLista() {
        Nodo<T> actual = primero;
        while (actual != null) {
            System.out.println(actual.getElemento());
            actual = actual.getSiguiente();
        }
    }

    public T obtener(int indice) {
        Nodo<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getElemento();
    }

    public void insertar(int pos, T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (pos == 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
        contador++;
    }

    public void eliminar(int pos) {
        if (pos == 0) {
            primero = primero.getSiguiente();
        } else {
            Nodo<T> actual = primero;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            if (pos == contador - 1) {
                ultimo = actual;
            }
        }
        contador--;
    }

    public int getContador() {
        return contador;
    }
}

class Nodo<T> {
    private T elemento;
    private Nodo<T> siguiente;

    public Nodo(T elemento) {
        this.elemento = elemento;
        this.siguiente = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}