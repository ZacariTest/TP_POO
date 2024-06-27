package poo_tp;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int setsGanadosEquipo1;
    private int setsGanadosEquipo2;
    private String fase;

    public Partido(Equipo equipo1, Equipo equipo2, String fase) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fase = fase;
    }

    
    
    public Equipo getEquipo1() {
        return equipo1;
    }

    
    public Equipo getEquipo2() {
        return equipo2;
    }
    
    // Goles=Sets (Goles1)

    public int getSetsGanadosEquipo1() {
        return setsGanadosEquipo1;
    }

    public void setSetsGanadosEquipo1(int setsGanadosEquipo1) {
        this.setsGanadosEquipo1 = setsGanadosEquipo1;
    }

    // Goles=Sets (Goles2)
    
    public int getSetsGanadosEquipo2() {
        return setsGanadosEquipo2;
    }

    public void setSetsGanadosEquipo2(int setsGanadosEquipo2) {
        this.setsGanadosEquipo2 = setsGanadosEquipo2;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    @Override
    public String toString() {
        return " Partido: " + equipo1.getNombre() + " vs " + equipo2.getNombre() + " | Fase: " + fase + 
               " | Resultado: " + setsGanadosEquipo1 + "-" + setsGanadosEquipo2;
    }
}
