package modelo;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Jugador {
    private String Nombre;
    private ArrayList<Terreno> pesos;

    public Jugador(String Nombre, ArrayList<Terreno> pesos) {
        this.Nombre = Nombre;
        this.pesos = pesos;
    }

    public String getNombre() {
        return Nombre;
    }

    @Override
    public String toString() {
        return "JUGADOR: \n" 
                + "Nombre: " + Nombre + "\n" 
                + "Terreno(Costos): " + pesos.toString();
    }
}
