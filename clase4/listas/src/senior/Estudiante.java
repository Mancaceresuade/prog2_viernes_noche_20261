package senior;

public class Estudiante {
    private final String nombre;
    private final ListaTDA<Tupla<String, Double>> notas;

    public Estudiante(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede estar vacio.");
        }
        this.nombre = nombre;
        this.notas = new ListaEnlazada<>();
    }

    public void registrarNota(String materia, double nota) {
        if (nota < 0 || nota > 10) throw new IllegalArgumentException("Nota fuera de rango: " + nota);
        notas.agregar(new Tupla<>(materia, nota));
    }

    public String getNombre() {
        return nombre;
    }

    public ListaTDA<Tupla<String, Double>> getNotas() {
        return notas;
    }
}
