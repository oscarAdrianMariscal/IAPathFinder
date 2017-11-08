package controlador;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import modelo.Casilla;
import modelo.Coordenada;
import modelo.Jugador;
import modelo.ParsearArchivo;
import modelo.Tablero;
import modelo.Terreno;
import vista.VentanaArbol;
import vista.VentanaPrincipal;

public class Controlador {

	public boolean esValidoElMapa;
	public boolean esValidaPosicionInicial;
	public boolean nuevaPartida;
	Tablero tablero;
	Casilla [][]mapaSinDatos;
	Casilla [][]mapaConNombres;
	ArrayList<Terreno> terrenoSinPesos;
	ArrayList<Terreno> terrenoConNombres;
	ArrayList<Jugador> jugadores = new ArrayList<>();

        VentanaPrincipal vP;


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
		tablero.reiniciarCasillasUsadas();
		esValidaPosicionInicial= tablero.posicionInicialEsValida();
		System.out.println("");
	}

	public void parsearArchivo(String direccion) {
		ParsearArchivo parseador = new ParsearArchivo(direccion);
		mapaSinDatos = parseador.getMapa();
		terrenoSinPesos= parseador.dameTerrenos();
		esValidoElMapa= parseador.esValido();
	}

	public void agregarJugador(ArrayList<Terreno> terrenos,String nombre, ImageIcon imagen) {
		Jugador jugador = new Jugador(nombre, terrenos, imagen);
		jugadores.add(jugador);
	}

	public void eliminarJugador(int posicion)
	{
		jugadores.remove(posicion);
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

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public ArrayList<Jugador> getArregloJugadores()
	{
		return jugadores;
	}

	public void reiniciaArregloVisitas()
	{
		for(int i = 0; i<tablero.getNoRenglones(); i++)
		{
			for(int j=0; j<tablero.getNoColumnas(); j++)
			{
				tablero.getCoordenadaEspecial(i, j).reiniciaNoVisitas();
			}
		}
	}

        
        public void moverArriba(int renglon, int columna)
        {
            vP.hacerMovimientoArriba(renglon, columna);
        }
        
        public void moverAbajo(int renglon, int columna)
        {
            vP.hacerMovimientoAbajo(renglon, columna);
        }
        
        public void moverIzquierda(int renglon, int columna)
        {
            vP.hacerMovimientoIzquierda(renglon, columna);
        }
        
        public void moverDerecha(int renglon, int columna)
        {
            vP.hacerMovimientoDerecha(renglon, columna);
        }

	public void setvP(VentanaPrincipal vP) {
		this.vP = vP;
	}
	
	public void mostrarArbol(JTree arbol, String titulo) {
	       	vP.mostarArbol(arbol,titulo);
	}
}
