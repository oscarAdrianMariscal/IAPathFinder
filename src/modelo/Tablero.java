package modelo;

public class Tablero {
    private int tamanioMaximo = 15;
    private int noRenglones;
    private int noColumnas;
    private Casilla[][] mapa;
    private Terreno[] terrenos;
    private Jugador[] jugadores;
    private Coordenada inicio;
    private Coordenada fin;
    private Casilla actual;

    public Tablero(int noRenglones, int noColumnas, Casilla[][] mapa, Terreno[] terrenos, Jugador[] jugadores, Coordenada inicio, Coordenada fin) {
        this.noRenglones = noRenglones;
        this.noColumnas = noColumnas;
        this.mapa = mapa;
        this.terrenos = terrenos;
        this.jugadores = jugadores;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getTamanioMaximo() {
        return tamanioMaximo;
    }

    public int getNoRenglones() {
        return noRenglones;
    }

    public int getNoColumnas() {
        return noColumnas;
    }

    public Casilla[][] getMapa() {
        return mapa;
    }

    public Terreno[] getTerrenos() {
        return terrenos;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public Coordenada getInicio() {
        return inicio;
    }

    public Coordenada getFin() {
        return fin;
    }
    
    public Casilla getCoordenadaEspecial(int renglon, int columna)
    {
        return mapa[renglon][columna];
    }
    
    public boolean posicionInicialEsValida() {
    	boolean esValidaLaPosicion=true;
    	Terreno terreno =mapa[inicio.getCoordenadaI()][inicio.getCoordenadaJ()].getTerreno(); 
    	int idTerreno = terreno.getIdTerreno();
    	for (Terreno t : jugadores[0].getPesos()) {
    		if (idTerreno == t.getIdTerreno()) {
    			if (t.getCosto()==-1) {
    				esValidaLaPosicion=false;
    			}
    		}
    			
    	}
    	return esValidaLaPosicion;
    }
    
    public String dameTerreno(Coordenada coordenada)
    {
        String terreno = new String();
        terreno = "Terreno: " + mapa[coordenada.getCoordenadaI()][coordenada.getCoordenadaJ()].getTerreno().getNombreTerreno();
        return terreno;
    }
    public void reiniciarCasillasUsadas() {
    	for(Casilla []renglon: mapa) {
    		for(Casilla c : renglon) {
    			c.setUsado(false);
    		}
    	}
    }
}
