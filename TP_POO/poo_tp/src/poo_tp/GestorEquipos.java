package poo_tp;

import java.util.LinkedList;
import java.util.Random;

public class GestorEquipos {
    private LinkedList<Equipo> listaEquipos;

    public GestorEquipos() {
        this.listaEquipos = new LinkedList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        listaEquipos.add(equipo);
    }

    public void eliminarEquipo(String nombreEquipo) {
        listaEquipos.removeIf(equipo -> equipo.getNombre().equalsIgnoreCase(nombreEquipo));
    }

    public Equipo buscarEquipo(String nombreEquipo) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    public int obtenerCantidadEquipos() {
        return listaEquipos.size();
    }

    public LinkedList<Equipo> obtenerListaEquipos() {
        return listaEquipos;
    }

    public String[] jugarPartido(Equipo equipo1, Equipo equipo2) {
        StringBuilder resultadoPartido = new StringBuilder();
        resultadoPartido.append("Inicia el partido entre ").append(equipo1.getNombre())
                .append(" y ").append(equipo2.getNombre()).append("\n");

        int puntajeEquipo1 = 0;
        int puntajeEquipo2 = 0;

        for (int set = 1; set <= 5; set++) {
            resultadoPartido.append("- Set ").append(set).append(":\n");
            int resultadoSet = jugarSet();
            if (resultadoSet > 0) {
                puntajeEquipo1++;
            } else if (resultadoSet < 0) {
                puntajeEquipo2++;
            }
            resultadoPartido.append("Marcador:\n");
            resultadoPartido.append(equipo1.getNombre()).append(": ").append(puntajeEquipo1).append("\n");
            resultadoPartido.append(equipo2.getNombre()).append(": ").append(puntajeEquipo2).append("\n");
        }

        resultadoPartido.append("Final del partido:\n");
        String ganador;
        if (puntajeEquipo1 > puntajeEquipo2) {
            resultadoPartido.append("¡El equipo ").append(equipo1.getNombre()).append(" gana el partido!\n");
            ganador = equipo1.getNombre();
        } else if (puntajeEquipo1 < puntajeEquipo2) {
            resultadoPartido.append("¡El equipo ").append(equipo2.getNombre()).append(" gana el partido!\n");
            ganador = equipo2.getNombre();
        } else {
            resultadoPartido.append("¡El partido termina en empate!\n");
            ganador = "Empate";
        }

        Jugador mejorJugadorEquipo1 = seleccionarMejorJugador(equipo1);
        Jugador mejorJugadorEquipo2 = seleccionarMejorJugador(equipo2);

        resultadoPartido.append("Mejor jugador del equipo ").append(equipo1.getNombre())
                .append(": ").append(mejorJugadorEquipo1.getNombre()).append("\n");
        resultadoPartido.append("Mejor jugador del equipo ").append(equipo2.getNombre())
                .append(": ").append(mejorJugadorEquipo2.getNombre()).append("\n");

        return new String[]{resultadoPartido.toString(), ganador};
    }

    private int jugarSet() {
        Random random = new Random();
        return random.nextInt(3) - 1;
    }

    private Jugador seleccionarMejorJugador(Equipo equipo) {
        Random random = new Random();
        LinkedList<Jugador> jugadores = equipo.obtenerListaJugadores();
        return jugadores.get(random.nextInt(jugadores.size()));
    }
}

