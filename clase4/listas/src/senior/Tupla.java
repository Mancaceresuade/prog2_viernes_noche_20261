package senior;

public class Tupla<A, B> {
    private final A primero;
    private final B segundo;

    public Tupla(A primero, B segundo) {
        if (primero == null || segundo == null) {
            throw new IllegalArgumentException("Los componentes de la tupla no pueden ser nulos.");
        }
        this.primero = primero;
        this.segundo = segundo;
    }

    public A getPrimero() {
        return primero;
    }

    public B getSegundo() {
        return segundo;
    }

    @Override
    public String toString() {
        return "(" + primero + ", " + segundo + ")";
    }
}
