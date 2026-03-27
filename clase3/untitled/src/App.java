public class App {
    public static void main(String[] args) {
        ParOrdenado<Integer,Integer> coordenada = new ParOrdenado<>(6,5);
        System.out.println(coordenada.getPrimero());
        System.out.println(coordenada.getSegundo());

        ParOrdenado<String,Integer> calleNumero = new ParOrdenado<>("Belgrano",5);
        System.out.println(calleNumero.getPrimero());
        System.out.println(calleNumero.getSegundo());
    }
}
