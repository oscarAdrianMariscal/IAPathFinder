package modelo;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Jugador {
    private String Nombre;
    private ArrayList<Terreno> pesos;
    ImageIcon imagen;

    public Jugador(String Nombre, ArrayList<Terreno> pesos, ImageIcon img) {
        this.Nombre = Nombre;
        this.pesos = pesos;
        imagen = img;
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

	public ArrayList<Terreno> getPesos() {
		return pesos;
	}

	public void setPesos(ArrayList<Terreno> pesos) {
		this.pesos = pesos;
	}
}
