public class Arbol<T extends Comparable<T>> {
    Nodo<T> raiz;
    public void insertar(T e) {
        raiz = insertarRec(raiz, e);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T e) {
        if (nodo == null)           // lugar vacío → crear nodo
            return new Nodo<T>(e);

        int comp = e.compareTo(nodo.equipo);
        if (comp < 0)
            nodo.izq = insertarRec(nodo.izq, e);  // va a la izquierda
        else if (comp > 0)
            nodo.der = insertarRec(nodo.der, e);  // va a la derecha
        // si comp == 0: no se inserta (duplicado)

        return nodo;
    }

    public void preOrder() {
        preOrder(this.raiz);
    }

    private void preOrder(Nodo<T> nodo) {
        if(nodo==null) return;
        System.out.println(nodo);
        preOrder(nodo.izq);
        preOrder(nodo.der);
    }
    public Nodo<T> buscarPorNombre(String nombre) {
        return buscarNombreRec(raiz, nombre);
    }

    private Nodo<T> buscarNombreRec(Nodo<T> nodo, String nombre) {
        if (nodo == null) return null;  // no encontrado

        // Como es genérico, dependemos de toString o una interfaz. 
        // Para este ejercicio, asumiremos que comparamos con el nombre vía toString o casting.
        if (nodo.equipo instanceof Equipo) {
            if (((Equipo)nodo.equipo).nombre.equals(nombre)) {
                return nodo;
            }
        } else if (nodo.equipo.toString().contains(nombre)) {
            return nodo;
        }

        // Busca en ambos subárboles (no puede podar por nombre ya que está ordenado por rating)
        Nodo<T> izqRes = buscarNombreRec(nodo.izq, nombre);
        if (izqRes != null) return izqRes;

        return buscarNombreRec(nodo.der, nombre);
    }

}


// Nodo del árbol
class Nodo<T extends Comparable<T>> {
    T equipo;
    Nodo<T> izq, der;
    public Nodo(T e) { this.equipo = e; }

    @Override
    public String toString() {
        return "Nodo{" +
                "equipo=" + equipo +
                '}';
    }
}