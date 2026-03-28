public interface iConjunto <T>{
    void agregar(T elemento);
    void sacar(T elemento);
    boolean pertenece(T elemento);
    boolean estaVacio();
    void imprimir();
}
