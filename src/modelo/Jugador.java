package modelo;

import java.util.ArrayList;

public class Jugador {
    private String Nombre;
    //IMAGEN
    private ArrayList<Terreno> pesos;
    private Terreno[] terrenoPermitido;

    public Jugador(String Nombre, ArrayList<Terreno> pesos) {
        this.Nombre = Nombre;
        this.pesos = pesos;
    }

    public String getNombre() {
        return Nombre;
    }

    public Terreno[] getTerrenoPermitido() {
        return terrenoPermitido;
    }
    
    public String terrPermitido(Terreno[] terrenoPermitido)
    {
        String terrenos = new String();
        int i=0;
        for(i=0; i<terrenoPermitido.length; i++)
        {
            terrenos = terrenos + ", " + terrenoPermitido[i];
        }
        return terrenos;
    }

    @Override
    public String toString() {
        return "JUGADOR: \n" 
                + "Nombre: " + Nombre + "\n" 
                + "T. Permitidos: " + terrPermitido(terrenoPermitido);
    }
}
