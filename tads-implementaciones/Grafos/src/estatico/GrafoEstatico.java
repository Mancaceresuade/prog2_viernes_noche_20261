package estatico;

public class GrafoEstatico {
    static int n = 101;
    int [][] MAdy;
    int maxEtiquetaUsada; // Para rastrear la etiqueta más alta usada
    public GrafoEstatico() {
        MAdy = new int [n][n];
        maxEtiquetaUsada = -1;
    }
    public void agregarVertice(int etiqueta) {
        if (etiqueta >= 0 && etiqueta < n) { // Asegurarse de que la etiqueta esté dentro de los límites
            if (etiqueta > maxEtiquetaUsada) {
                maxEtiquetaUsada = etiqueta;
            }
        } else {
            System.out.println("Etiqueta de vértice " + etiqueta + " fuera de rango (0 a " + (n - 1) + ").");
        }
    }
    public void agregarArista( int v1 , int v2 , int peso ) {
        if (v1 >= 0 && v1 < n && v2 >= 0 && v2 < n) {
            MAdy[v1][v2] = peso;
            MAdy[v2][v1] = peso; // Si es un grafo no dirigido
            // Asegurarse de que los vértices existan lógicamente
            if (v1 > maxEtiquetaUsada) maxEtiquetaUsada = v1;
            if (v2 > maxEtiquetaUsada) maxEtiquetaUsada = v2;
        } else {
            System.out.println("Vértices no válidos (fuera de rango): " + v1 + ", " + v2);
        }
    }    
    public void mostrarGrafo() {
        if (maxEtiquetaUsada == -1) {
            System.out.println("El grafo está vacío.");
            return;
        }
        System.out.println("--- Grafo Estático ---");
        for (int i = 0; i <= maxEtiquetaUsada; i++) { // Iterar hasta el máximo índice usado
            // Solo mostramos vértices que tienen alguna conexión o han sido agregados
            boolean tieneAristas = false;
            for (int j = 0; j <= maxEtiquetaUsada; j++) {
                if (MAdy[i][j] != 0) {
                    if (!tieneAristas) { // Imprimir la etiqueta del vértice solo una vez
                        System.out.print("Vértice " + i + ": ");
                    }
                    System.out.print(" -> " + j + " (peso: " + MAdy[i][j] + ")");
                    tieneAristas = true;
                }
            }
            if (tieneAristas) {
                System.out.println();
            } else if (i <= maxEtiquetaUsada) { // Si el vértice fue "agregado" pero no tiene aristas
                System.out.println("Vértice " + i + ": No tiene aristas.");
            }
        }
        System.out.println("-------------\n");
    }
}
