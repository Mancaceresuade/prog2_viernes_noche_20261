package semisemior;

public class Tupla implements TuplaTDA {
    private final String materia;
    private final double nota;

    public Tupla(String materia, double nota) {
        if (materia == null || materia.isEmpty()) {
            throw new IllegalArgumentException("La materia no puede estar vacia.");
        }
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }
        this.materia = materia;
        this.nota = nota;
    }

    @Override
    public String getMateria() {
        return materia;
    }

    @Override
    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return String.format("(%s, %.1f)", materia, nota);
    }
}
