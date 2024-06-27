package poo_tp;

import java.util.LinkedList;
import java.util.Random;
import javax.swing.JOptionPane;

public class GestorEquipos {
    private LinkedList<Equipo> listaEquipos;

    public GestorEquipos() {
        this.listaEquipos = new LinkedList<>();
    }

    public boolean agregarEquipo(Equipo equipo) {
        if (equipo == null || equipo.getNombre() == null || equipo.getNombre().trim().isEmpty()) {
            return false;
        }
        if (buscarEquipo(equipo.getNombre()) != null) {
            return false; 
        }
        listaEquipos.add(equipo);
        return true;
    }

    public boolean eliminarEquipo(String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            return false;
        }
        return listaEquipos.removeIf(equipo -> equipo.getNombre().equalsIgnoreCase(nombreEquipo));
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

    public boolean agregarJugadorAEquipo(String nombreEquipo, Jugador jugador) {
        if (jugador == null || jugador.getNombre() == null || jugador.getNombre().trim().isEmpty() || jugador.getEdad() <= 0) {
            return false;
        }
        Equipo equipo = buscarEquipo(nombreEquipo);
        if (equipo == null) {
            return false;
        }
        equipo.agregarJugador(jugador);
        return true;
    }

    public boolean eliminarJugadorDeEquipo(String nombreEquipo, String nombreJugador) {
        Equipo equipo = buscarEquipo(nombreEquipo);
        if (equipo == null || nombreJugador == null || nombreJugador.trim().isEmpty()) {
            return false;
        }
        equipo.eliminarJugador(nombreJugador);
        return true;
    }

    public Partido jugarPartido(Equipo equipo1, Equipo equipo2, String fase) {
        Partido partido = new Partido(equipo1, equipo2, fase);

        int puntajeEquipo1 = 0;
        int puntajeEquipo2 = 0;

        for (int set = 1; set <= 5; set++) {
            int resultadoSet = jugarSet();
            if (resultadoSet > 0) {
                puntajeEquipo1++;
            } else if (resultadoSet < 0) {
                puntajeEquipo2++;
            }
        }

        partido.setSetsGanadosEquipo1(puntajeEquipo1);
        partido.setSetsGanadosEquipo2(puntajeEquipo2);

        StringBuilder resultadoPartido = new StringBuilder();
        resultadoPartido.append("Inicia el partido entre ").append(equipo1.getNombre())
                .append(" y ").append(equipo2.getNombre()).append("\n")
                .append("Resultado final: ").append(partido).append("\n");

        JOptionPane.showMessageDialog(null, resultadoPartido.toString(), "Resultado del Partido", JOptionPane.INFORMATION_MESSAGE);

        return partido;
    }

    private int jugarSet() {
        Random random = new Random();
        return random.nextInt(3) - 1;
    }

    public void simularTorneo() {
        LinkedList<Equipo> equiposParticipantes = new LinkedList<>(listaEquipos);

        int totalEquipos = equiposParticipantes.size();
        while (equiposParticipantes.size() > 1) {
            LinkedList<Equipo> equiposGanadores = new LinkedList<>();
            String fase = determinarFase(equiposParticipantes.size(), totalEquipos);
            
            for (int i = 0; i < equiposParticipantes.size(); i += 2) {
                if (i + 1 < equiposParticipantes.size()) {
                    Equipo equipo1 = equiposParticipantes.get(i);
                    Equipo equipo2 = equiposParticipantes.get(i + 1);
                    Partido partido = jugarPartido(equipo1, equipo2, fase);

                    Equipo ganador;
                    if (partido.getSetsGanadosEquipo1() > partido.getSetsGanadosEquipo2()) {
                        ganador = partido.getEquipo1();
                    } else if (partido.getSetsGanadosEquipo1() < partido.getSetsGanadosEquipo2()) {
                        ganador = partido.getEquipo2();
                    } else {
                        ganador = new Random().nextBoolean() ? equipo1 : equipo2;
                    }
                    equiposGanadores.add(ganador);
                } else {
                    equiposGanadores.add(equiposParticipantes.get(i));
                }
            }
            equiposParticipantes = equiposGanadores;
        }

        Equipo campeon = equiposParticipantes.getFirst();
        Jugador mejorJugador = seleccionarMejorJugador(campeon);
        String resultadoFinal = "¡El campeón del torneo es " + campeon.getNombre() +
                                "!\nMejor jugador: " + mejorJugador.getNombre() + "\n";
        JOptionPane.showMessageDialog(null, resultadoFinal, "Resultado del Torneo", JOptionPane.INFORMATION_MESSAGE);
    }

    private String determinarFase(int equiposRestantes, int totalEquipos) {
        switch (equiposRestantes) {
            case 2:
                return "Final";
            case 4:
                return "Semifinales";
            case 8:
                return "Cuartos de Final";
            case 16:
                return "Octavos de Final";
            default:
                return "Fase de Grupos";
        }
    }

    private Jugador seleccionarMejorJugador(Equipo equipo) {
        Random random = new Random();
        LinkedList<Jugador> jugadores = equipo.obtenerListaJugadores();
        return jugadores.get(random.nextInt(jugadores.size()));
    }
}
