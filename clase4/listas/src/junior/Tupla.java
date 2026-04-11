package junior;

public class Tupla {
    String materia;
    double nota;

    public Tupla(String materia, double nota) {
        this.materia = materia;
        this.nota = nota;
    }

    public String getMateria() {
        return materia;
    }

    public double getNota() {
        return nota;
    }

    public String toString() {
        return materia + ": " + nota;
    }
}
