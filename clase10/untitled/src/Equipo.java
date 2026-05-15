public class Equipo {
    String nombre;
    int    rating;
    public Equipo(String nombre, int rating) {
        this.nombre = nombre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", rating=" + rating +
                '}';
    }
}
