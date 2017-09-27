package controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Jugador;
import modelo.ParsearArchivo;
import modelo.Tablero;
import modelo.Terreno;
import vista.TableroIU;

public class Controlador {

    
    //Archivo
    
	public boolean esValidoElMapa;

   
    Tablero tablero;

    Casilla [][]mapaSinDatos;
    Casilla [][]mapaConNombres;
    ArrayList<Terreno> terrenoSinPesos;

    ArrayList<Terreno> terrenoConNombres;
    ArrayList<Jugador> jugadores = new ArrayList<>();
    
    /*
    public Controlador() {
    }
    */
    
    public void crearTablero(Coordenada inicial,Coordenada meta) {
    	mapaConNombres = mapaSinDatos;
    	for (int i = 0; i < mapaSinDatos.length; i++) {
    		for (int j = 0 ; j < mapaSinDatos[0].length;j++) {
    			int idTerrenoActual =  mapaSinDatos[i][j].getTerreno().getIdTerreno();
    			for (Terreno terreno: terrenoConNombres) {
    				if (terreno.getIdTerreno()==idTerrenoActual) {
    					mapaConNombres[i][j].setTerreno(terreno);
    				}
    			}
    		}
    	}
    	System.out.println("");
    	int renglones = mapaConNombres.length;
    	int columnas = mapaConNombres[0].length;
    	Terreno [] terrenos = terrenoConNombres.toArray(new Terreno[ terrenoConNombres.size()]);
    	Jugador [] jugadoresArray = jugadores.toArray(new Jugador[jugadores.size()]);
    	tablero =new Tablero(renglones, columnas, mapaConNombres, terrenos, jugadoresArray, inicial, meta);
    	System.out.println("");
    }
    
    public void parsearArchivo(String direccion) {
    	ParsearArchivo parseador = new ParsearArchivo(direccion);
    	mapaSinDatos = parseador.getMapa();
    	terrenoSinPesos= parseador.dameTerrenos();
    	esValidoElMapa= parseador.esValido();
    }
    
    public void agregarJugador(ArrayList<Terreno> terrenos,String nombre) {
	Jugador jugador = new Jugador(nombre, terrenos);
	jugadores.add(jugador);
    }
	
    public ArrayList<Terreno> getTerrenoSinPesos() {
		return terrenoSinPesos;
    }


    public ArrayList<Terreno> getTerrenoConNombres() {
	return terrenoConNombres;
    }
    
    public void setTerrenoConNombres(ArrayList<Terreno> terrenoConNombres) {
        this.terrenoConNombres = terrenoConNombres;
    }
    
    public int getTamanioTerrenoColumnas(){
        return tablero.getNoColumnas();
    }
    
    public int getTamanioTerrenoRenglones(){
        return tablero.getNoRenglones();
    }
    
    public String muestraDatosCasilla(Coordenada coordenada)
    {
        Casilla[][] casilla = tablero.getMapa();
        String informacion = casilla[coordenada.getCoordenadaI()][coordenada.getCoordenadaJ()].toString();
        return informacion;
    }

    public Tablero getTablero()
    {
        return tablero;
    }
}
