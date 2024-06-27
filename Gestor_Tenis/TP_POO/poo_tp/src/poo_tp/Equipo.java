package poo_tp;

import java.util.LinkedList;

public class Equipo {

    private String nombre;
    private String ciudad;
    private LinkedList<Jugador> jugadores;

   
    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.jugadores = new LinkedList<>();
    }

    
    
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    
    
    public void eliminarJugador(String nombre) {
        jugadores.removeIf(jugador -> jugador.getNombre().equalsIgnoreCase(nombre));
    }

    
    
    public Jugador buscarJugador(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    
    public int obtenerCantidadJugadores() {
        return jugadores.size();
    }

    
   
    public LinkedList<Jugador> obtenerListaJugadores() {
        return jugadores;
    }
    

   
    public String getNombre() {
        return nombre;
    }

  
    @Override
    public String toString() {
        return "- " + nombre + " de la ciudad " + ciudad;
    }

}
