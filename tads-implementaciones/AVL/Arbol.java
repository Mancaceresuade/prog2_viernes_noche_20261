public class Arbol<T> {
    private Nodo<T> raiz;
    public Arbol() {
        this.raiz = null;
    }
    public Nodo<T> getRaiz() {
        return raiz;
    }
    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }
    public boolean estaVacio() {
        return this.raiz == null;
    }
    public void insertar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (this.estaVacio()) {
            this.raiz = nuevoNodo;
        } else {
            insertarRecursivo(this.raiz, nuevoNodo);
        }
    }
    private void insertarRecursivo(Nodo<T> raiz2, Nodo<T> nuevoNodo) {
        if (nuevoNodo.getDato().hashCode() < raiz2.getDato().hashCode()) {
            if (raiz2.getIzquierdo() == null) {
                raiz2.setIzquierdo(nuevoNodo);
            } else {
                insertarRecursivo(raiz2.getIzquierdo(), nuevoNodo);
            }
        } else {
            if (raiz2.getDerecho() == null) {
                raiz2.setDerecho(nuevoNodo);
            } else {
                insertarRecursivo(raiz2.getDerecho(), nuevoNodo);
            }
        }
    }    

    public void imprimirPorNivel(int nivel) {
        if (this.estaVacio()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        imprimirPorNivelRecursivo(this.raiz, nivel, 0);
    }
    private void imprimirPorNivelRecursivo(Nodo<T> nodo, int nivel, int nivelActual) {
        if (nodo == null) {
            return;
        }
        if (nivelActual == nivel) {
            System.out.print(nodo.getDato() + " ");
        } else {
            imprimirPorNivelRecursivo(nodo.getIzquierdo(), nivel, nivelActual + 1);
            imprimirPorNivelRecursivo(nodo.getDerecho(), nivel, nivelActual + 1);
        }
    }

    public boolean buscar(T dato) {
        return buscarRecursivo(this.raiz, dato);
    }
    private boolean buscarRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getDato().equals(dato)) {
            return true;
        }
        if (dato.hashCode() < nodo.getDato().hashCode()) {
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        } else {
            return buscarRecursivo(nodo.getDerecho(), dato);
        }
    }    

}
