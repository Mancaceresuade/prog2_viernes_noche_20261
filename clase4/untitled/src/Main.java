import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        ConjuntoTDA<Integer> numeros = new Conjunto<>(5);
        numeros.agregar(5);
        numeros.agregar(5);
        System.out.println(numeros);
        /*
        Set<Integer> numeros = new TreeSet<>(); //new HashSet<>();
        numeros.add(23);
        numeros.add(14);
        numeros.add(24);
        System.out.println(numeros);
         */
    }
}