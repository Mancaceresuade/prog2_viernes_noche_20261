package semisemior;

public class Estudiante {
    private final String nombre;
    private final ListaTDA notas;

    public Estudiante(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre;
        this.notas = new ListaDinamica();
    }

    public void agregarNota(String materia, double nota) {
        notas.agregar(new Tupla(materia, nota));
    }

    public double calcularPromedio() {
        if (notas.estaVacia()) return 0;
        double suma = 0;
        for (int i = 0; i < notas.tamanio(); i++) {
            suma += notas.obtener(i).getNota();
        }
        return suma / notas.tamanio();
    }

    public double obtenerNotaEnMateria(String materia) {
        for (int i = 0; i < notas.tamanio(); i++) {
            TuplaTDA t = notas.obtener(i);
            if (t.getMateria().equalsIgnoreCase(materia)) {
                return t.getNota();
            }
        }
        return -1;
    }

    public String getNombre() {
        return nombre;
    }

    public ListaTDA getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiante: ").append(nombre).append("\n");
        for (int i = 0; i < notas.tamanio(); i++) {
            sb.append("  ").append(notas.obtener(i)).append("\n");
        }
        sb.append(String.format("  Promedio: %.2f", calcularPromedio()));
        return sb.toString();
    }
}
