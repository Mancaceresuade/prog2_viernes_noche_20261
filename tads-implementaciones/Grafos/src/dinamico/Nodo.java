package dinamico;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    String valor;
    List<Nodo> adyacentes; // Aquí reside la naturaleza dinámica

    public Nodo(String valor) {
        this.valor = valor;
        this.adyacentes = new ArrayList<>();
    }

    public void agregarArista(Nodo destino) {
        this.adyacentes.add(destino);
    }

    public String getValor() {
        return valor;
    }

    public List<Nodo> getAdyacentes() {
        return adyacentes;
    }
}
