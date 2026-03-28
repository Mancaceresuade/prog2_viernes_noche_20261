public class App {
    public static void main(String[] args) {
        // Par Ordenado
        ParOrdenado<Integer,Integer> coordenada = new ParOrdenado<>(6,5);
        System.out.println("ParOrdenado (Integer): " + coordenada.getPrimero() + ", " + coordenada.getSegundo());

        // Tupla
        System.out.println("\nEjemplo de Tupla:");
        Tupla<String, Integer, Double> persona = new Tupla<>("Juan", 25, 1.75);
        persona.imprimirTupla();

        // Conjunto Estático
        System.out.println("\nEjemplo de ConjuntoEstatico:");
        iConjunto<String> miConjuntoEstatico = new ConjuntoEstatico<>(5);
        miConjuntoEstatico.agregar("A");
        miConjuntoEstatico.agregar("B");
        miConjuntoEstatico.agregar("A"); // Duplicado
        miConjuntoEstatico.agregar("C");
        miConjuntoEstatico.imprimir();
        System.out.println("¿Pertenece 'B'?: " + miConjuntoEstatico.pertenece("B"));
        miConjuntoEstatico.sacar("B");
        System.out.println("Después de sacar 'B':");
        miConjuntoEstatico.imprimir();

        // Conjunto Dinámico
        System.out.println("\nEjemplo de ConjuntoDinamico:");
        iConjunto<Integer> miConjuntoDinamico = new ConjuntoDinamico<>();
        miConjuntoDinamico.agregar(1);
        miConjuntoDinamico.agregar(2);
        miConjuntoDinamico.agregar(1); // Duplicado
        miConjuntoDinamico.agregar(3);
        miConjuntoDinamico.imprimir();
        System.out.println("¿Pertenece 2?: " + miConjuntoDinamico.pertenece(2));
        miConjuntoDinamico.sacar(2);
        System.out.println("Después de sacar 2:");
        miConjuntoDinamico.imprimir();
    }
}
