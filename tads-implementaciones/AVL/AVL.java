public class AVL<T extends Comparable<T>> {
    
    private Arbol<T> arbol;

    public AVL() {
        arbol = new Arbol<>();
    }
    public void agregar(T elemento) {
        arbol.setRaiz(agregarRecursivo(arbol.getRaiz(), elemento));
    }

    private Nodo<T> agregarRecursivo(Nodo<T> nodo, T elemento) {
        if (nodo == null) {
            return new Nodo<>(elemento);
        }

        int cmp = elemento.compareTo(nodo.getDato());
        if (cmp < 0) {
            nodo.setIzquierdo(agregarRecursivo(nodo.getIzquierdo(), elemento));
        } else if (cmp > 0) {
            nodo.setDerecho(agregarRecursivo(nodo.getDerecho(), elemento));
        } else {
            // Elemento duplicado, no se agrega
            return nodo;
        }

        // Actualizar altura
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        // Balancear el nodo
        int balance = getBalance(nodo);

        // Rotaciones
        // Izquierda Izquierda
        if (balance > 1 && elemento.compareTo(nodo.getIzquierdo().getDato()) < 0) {
            return rotarDerecha(nodo);
        }
        // Derecha Derecha
        if (balance < -1 && elemento.compareTo(nodo.getDerecho().getDato()) > 0) {
            return rotarIzquierda(nodo);
        }
        // Izquierda Derecha
        if (balance > 1 && elemento.compareTo(nodo.getIzquierdo().getDato()) > 0) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }
        // Derecha Izquierda
        if (balance < -1 && elemento.compareTo(nodo.getDerecho().getDato()) < 0) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Métodos auxiliares para AVL

    private int altura(Nodo<T> nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private int getBalance(Nodo<T> nodo) {
        return (nodo == null) ? 0 : altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    private Nodo<T> rotarDerecha(Nodo<T> y) {
        Nodo<T> x = y.getIzquierdo();
        Nodo<T> T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        y.setAltura(1 + Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())));
        x.setAltura(1 + Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())));

        return x;
    }

    private Nodo<T> rotarIzquierda(Nodo<T> x) {
        Nodo<T> y = x.getDerecho();
        Nodo<T> T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        x.setAltura(1 + Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())));
        y.setAltura(1 + Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())));

        return y;
    }

    public void imprimirPorNivel(int nivel) {
        if (arbol.estaVacio()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        arbol.imprimirPorNivel(nivel);
    }

    public void imprimirMayor() {
        if (arbol.estaVacio()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        System.out.println(imprimirMayorRecursivo(arbol.getRaiz()).toString());
    }
    private Nodo<T> imprimirMayorRecursivo(Nodo<T> raiz) {
        if (raiz.getDerecho() != null) {
            return imprimirMayorRecursivo(raiz.getDerecho());
        }
        return raiz; // Retorna el nodo con el valor máximo
    }
    
}
