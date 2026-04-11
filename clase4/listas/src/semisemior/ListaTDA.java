package semisemior;

public interface ListaTDA {
    void agregar(TuplaTDA tupla);
    TuplaTDA obtener(int indice);
    void eliminar(int indice);
    int tamanio();
    boolean estaVacia();
}
