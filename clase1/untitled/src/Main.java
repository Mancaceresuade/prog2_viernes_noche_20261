import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] matriz = {{4,3,4},{1,2,3},{5,4,3}};
        recorrerPorFila(matriz);
        /*
        int[] numeros = {4,7,2,1};
        for (int i = 0; i < numeros.length; i++) {
            System.out.println(numeros[i]);
        }
        System.out.println(algunPar(numeros));
        */
         */
        /*
        int i = 0;
        while(i<10){
            System.out.println(i);
            i++;
        }
        */

        /*
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        */
        // comentario
        /*
        int numero=10;
        System.out.println("hola mundo");
         */
        // System.out.println(saludar("Raul"));
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un numero ");
        int numero = scanner.nextInt();
        System.out.println("su numero es " + numero);
        scanner.close();
        */

        // calcular();
    }

    private static void recorrerPorFila(int[][] matriz) {
        for (int i=0; i<matriz.length; i++){
            for (int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean algunPar(int[] numeros) {
        boolean rta = false;
        for (int i = 0; i < ; i++) {
            rta = rta || (numeros[i]%2)==0;
        }
        return rta;
    }

    private static boolean todosPares(int[] numeros) {
        boolean rta = true;
        for (int i = 0; i < numeros.length; i++) {
            rta = rta && (numeros[i]%2)==0;
        }
        return rta;
    }

    private static void calcular() {
        int numero=10;
        if(numero==10) {
            System.out.println("Es diez");
        }
    }
    private static String saludar(String nombre) {
        return "Hola "+nombre;
    }
}