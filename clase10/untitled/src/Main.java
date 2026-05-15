public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        Equipo equipo = new Equipo("Arg",1200);
        arbol.insertar(equipo);
        Equipo equipo1 = new Equipo("Fra",1100);
        arbol.insertar(equipo1);
        arbol.preOrder();
    }
}