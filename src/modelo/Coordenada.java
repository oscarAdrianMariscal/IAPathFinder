package modelo;

public class Coordenada {
    private int coordenadaI;
    private int coordenadaJ;
    
    public Coordenada(int coordenadaI, int coordenadaJ)
    {
        this.coordenadaI = coordenadaI;
        this.coordenadaJ = coordenadaJ;
    }

    public int getCoordenadaI() {
        return coordenadaI;
    }

    public int getCoordenadaJ() {
        return coordenadaJ;
    }
}
