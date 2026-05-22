public class Equipo implements Comparable<Equipo> {
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

    @Override
    public int compareTo(Equipo o) {
        int aux = 0;
        if(this.rating > o.rating) {
            aux = 1;
        } else {
            if(this.rating == o.rating) {
                aux = 0;
            } else {
                aux = -1;
            }
        }

        return aux ;
    }

    public String toJson() {
        return "{\"nombre\":\"" + nombre + "\", \"rating\":" + rating + "}";
    }
}
