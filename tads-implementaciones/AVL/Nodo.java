public class Nodo<T> {
    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;
    private int altura;
    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; // Altura inicial del nodo es 1
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    } 
    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }
    public Nodo<T> getDerecho() {
        return derecho;
    }
    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }
    @Override
    public String toString() {
        return this.dato.toString();

    }   
}
