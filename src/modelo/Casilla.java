package modelo;

public class Casilla {
    private boolean usado;
    private boolean tieneNiebla;
    private int[] noVisitas;
    private Coordenada coordenada;
    private Terreno terreno;
    
    public Casilla(boolean usado, boolean tieneNiebla,
                     int[] noVisitas, Coordenada coordenada, Terreno terreno){
        
        this.usado = usado;
        this.tieneNiebla = tieneNiebla;
        this.noVisitas = noVisitas;
        this.coordenada = coordenada;
        this.terreno = terreno;
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

    public int[] getNoVisitas() {
        return noVisitas;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
    
    public String noVisitas(int[] noVisitas){
        
        int i = 0;
        String numVisitas = new String();
        
        for(i=0; i<noVisitas.length; i++)
        {
            numVisitas = numVisitas + ", " + noVisitas[i]; 
        }
        
        return numVisitas;
    }
    
    private String transformaCoordenadaI(int coordenadaI)
    {
        String cI = new String();
        
        switch(coordenadaI)
        {
            case 1:
                cI = "A";
                break;
            case 2:
                cI = "B";
                break;
            case 3:
                cI = "C";
                break;
            case 4:
                cI = "D";
                break;
            case 5:
                cI = "E";
                break;
            case 6:
                cI = "F";
                break;
            case 7:
                cI = "G";
                break;
            case 8:
                cI = "H";
                break;
            case 9:
                cI = "I";
                break;
            case 10:
                cI = "J";
                break;
            case 11:
                cI = "K";
                break;
            case 12:
                cI = "L";
                break;
            case 13:
                cI = "M";
                break;
            case 14:
                cI = "N";
                break;
            case 15:
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
                +"NoVisitas: " + noVisitas(noVisitas) + "\n"
                +"Coordenada: (" + coordenada.getCoordenadaJ() +", " 
                + transformaCoordenadaI(coordenada.getCoordenadaI())+ ")";
    }
}
