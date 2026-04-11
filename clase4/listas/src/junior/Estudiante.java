package junior;

public class Estudiante {
    String nombre;
    Lista notas;

    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.notas = new Lista();
    }

    public String getNombre() {
        return nombre;
    }

    public Lista getNotas() {
        return notas;
    }

    public void agregarNota(String materia, double nota) {
        notas.agregar(new Tupla(materia, nota));
    }
}
