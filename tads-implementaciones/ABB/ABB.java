package clase11;

public class ABB<T extends Comparable<T>> {

	private Arbol<T> arbol;

    public ABB() {
        arbol = new Arbol<>();
    }
    
    public void imprimirNoEficiente() {
    	arbol.imprimirArbolNoEficiente();
    }
	
	public void imprimirMinimo() {
		System.out.println(minimo());
	}
	
	public T minimo() {
	    if (arbol.raiz == null) return null;
	    return minimo(arbol.raiz);
	}

	private T minimo(Nodo<T> nodo) {
	    if (nodo.izq == null) {
	        return nodo.elemento;
	    }
	    return (T) minimo(nodo.izq);
	}
	
	public void agregar(T elem) {
        arbol.raiz = agregar(arbol.raiz, elem);
    }

    private Nodo<T> agregar(Nodo<T> nodo, T elem) {
        if (nodo == null) {
            return new Nodo<>(elem);
        }
        int comparacion = elem.compareTo(nodo.elemento);
        if (comparacion < 0) {
            nodo.izq = agregar(nodo.izq, elem);
        } else if (comparacion > 0) {
            nodo.der = agregar(nodo.der, elem);
        }
        return nodo;
    }
    
}

