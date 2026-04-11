public class ConjuntoDinamico<T> implements ConjuntoTDA<T>{
    Nodo<T> primero;
    @Override
    public void agregar(T elemento) {
        Nodo<T> aux = new Nodo<>(elemento);
        if(this.primero==null){
            this.primero = aux;
        } else {
            Nodo<T> actual = primero;
            while(actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = aux;
        }
    }

    @Override
    public void listar() {
        Nodo<T> actual = primero;
        while(actual.siguiente != null) {
            System.out.println(actual);
            actual = actual.siguiente;
        }
    }

    @Override
    public boolean existe(T elemento) {
        return false;
    }
}

class Nodo<T> {
    T elemento;
    Nodo siguiente;
    public Nodo(T elemento) {
        this.elemento = elemento;
    }
    @Override
    public String toString() {
        return elemento.toString();
    }
}