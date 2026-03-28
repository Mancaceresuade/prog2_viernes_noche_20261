public class PruebaConjunto {
    public static void main(String[] args) {
        iConjunto<Integer> conjunto = new ConjuntoEstatico<>(10);
        conjunto.agregar(10);
        conjunto.agregar(15);
        conjunto.agregar(10);
        conjunto.imprimir();
    }
}
