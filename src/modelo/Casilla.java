package modelo;

import java.util.ArrayList;

public class Casilla {
    private boolean usado;
    private boolean tieneNiebla;
    private ArrayList<Integer> noVisitas;
    private Coordenada coordenada;
    private Terreno terreno;
    
    public Casilla(boolean usado, boolean tieneNiebla, Coordenada coordenada, Terreno terreno){
        
        this.usado = usado;
        this.tieneNiebla = tieneNiebla;
        this.coordenada = coordenada;
        this.terreno = terreno;
        this.noVisitas = new ArrayList<>();
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public boolean isUsado() {
        return usado;
    }

    public boolean getTieneNiebla() {
        return tieneNiebla;
    }

    public ArrayList<Integer> getNoVisitas() {
        return noVisitas;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
    
    public void setCoordenada(Coordenada c)
    {
        this.coordenada = c;
    }
    
    public String transformaCoordenadaX(int coordenadaX)
        {
            String cX = new String("ERROR");
            if(coordenadaX == 0)
                cX = "A";
            if(coordenadaX == 1)
                cX = "B";
            if(coordenadaX == 2)
                cX = "C";
            if(coordenadaX == 3)
                cX = "D";
            if(coordenadaX == 4)
                cX = "E";
            if(coordenadaX == 5)
                cX = "F";
            if(coordenadaX == 6)
                cX = "G";
            if(coordenadaX == 7)
                cX = "H";
            if(coordenadaX == 8)
                cX = "I";
            if(coordenadaX == 9)
                cX = "J";
            if(coordenadaX == 10)
                cX = "K";
            if(coordenadaX == 11)
                cX = "L";
            if(coordenadaX == 12)
                cX = "M";
            if(coordenadaX == 13)
                cX = "N";
            if(coordenadaX == 14)
                cX = "O";
            return cX;
        }

    @Override
    public String toString() {
        return "CASILLA:\n"  
                +"Terreno: " + getTerreno().getNombreTerreno() + " " +getTerreno().getCosto() +  "\n"
                +"Usado: " + usado + "\n" 
                +"Niebla: " + tieneNiebla + "\n" 
                +"NoVisitas: " + noVisitas.toString() + "\n"
                +"Coordenada: (" + transformaCoordenadaX(coordenada.getCoordenadaJ())+", " 
                + (int)(coordenada.getCoordenadaI() + 1) + ")";
    }

    public void setTieneNiebla(boolean tieneNiebla) {
	this.tieneNiebla = tieneNiebla;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }
    
    public void setUsado(boolean bool) {
        this.usado = bool;
    }
    
    public void setNoVisitas(int numero)
    {
        noVisitas.add(numero);
    }

    public void reiniciaNoVisitas() {
        noVisitas.clear();	
    }
}