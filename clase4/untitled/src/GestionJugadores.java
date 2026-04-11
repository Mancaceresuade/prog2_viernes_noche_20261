import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionJugadores {

    public void procesar() {

        List<String> jugadoresBoca = new ArrayList<>();
        cargarJugadores(jugadoresBoca);
            System.out.println(jugadoresBoca);
        Map<String,Integer> jugadoresPorNombre = new HashMap<>();
            for (String jugador: jugadoresBoca) {
            if(jugadoresPorNombre.get(jugador)==null) {
                jugadoresPorNombre.put(jugador,0);
            }
            jugadoresPorNombre.put(jugador,jugadoresPorNombre.get(jugador)+1);
        }
            for (String jugador: jugadoresPorNombre.keySet()) {
            System.out.println(jugador + " " + jugadoresPorNombre.get(jugador));
        }
    }
    private static void cargarJugadores(List<String> jugadoresBoca) {
        jugadoresBoca.add("Marchesin");
        jugadoresBoca.add("Marchesin");
        jugadoresBoca.add("Costas");
        jugadoresBoca.add("Paredes");
        jugadoresBoca.add("Blanco");
        jugadoresBoca.add("Janson");
    }

}
