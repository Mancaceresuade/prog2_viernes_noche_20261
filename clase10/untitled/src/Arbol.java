public class Arbol {
    Nodo raiz;
    public void insertar(Equipo e) {
        raiz = insertarRec(raiz, e);
    }

    private Nodo insertarRec(Nodo nodo, Equipo e) {
        if (nodo == null)           // lugar vacío → crear nodo
            return new Nodo(e);

        if (e.rating < nodo.equipo.rating)
            nodo.izq = insertarRec(nodo.izq, e);  // va a la izquierda
        else if (e.rating > nodo.equipo.rating)
            nodo.der = insertarRec(nodo.der, e);  // va a la derecha
        // si rating == rating: no se inserta (duplicado)

        return nodo;
    }

    public void preOrder() {
        preOrder(this.raiz);
    }

    private void preOrder(Nodo nodo) {
        if(nodo==null) return;
        System.out.println(nodo);
        preOrder(nodo.izq);
        preOrder(nodo.der);
    }


}


// Nodo del árbol
class Nodo {
    Equipo equipo;
    Nodo   izq, der;
    public Nodo(Equipo e) { this.equipo = e; }

    @Override
    public String toString() {
        return "Nodo{" +
                "equipo=" + equipo +
                '}';
    }
}