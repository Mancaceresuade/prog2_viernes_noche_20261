package senior;

public class MainSenior {

    public static void main(String[] args) {
        System.out.println("=== VERSION SENIOR ===\n");

        SistemaNotas sistema = new SistemaNotas();
        cargarDatos(sistema);

        sistema.mostrarTodos();
        sistema.mostrarPromedios();
        sistema.mostrarMejorEnMateria("Matematicas");
        sistema.mostrarAprobados(6.0);
        sistema.mostrarOrdenadosPorPromedio();
    }

    private static void cargarDatos(SistemaNotas sistema) {
        Estudiante juan = new Estudiante("Juan Perez");
        juan.registrarNota("Matematicas", 8.5);
        juan.registrarNota("Historia", 7.0);
        juan.registrarNota("Fisica", 9.0);
        sistema.registrarEstudiante(juan);

        Estudiante maria = new Estudiante("Maria Lopez");
        maria.registrarNota("Matematicas", 6.0);
        maria.registrarNota("Historia", 9.5);
        maria.registrarNota("Fisica", 7.5);
        sistema.registrarEstudiante(maria);

        Estudiante carlos = new Estudiante("Carlos Gomez");
        carlos.registrarNota("Matematicas", 4.0);
        carlos.registrarNota("Historia", 5.0);
        carlos.registrarNota("Fisica", 6.0);
        sistema.registrarEstudiante(carlos);
    }
}
