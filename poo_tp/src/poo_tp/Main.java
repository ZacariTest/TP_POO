package poo_tp;

import javax.swing.JOptionPane;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        GestorEquipos gestorEquipos = new GestorEquipos();

        Jugador jugador1 = new Jugador("Rafael Nadal", 35);
        Jugador jugador2 = new Jugador("Novak Djokovic", 34);
        Jugador jugador3 = new Jugador("Roger Federer", 39);
        Jugador jugador4 = new Jugador("Andy Murray", 34);

        Equipo equipo1 = new Equipo("Equipo A", "Ciudad A");
        equipo1.agregarJugador(jugador1);
        equipo1.agregarJugador(jugador2);

        Equipo equipo2 = new Equipo("Equipo B", "Ciudad B");
        equipo2.agregarJugador(jugador3);
        equipo2.agregarJugador(jugador4);

        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);

        while (true) {
            String[] opciones = {"Agregar Equipo", "Eliminar Equipo", "Buscar Equipo", "Mostrar Todos los Equipos", "Simular Partido", "Agregar Jugador", "Eliminar Jugador", "Salir"};
            int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestor de equipos de Tenis",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (eleccion) {
                case 0: // Agregar Equipo
                    String nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo:");
                    if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre del equipo no puede estar vacío.");
                        break;
                    }
                    String ciudadEquipo = JOptionPane.showInputDialog("Ingrese la ciudad del equipo:");
                    if (ciudadEquipo == null || ciudadEquipo.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La ciudad del equipo no puede estar vacía.");
                        break;
                    }
                    Equipo nuevoEquipo = new Equipo(nombreEquipo, ciudadEquipo);
                    gestorEquipos.agregarEquipo(nuevoEquipo);
                    JOptionPane.showMessageDialog(null, "Equipo agregado: " + nuevoEquipo);
                    break;

                 // Eliminar Equipo
                case 1: 
                    nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo a eliminar:");
                    gestorEquipos.eliminarEquipo(nombreEquipo);
                    JOptionPane.showMessageDialog(null, "Equipo eliminado: " + nombreEquipo);
                    break;

                 // Buscar Equipo
                case 2: 
                    nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo a buscar:");
                    Equipo equipoBuscado = gestorEquipos.buscarEquipo(nombreEquipo);
                    if (equipoBuscado != null) {
                        JOptionPane.showMessageDialog(null, "Equipo encontrado: " + equipoBuscado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado: " + nombreEquipo);
                    }
                    break;

                 // Mostrar Todos los Equipos
                case 3: 
                    LinkedList<Equipo> listaEquipos = gestorEquipos.obtenerListaEquipos();
                    StringBuilder equipos = new StringBuilder("Equipos:\n");
                    for (Equipo eq : listaEquipos) {
                        equipos.append(eq.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, equipos.toString());
                    break;

                case 4: // Simular Partido
                    String equipo1Nombre = JOptionPane.showInputDialog("Ingrese el nombre del primer equipo:");
                    String equipo2Nombre = JOptionPane.showInputDialog("Ingrese el nombre del segundo equipo:");
                    Equipo equipo1Encontrado = gestorEquipos.buscarEquipo(equipo1Nombre);
                    Equipo equipo2Encontrado = gestorEquipos.buscarEquipo(equipo2Nombre);

                    if (equipo1Encontrado != null && equipo2Encontrado != null) {
                        String[] resultado = gestorEquipos.jugarPartido(equipo1Encontrado, equipo2Encontrado);
                        JOptionPane.showMessageDialog(null, resultado[0]);
                        JOptionPane.showMessageDialog(null, "Resultado final: " + (resultado[1].equals("Empate") ? "Empate" : "Ganador: " + resultado[1]));
                    } else {
                        JOptionPane.showMessageDialog(null, "Uno o ambos equipos no fueron encontrados.");
                    }
                    break;

                case 5: // Agregar Jugador
                    nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo al que desea agregar un jugador:");
                    equipoBuscado = gestorEquipos.buscarEquipo(nombreEquipo);
                    if (equipoBuscado != null) {
                        String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador:");
                        String edadStr = JOptionPane.showInputDialog("Ingrese la edad del jugador:");
                        int edadJugador;
                        try {
                            edadJugador = Integer.parseInt(edadStr);
                            if (edadJugador <= 0) {
                                JOptionPane.showMessageDialog(null, "La edad debe ser un número positivo.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.");
                            break;
                        }
                        Jugador nuevoJugador = new Jugador(nombreJugador, edadJugador);
                        equipoBuscado.agregarJugador(nuevoJugador);
                        JOptionPane.showMessageDialog(null, "Jugador agregado: " + nuevoJugador);
                    } else {
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado: " + nombreEquipo);
                    }
                    break;

                case 6: // Eliminar Jugador
                    nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo del que desea eliminar un jugador:");
                    equipoBuscado = gestorEquipos.buscarEquipo(nombreEquipo);
                    if (equipoBuscado != null) {
                        String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador a eliminar:");
                        equipoBuscado.eliminarJugador(nombreJugador);
                        JOptionPane.showMessageDialog(null, "Jugador eliminado: " + nombreJugador);
                    } else {
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado: " + nombreEquipo);
                    }
                    break;

                case 7: // Salir
                    JOptionPane.showMessageDialog(null, "¡Que tengas un buen dia!");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }
}


