public class ABB<T extends Comparable<T>> {
    private Arbol<T> arbol;
    public ABB() {
        this.arbol = new Arbol<>();
    }
    public Nodo<T> getRaiz() {
        return arbol.getRaiz();
    }
    public void imprimirPorNivel(int nivel) {
        arbol.imprimirPorNivel(nivel);
    }
    public void insertar(T dato) {
        insertar(arbol.getRaiz(), dato);
    }

    private void insertar(Nodo<T> raiz, T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (raiz == null) {
            arbol.setRaiz(nuevoNodo);
        } else {
            insertarRecursivo(raiz, nuevoNodo);
        }
    }
    
    private void insertarRecursivo(Nodo<T> raiz, Nodo<T> nuevoNodo) {
        if (nuevoNodo.getDato().compareTo(raiz.getDato()) < 0) {
            if (raiz.getIzquierdo() == null) {
                raiz.setIzquierdo(nuevoNodo);
            } else {
                insertarRecursivo(raiz.getIzquierdo(), nuevoNodo);
            }
        } else {
            if (raiz.getDerecho() == null) {
                raiz.setDerecho(nuevoNodo);
            } else {
                insertarRecursivo(raiz.getDerecho(), nuevoNodo);
            }
        }
    }
    public boolean estaVacio() {
        return arbol.estaVacio();
    }
    public boolean buscar(T dato) {
        return arbol.buscar(dato);
    }


}
