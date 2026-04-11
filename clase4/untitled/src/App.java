import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class App {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(15);
        numeros.add(30);
        numeros.add(15);
        for (int i = 0; i < numeros.size(); i++) {
            System.out.println(numeros.get(i));
        }
        for (Integer num: numeros) {
            System.out.println(num);
        }
        Iterator<Integer> it = numeros.iterator();
        while(it.hasNext()) {
            Integer aux = it.next();
        }
        numeros.forEach( n -> System.out.println(n));
        numeros.stream().filter( n -> n > 15);
    }
}
