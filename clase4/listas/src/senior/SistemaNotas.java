package senior;

/**
 * Servicio que encapsula la logica de negocio sobre estudiantes y notas.
 * Trabaja sobre un TAD Lista de Estudiantes.
 */
public class SistemaNotas {
    private final ListaTDA<Estudiante> estudiantes;

    public SistemaNotas() {
        this.estudiantes = new ListaEnlazada<>();
    }

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) throw new IllegalArgumentException("Estudiante nulo.");
        estudiantes.agregar(estudiante);
    }

    public void mostrarTodos() {
        System.out.println("--- Listado de estudiantes ---");
        for (int i = 0; i < estudiantes.tamanio(); i++) {
            Estudiante est = estudiantes.obtener(i);
            System.out.println("\nEstudiante: " + est.getNombre());
            ListaTDA<Tupla<String, Double>> notas = est.getNotas();
            for (int j = 0; j < notas.tamanio(); j++) {
                Tupla<String, Double> t = notas.obtener(j);
                System.out.printf("  %-15s -> %.1f%n", t.getPrimero(), t.getSegundo());
            }
        }
        System.out.println();
    }

    public void mostrarPromedios() {
        System.out.println("--- Promedios ---");
        for (int i = 0; i < estudiantes.tamanio(); i++) {
            Estudiante est = estudiantes.obtener(i);
            double promedio = calcularPromedio(est);
            System.out.printf("%-15s -> %.2f%n", est.getNombre(), promedio);
        }
        System.out.println();
    }

    public void mostrarMejorEnMateria(String materia) {
        System.out.println("--- Mejor nota en " + materia + " ---");
        Estudiante mejor = null;
        double mejorNota = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < estudiantes.tamanio(); i++) {
            Estudiante est = estudiantes.obtener(i);
            double nota = buscarNotaEnMateria(est, materia);
            if (nota > mejorNota) {
                mejorNota = nota;
                mejor = est;
            }
        }

        if (mejor != null && mejorNota != Double.NEGATIVE_INFINITY) {
            System.out.printf("%s con %.1f en %s%n%n", mejor.getNombre(), mejorNota, materia);
        } else {
            System.out.println("Nadie tiene nota en " + materia + "\n");
        }
    }

    public void mostrarAprobados(double notaMinima) {
        System.out.println("--- Aprobados (promedio >= " + notaMinima + ") ---");
        boolean hayAprobados = false;
        for (int i = 0; i < estudiantes.tamanio(); i++) {
            Estudiante est = estudiantes.obtener(i);
            double promedio = calcularPromedio(est);
            if (promedio >= notaMinima) {
                System.out.printf("  %-15s promedio: %.2f%n", est.getNombre(), promedio);
                hayAprobados = true;
            }
        }
        if (!hayAprobados) System.out.println("  Ninguno aprobado.");
        System.out.println();
    }

    public void mostrarOrdenadosPorPromedio() {
        System.out.println("--- Ranking por promedio (mayor a menor) ---");
        // Copiar referencias a un arreglo para ordenar (no se modifican los datos originales)
        int n = estudiantes.tamanio();
        Estudiante[] arr = new Estudiante[n];
        for (int i = 0; i < n; i++) arr[i] = estudiantes.obtener(i);

        // Bubble sort simple por promedio descendente
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (calcularPromedio(arr[j]) < calcularPromedio(arr[j + 1])) {
                    Estudiante temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        int posicion = 1;
        for (Estudiante est : arr) {
            System.out.printf("  %d. %-15s %.2f%n", posicion++, est.getNombre(), calcularPromedio(est));
        }
        System.out.println();
    }

    private double calcularPromedio(Estudiante est) {
        ListaTDA<Tupla<String, Double>> notas = est.getNotas();
        if (notas.estaVacia()) return 0;
        double suma = 0;
        for (int i = 0; i < notas.tamanio(); i++) {
            suma += notas.obtener(i).getSegundo();
        }
        return suma / notas.tamanio();
    }

    private double buscarNotaEnMateria(Estudiante est, String materia) {
        ListaTDA<Tupla<String, Double>> notas = est.getNotas();
        for (int i = 0; i < notas.tamanio(); i++) {
            Tupla<String, Double> t = notas.obtener(i);
            if (t.getPrimero().equalsIgnoreCase(materia)) {
                return t.getSegundo();
            }
        }
        return Double.NEGATIVE_INFINITY;
    }
}
