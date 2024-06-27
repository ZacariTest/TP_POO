package poo_tp;

import java.util.Random;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        GestorEquipos gestorEquipos = new GestorEquipos();
        Random random = new Random();

        
        String[] nombresEquipos = {"Equipo A", "Equipo B", "Equipo C", "Equipo D", "Equipo E", "Equipo F", "Equipo G", "Equipo H"};

        for (String nombre : nombresEquipos) {
            Equipo equipo = new Equipo(nombre, "Ciudad de " + nombre);
            gestorEquipos.agregarEquipo(equipo);
        }

        
        String[] nombresJugadores = {
            "Rafael Nadal", "Novak Djokovic", "Roger Federer",
            "Andy Murray", "Serena Williams", "Venus Williams",
            "Maria Sharapova", "Simona Halep", "Naomi Osaka",
            "Ashleigh Barty", "Dominic Thiem", "Daniil Medvedev",
            "Alexander Zverev", "Stefanos Tsitsipas", "Bianca Andreescu",
            "Iga Swiatek", "Petra Kvitova", "Garbiñe Muguruza",
            "Caroline Wozniacki", "Angelique Kerber"
        };

        
        for (Equipo equipo : gestorEquipos.obtenerListaEquipos()) {
            for (int i = 0; i < 5; i++) {
                equipo.agregarJugador(new Jugador(nombresJugadores[random.nextInt(nombresJugadores.length)], random.nextInt(23) + 18));
            }
        }

        boolean simular = false;

        while (true) {
            String[] opciones1 = {"Agregar Equipo", "Eliminar Equipo", "Buscar Equipo", "Ver Todos los Equipos y pasar a simular", "Salir"};
            int eleccion1 = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestor de equipos de Tenis",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones1, opciones1[0]);

            if (eleccion1 == JOptionPane.CLOSED_OPTION) break;

            switch (eleccion1) {
                case 0: // Agregar Equipo
                    String nombreEquipoNuevo = JOptionPane.showInputDialog("Ingrese el nombre del equipo:");
                    String ciudadEquipo = JOptionPane.showInputDialog("Ingrese la ciudad del equipo:");
                    if (nombreEquipoNuevo != null && !nombreEquipoNuevo.trim().isEmpty() &&
                        ciudadEquipo != null && !ciudadEquipo.trim().isEmpty()) {
                        Equipo nuevoEquipo = new Equipo(nombreEquipoNuevo, ciudadEquipo);
                        boolean agregado = gestorEquipos.agregarEquipo(nuevoEquipo);
                        if (agregado) {
                            JOptionPane.showMessageDialog(null, "Equipo agregado: " + nuevoEquipo + ". - (Recuerda añadir al menos un jugador)");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo agregar el equipo. Verifique si ya existe o si los datos son válidos.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos de equipo inválidos. Intentalo nuevamente.");
                    }
                    break;

                case 1: // Eliminar Equipo
                    String nombreEquipoEliminar = JOptionPane.showInputDialog("Ingrese el nombre del equipo a eliminar:");
                    if (nombreEquipoEliminar != null && !nombreEquipoEliminar.trim().isEmpty()) {
                        boolean eliminado = gestorEquipos.eliminarEquipo(nombreEquipoEliminar);
                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Equipo eliminado: " + nombreEquipoEliminar);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el equipo: " + nombreEquipoEliminar);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de equipo inválido. Intente nuevamente.");
                    }
                    break;

                case 2: // Buscar Equipo
                    String nombreEquipoBuscar = JOptionPane.showInputDialog("Ingrese el nombre del equipo a buscar:");
                    if (nombreEquipoBuscar != null && !nombreEquipoBuscar.trim().isEmpty()) {
                        Equipo equipoBuscado = gestorEquipos.buscarEquipo(nombreEquipoBuscar);
                        if (equipoBuscado != null) {
                            JOptionPane.showMessageDialog(null, "Equipo encontrado: " + equipoBuscado);
                        } else {
                            JOptionPane.showMessageDialog(null, "Equipo no encontrado: " + nombreEquipoBuscar);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de equipo inválido. Intente nuevamente.");
                    }
                    break;

                case 3: // Ver Todos los Equipos y pasar a simular
                    StringBuilder equipos = new StringBuilder("Equipos:\n");
                    for (Equipo eq : gestorEquipos.obtenerListaEquipos()) {
                        equipos.append(eq.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, equipos.toString());
                    simular = true; 
                    break;

                case 4: // Salir
                    JOptionPane.showMessageDialog(null, "¡Que tengas un buen día!");
                    System.exit(0);
                    break;
            }

            // Opciones 2 del menu.
            if (simular) {
                while (true) {
                    String[] opciones2 = {"Simular Partido", "Agregar Jugador", "Eliminar Jugador", "Mostrar Lista de Jugadores", "Simular Torneo", "Salir"};
                    int eleccion2 = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestor de equipos de Tenis",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones2, opciones2[0]);

                    if (eleccion2 == JOptionPane.CLOSED_OPTION) break;

                    switch (eleccion2) {
                        case 0: // Simular Partido
                            String equipo1Nombre = JOptionPane.showInputDialog("Ingrese el nombre del primer equipo:");
                            String equipo2Nombre = JOptionPane.showInputDialog("Ingrese el nombre del segundo equipo:");
                            String fase = JOptionPane.showInputDialog("Ingrese la fase del torneo (ej. 'Cuartos de final', 'Semifinal', 'Final'):");
                            Equipo equipo1 = gestorEquipos.buscarEquipo(equipo1Nombre);
                            Equipo equipo2 = gestorEquipos.buscarEquipo(equipo2Nombre);

                            if (equipo1 != null && equipo2 != null) {
                                String[] opcionesApuesta = {equipo1Nombre, equipo2Nombre, "Empate"};
                                int apuesta = JOptionPane.showOptionDialog(null, "¿A quién le apuestas? o ¿Esperas un empate?", "Apuesta",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesApuesta, opcionesApuesta[0]);

                                Partido partido = gestorEquipos.jugarPartido(equipo1, equipo2, fase);
                                String resultadoFinal = partido.getSetsGanadosEquipo1() == partido.getSetsGanadosEquipo2() ? "Empate" :
                                                        partido.getSetsGanadosEquipo1() > partido.getSetsGanadosEquipo2() ? equipo1Nombre : equipo2Nombre;
                                JOptionPane.showMessageDialog(null, "Resultado final: " + resultadoFinal);

                                if ((apuesta == 0 && resultadoFinal.equals(equipo1Nombre)) ||
                                    (apuesta == 1 && resultadoFinal.equals(equipo2Nombre)) ||
                                    (apuesta == 2 && resultadoFinal.equals("Empate"))) {
                                    JOptionPane.showMessageDialog(null, "¡Ganaste la apuesta!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Perdiste la apuesta.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Uno o ambos equipos no fueron encontrados.");
                            }
                            break;

                        case 1: // Agregar Jugador
                            String equipoNombre = JOptionPane.showInputDialog("Ingrese el nombre del equipo al que desea agregar un jugador:");
                            Equipo equipo = gestorEquipos.buscarEquipo(equipoNombre);
                            if (equipo != null) {
                                String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador:");
                                String edadStr = JOptionPane.showInputDialog("Ingrese la edad del jugador:");
                                try {
                                    int edad = Integer.parseInt(edadStr);
                                    Jugador nuevoJugador = new Jugador(nombreJugador, edad);
                                    boolean jugadorAgregado = gestorEquipos.agregarJugadorAEquipo(equipoNombre, nuevoJugador);
                                    if (jugadorAgregado) {
                                        JOptionPane.showMessageDialog(null, "Jugador agregado: " + nuevoJugador);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo agregar el jugador. Verifique los datos.");
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Edad inválida. Intente nuevamente.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                            }
                            break;

                        case 2: // Eliminar Jugador
                            String equipoNombreEliminar = JOptionPane.showInputDialog("Ingrese el nombre del equipo del cual desea eliminar un jugador:");
                            Equipo equipoEliminar = gestorEquipos.buscarEquipo(equipoNombreEliminar);
                            if (equipoEliminar != null) {
                                String nombreJugadorEliminar = JOptionPane.showInputDialog("Ingrese el nombre del jugador:");
                                boolean jugadorEliminado = gestorEquipos.eliminarJugadorDeEquipo(equipoNombreEliminar, nombreJugadorEliminar);
                                if (jugadorEliminado) {
                                    JOptionPane.showMessageDialog(null, "Jugador eliminado: " + nombreJugadorEliminar);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se encontró el jugador. Verifique los datos.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                            }
                            break;

                        case 3: // Mostrar Lista de Jugadores
                            String equipoNombreMostrar = JOptionPane.showInputDialog("Ingrese el nombre del equipo para mostrar su lista de jugadores:");
                            Equipo equipoMostrar = gestorEquipos.buscarEquipo(equipoNombreMostrar);
                            if (equipoMostrar != null) {
                                StringBuilder jugadores = new StringBuilder("Jugadores del equipo " + equipoNombreMostrar + ":\n");
                                for (Jugador jugador : equipoMostrar.obtenerListaJugadores()) {
                                    jugadores.append(jugador.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, jugadores.toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                            }
                            break;

                        case 4: // Simular Torneo
                            gestorEquipos.simularTorneo();
                            break;

                        case 5: // Salir
                            JOptionPane.showMessageDialog(null, "¡Que tengas un buen día!");
                            System.exit(0);
                            break;
                    }
                }
            }
        }
    }
}
