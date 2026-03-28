public class ConjuntoDinamico<T> implements iConjunto<T> {
    private Nodo<T> inicio;

    public ConjuntoDinamico() {
        this.inicio = null;
    }

    @Override
    public void agregar(T elemento) {
        if (!pertenece(elemento)) {
            Nodo<T> nuevo = new Nodo<>(elemento);
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
    }

    @Override
    public void sacar(T elemento) {
        if (estaVacio()) return;

        if (inicio.elemento.equals(elemento)) {
            inicio = inicio.siguiente;
            return;
        }

        Nodo<T> aux = inicio;
        while (aux.siguiente != null && !aux.siguiente.elemento.equals(elemento)) {
            aux = aux.siguiente;
        }

        if (aux.siguiente != null) {
            aux.siguiente = aux.siguiente.siguiente;
        }
    }

    @Override
    public boolean pertenece(T elemento) {
        Nodo<T> aux = inicio;
        while (aux != null) {
            if (aux.elemento.equals(elemento)) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    @Override
    public boolean estaVacio() {
        return inicio == null;
    }

    @Override
    public void imprimir() {
        Nodo<T> aux = inicio;
        while (aux != null) {
            System.out.print(aux.elemento + " ");
            aux = aux.siguiente;
        }
        System.out.println();
    }

    private static class Nodo<T> {
        T elemento;
        Nodo<T> siguiente;

        Nodo(T elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }
    }
}
