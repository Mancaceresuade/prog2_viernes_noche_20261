public class Main {
    public static void main(String[] args) {
        int[][] matriz = {{3,4,5},{3,2,1},{5,4,3}};
        System.out.println(algunaFilaTodosPares(matriz));
    }
    private static boolean algunaFilaTodosPares(int[][] matriz) {
        boolean rta = false; // 1
        int aux = matriz.length; // 1
        for (int i = 0; i < aux; i++) { // 1+n+n
            rta = rta || todosParesEnFila(matriz[i]); // 2n + n * m(n)
        }
        return rta; // 1
    } // f(n) = 1+1+1+n+n+2n+n*m(n)+1
    // f(n) = 1+1+1+n+n+2n+n*(4+7n)+1
    // f(n) = 1+1+1+n+n+2n+n4+7n^2+1 = 4+8n+7n^2
    // f(n) < c g(n)
    // 4+8n+7n^2 < 8 n^2
    // 4/n^2+8n/n^2+7n^2/n^2 < 8 n^2/n^2
    // 4/n^2+8/n+7 < 8
    // f(n) pertence a O(n^2) para c=8 y desde n0 > 9
    private static boolean todosParesEnFila(int[] fila) {
        boolean rta = true; // 1
        int aux = fila.length; // 1
        for (int i = 0; i < aux; i++) { // 1+n+n
            rta = rta && esPar(fila[i]); // n + n k(n) = n + n*3
        }
        return rta; // 1
    } // m(n) = 1+1+1+n+n+2n+3n+1 = 4+7n
    // Complejidad asintotica
    // m(n) < c g(n)
    // Termino dominante, constante + 1
    // 4+7n < 8n
    // 4/n+7n/n < 8n/n
    // 4/n+7 < 8
    // de 1 a 4 no se cumple, se cumple a partir de n0 >= 5
    // Por lo tanto m(n) pertence a O(n)   para c= 9 y n0 >= 5

    private static boolean esPar(int numero) {
        return (numero%2)==0; // 3
    } // k(n) = 3


}