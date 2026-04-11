package senior;

public interface ListaTDA<T> {
    void agregar(T elemento);
    T obtener(int indice);
    void eliminar(int indice);
    int tamanio();
    boolean estaVacia();
}
