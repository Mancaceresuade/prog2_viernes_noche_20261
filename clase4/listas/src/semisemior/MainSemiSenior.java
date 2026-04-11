package semisemior;

public class MainSemiSenior {

    public static void main(String[] args) {
        System.out.println("=== VERSION SEMI-SENIOR ===\n");

        Estudiante[] estudiantes = cargarEstudiantes();

        mostrarTodos(estudiantes);
        mostrarPromedios(estudiantes);
        mostrarMejorEnMateria(estudiantes, "Matematicas");
        mostrarAprobados(estudiantes, 6.0);
    }

    private static Estudiante[] cargarEstudiantes() {
        Estudiante[] lista = new Estudiante[3];

        lista[0] = new Estudiante("Juan Perez");
        lista[0].agregarNota("Matematicas", 8.5);
        lista[0].agregarNota("Historia", 7.0);
        lista[0].agregarNota("Fisica", 9.0);

        lista[1] = new Estudiante("Maria Lopez");
        lista[1].agregarNota("Matematicas", 6.0);
        lista[1].agregarNota("Historia", 9.5);
        lista[1].agregarNota("Fisica", 7.5);

        lista[2] = new Estudiante("Carlos Gomez");
        lista[2].agregarNota("Matematicas", 4.0);
        lista[2].agregarNota("Historia", 5.0);
        lista[2].agregarNota("Fisica", 6.0);

        return lista;
    }

    private static void mostrarTodos(Estudiante[] estudiantes) {
        System.out.println("--- Notas de todos los estudiantes ---");
        for (Estudiante est : estudiantes) {
            System.out.println(est);
            System.out.println();
        }
    }

    private static void mostrarPromedios(Estudiante[] estudiantes) {
        System.out.println("--- Promedios ---");
        for (Estudiante est : estudiantes) {
            System.out.printf("%-15s -> %.2f%n", est.getNombre(), est.calcularPromedio());
        }
        System.out.println();
    }

    private static void mostrarMejorEnMateria(Estudiante[] estudiantes, String materia) {
        System.out.println("--- Mejor nota en " + materia + " ---");
        Estudiante mejor = null;
        double mejorNota = -1;
        for (Estudiante est : estudiantes) {
            double nota = est.obtenerNotaEnMateria(materia);
            if (nota > mejorNota) {
                mejorNota = nota;
                mejor = est;
            }
        }
        if (mejor != null) {
            System.out.printf("%s con %.1f%n%n", mejor.getNombre(), mejorNota);
        }
    }

    private static void mostrarAprobados(Estudiante[] estudiantes, double notaMinima) {
        System.out.println("--- Estudiantes aprobados (promedio >= " + notaMinima + ") ---");
        for (Estudiante est : estudiantes) {
            if (est.calcularPromedio() >= notaMinima) {
                System.out.printf("  %s (%.2f)%n", est.getNombre(), est.calcularPromedio());
            }
        }
    }
}
