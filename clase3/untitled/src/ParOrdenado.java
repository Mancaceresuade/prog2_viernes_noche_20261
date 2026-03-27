public class ParOrdenado <T,U> implements iParOrdenado<T,U>{
    private T primero;
    private U segundo;
    public ParOrdenado(T numero1, U numero2) { // para crear par ordenado
        this.primero = numero1;
        this.segundo = numero2;
    }
    public T getPrimero() {
        return this.primero;
    }
    public U getSegundo() {
        return  this.segundo;
    }

}
