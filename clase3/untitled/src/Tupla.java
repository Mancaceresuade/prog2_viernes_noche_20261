public class Tupla <T,U,V> implements iTupla<T,U,V>{
    private T primero;
    private U segundo;
    private V tercero;

    public Tupla(T primero, U segundo, V tercero) {
        this.primero = primero;
        this.segundo = segundo;
        this.tercero = tercero;
    }

    @Override
    public T getPrimero() {
        return this.primero;
    }

    @Override
    public U getSegundo() {
        return this.segundo;
    }

    @Override
    public V getTercero() {
        return this.tercero;
    }

    @Override
    public void imprimirTupla() {
        System.out.println(this.primero+" "+this.segundo+" "+this.tercero);
    }
}
