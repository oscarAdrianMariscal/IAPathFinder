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

    public boolean isTieneNiebla() {
        return tieneNiebla;
    }

    public ArrayList<Integer> getNoVisitas() {
        return noVisitas;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
    
    private String transformaCoordenadaJ(int coordenadaI)
    {
        String cI = new String();
        
        switch(coordenadaI)
        {
            case 0:
                cI = "A";
            case 1:
                cI = "B";
                break;
            case 2:
                cI = "C";
                break;
            case 3:
                cI = "D";
                break;
            case 4:
                cI = "E";
                break;
            case 5:
                cI = "F";
                break;
            case 6:
                cI = "G";
                break;
            case 7:
                cI = "H";
                break;
            case 8:
                cI = "I";
                break;
            case 9:
                cI = "J";
                break;
            case 10:
                cI = "K";
                break;
            case 11:
                cI = "L";
                break;
            case 12:
                cI = "M";
                break;
            case 13:
                cI = "N";
                break;
            case 14:
                cI = "O";
                break;
            default:
                cI = "ERROR";
                break;
        }
        return cI;
    }

    @Override
    public String toString() {
        return "CASILLA:\n"  
                +"Terreno: " + getTerreno().getNombreTerreno() + "\n"
                +"Usado: " + usado + "\n" 
                +"Niebla: " + tieneNiebla + "\n" 
                +"NoVisitas: " + noVisitas.toString() + "\n"
                +"Coordenada: (" + transformaCoordenadaJ(coordenada.getCoordenadaJ())+", " 
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
