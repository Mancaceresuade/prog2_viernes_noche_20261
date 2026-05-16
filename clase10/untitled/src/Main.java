public class Main {
    public static void main(String[] args) {
        // arbol pensado para ordenarse por scoring
        Arbol<Cliente> arbolCliente = new Arbol<>();
        Cliente cliente = new Cliente("Juan","32434");
        Cliente cliente1 = new Cliente("Carlos","35252434");
        arbolCliente.insertar(cliente);
        arbolCliente.insertar(cliente1);
        arbolCliente.preOrder();

        /*
        Arbol<Equipo> arbol = new Arbol<>();
        Equipo equipo = new Equipo("Arg",1200);
        arbol.insertar(equipo);
        Equipo equipo1 = new Equipo("Fra",1100);
        arbol.insertar(equipo1);
        arbol.preOrder();
        System.out.println(arbol.buscarPorNombre("Fra"));
        */

    }
}