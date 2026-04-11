package junior;

public class MainJunior {

    // Lista de estudiantes como arreglo fijo
    static Estudiante[] estudiantes = new Estudiante[5];
    static int cantEstudiantes = 0;

    public static void main(String[] args) {
        System.out.println("=== VERSION JUNIOR ===\n");

        // Cargar datos
        Estudiante e1 = new Estudiante("Juan Perez");
        e1.agregarNota("Matematicas", 8.5);
        e1.agregarNota("Historia", 7.0);
        e1.agregarNota("Fisica", 9.0);
        estudiantes[cantEstudiantes] = e1;
        cantEstudiantes++;

        Estudiante e2 = new Estudiante("Maria Lopez");
        e2.agregarNota("Matematicas", 6.0);
        e2.agregarNota("Historia", 9.5);
        e2.agregarNota("Fisica", 7.5);
        estudiantes[cantEstudiantes] = e2;
        cantEstudiantes++;

        Estudiante e3 = new Estudiante("Carlos Gomez");
        e3.agregarNota("Matematicas", 4.0);
        e3.agregarNota("Historia", 5.0);
        e3.agregarNota("Fisica", 6.0);
        estudiantes[cantEstudiantes] = e3;
        cantEstudiantes++;

        // Mostrar todos los estudiantes
        System.out.println("--- Notas de todos los estudiantes ---");
        for (int i = 0; i < cantEstudiantes; i++) {
            Estudiante est = estudiantes[i];
            System.out.println("\nEstudiante: " + est.getNombre());
            Lista notas = est.getNotas();
            for (int j = 0; j < notas.getCantidad(); j++) {
                Tupla t = notas.obtener(j);
                System.out.println("  " + t.getMateria() + " -> " + t.getNota());
            }
        }

        // Calcular promedio de cada estudiante
        System.out.println("\n--- Promedios ---");
        for (int i = 0; i < cantEstudiantes; i++) {
            Estudiante est = estudiantes[i];
            Lista notas = est.getNotas();
            double suma = 0;
            for (int j = 0; j < notas.getCantidad(); j++) {
                suma += notas.obtener(j).getNota();
            }
            double promedio = suma / notas.getCantidad();
            System.out.printf("%s: promedio = %.2f%n", est.getNombre(), promedio);
        }

        // Buscar mejor nota de una materia especifica
        String materiaFiltro = "Matematicas";
        System.out.println("\n--- Mejor nota en " + materiaFiltro + " ---");
        String mejorEstudiante = "";
        double mejorNota = -1;
        for (int i = 0; i < cantEstudiantes; i++) {
            Estudiante est = estudiantes[i];
            Lista notas = est.getNotas();
            for (int j = 0; j < notas.getCantidad(); j++) {
                Tupla t = notas.obtener(j);
                if (t.getMateria().equals(materiaFiltro) && t.getNota() > mejorNota) {
                    mejorNota = t.getNota();
                    mejorEstudiante = est.getNombre();
                }
            }
        }
        System.out.println("Mejor en " + materiaFiltro + ": " + mejorEstudiante + " con " + mejorNota);
    }
}
