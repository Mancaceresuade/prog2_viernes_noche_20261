public class App {
    public static void main(String[] args) throws Exception {
        
        AVL<Cliente> avl = new AVL<>();
        avl.agregar(new Cliente(0.8));
        avl.agregar(new Cliente(0.5));
        avl.agregar(new Cliente(0.9));
        avl.agregar(new Cliente(0.3));
        // avl.imprimirPorNivel(2);
        avl.imprimirMayor();
        /*
        AVL<Integer> avl = new AVL<>();
        avl.agregar(10);
        avl.agregar(5);
        avl.agregar(20);
        avl.imprimirMayor();
         */
        /*
        AVL<String> avl = new AVL<>();
        avl.agregar("M");        
        avl.agregar("N");
        avl.agregar("O");
        avl.imprimirPorNivel(0  );
        System.err.println("");
        avl.imprimirPorNivel(1  );
        avl.agregar("L");
        avl.agregar("K");
        System.err.println("");
        avl.imprimirPorNivel(2 );
         */
        /*
        ABB<Cliente> arbol = new ABB<>();   
        arbol.insertar(new Cliente(0.8));
        arbol.insertar(new Cliente(0.9));
        arbol.insertar(new Cliente(0.7));
        arbol.imprimirPorNivel(1);
        */
        /*
        Arbol<Cliente> arbol = new Arbol<>();
        arbol.insertar(new Cliente(0.8));
        // arbol.imprimirPorNivel(0);
        arbol.insertar(new Cliente(0.5));
        arbol.insertar(new Cliente(0.9));
        arbol.imprimirPorNivel(2);
         */
        /*
        System.out.println("¿El árbol está vacío? " + arbol.estaVacio());
        Arbol<Integer> arbolEnteros = new Arbol<>();
        System.out.println("¿El árbol de enteros está vacío? " + arbolEnteros.estaVacio());
        
        Nodo<Cliente> nodo1 = new Nodo<>(new Cliente(0.8));
        System.out.println(nodo1);
        Nodo<Integer> nodo2 = new Nodo<>(5);
        System.out.println(nodo2);
         */
    }   
}
