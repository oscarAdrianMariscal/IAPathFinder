package modelo;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.tree.TreeNode;

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
	private String ordenExpansion;
	TreeNode<String> arbol;



	public Tablero(int noRenglones, int noColumnas, Casilla[][] mapa, Terreno[] terrenos, Jugador[] jugadores, Coordenada inicio, Coordenada fin) {
		this.noRenglones = noRenglones;
		this.noColumnas = noColumnas;
		this.mapa = mapa;
		this.terrenos = terrenos;
		this.jugadores = jugadores;
		this.inicio = inicio;
		this.fin = fin;
		this.ordenExpansion ="URDL";
		this.actual = new Casilla(false, false, inicio, null);
		arbol = new TreeNode<String>(inicio.dameCoordenadaEnCadena());
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
		
		//return mapa[inicio.getCoordenadaI()][inicio.getCoordenadaJ()].getTerreno().getCosto()==-1;
		return true;
		/*
		Terreno terreno =mapa[inicio.getCoordenadaI()][inicio.getCoordenadaJ()].getTerreno(); 
		int idTerreno = terreno.getIdTerreno();
		for (Terreno t : jugadores[0].getPesos()) {
			if (idTerreno == t.getIdTerreno()) {
				if (t.getCosto()==-1) {
					esValidaLaPosicion=false;
				}
			}

		}
		*/
		
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


	public void llenarMapaConLosPesosDelJugadorActual() {
		for(Casilla []renglon: mapa) {
			for(Casilla c : renglon) {
				int idTerreno = c.getTerreno().getIdTerreno();
				for (Terreno t : jugadores[0].getPesos()) {
					if (idTerreno == t.getIdTerreno()) {
						c.setTerreno(t);
					}
				}
			}
		}
		
		System.out.println("");
	}

	private DefaultMutableTreeNode agregarHijos (TreeNode<String> nodo, DefaultMutableTreeNode visualNodo) {	
		for (TreeNode<String> n : nodo.children) {
			DefaultMutableTreeNode rama = new DefaultMutableTreeNode(n.data);
			visualNodo.add(rama);
			agregarHijos(n, rama);
		}
		return visualNodo;	
	}

	public JTree dameJTree() {

		DefaultMutableTreeNode rootVisual = new DefaultMutableTreeNode(arbol.data);
		DefaultMutableTreeNode modeloDeArbolCompleto = agregarHijos(arbol, rootVisual);

		return new JTree(modeloDeArbolCompleto);
	}



	public void hacerMovimiento(Casilla a)
	{
		System.out.println(a);
		this.actual=a;
		String posActual = this.actual.getCoordenada().dameCoordenadaEnCadena();
		for (char direccion: ordenExpansion.toCharArray()) {
			//System.out.print(this.actual.getCoordenada().dameCoordenadaEnCadena() +	" ");
			if (direccion == 'U') {
				 esValidoArriba();
			}
//			if (direccion == 'R') {
//				System.out.println("R" + esValidoDerecha());
//			}
//			if (direccion == 'L') {
//				System.out.println("L" +  esValidoIzquierda());
//			}
//			if (direccion == 'D') {
//				System.out.println("D" +  esValidoAbajo());
//			}
		}
		//System.out.println("-------");
		String posAMover = actual.getCoordenada().dameCoordenadaEnCadena();
		
	}

	public boolean esValidoIzquierda() {
		
		int x = actual.getCoordenada().getCoordenadaJ();
		int y = actual.getCoordenada().getCoordenadaI();
		
		return false;
	}

	public boolean esValidoDerecha() {
		int x = actual.getCoordenada().getCoordenadaJ();
		int y = actual.getCoordenada().getCoordenadaI();
		return false;
	}
	public boolean esValidoArriba() {
		int x = actual.getCoordenada().getCoordenadaJ();
		int y = actual.getCoordenada().getCoordenadaI();
		
		System.out.println("-----");
		
		//------
		
		
		
		return false;
		
	}
	public boolean esValidoAbajo() {
		int x = actual.getCoordenada().getCoordenadaJ();
		int y = actual.getCoordenada().getCoordenadaI();
		return false;
	}

	public void imprimirMapa() {
		for (int i =0 ; i< noRenglones; i++) {
			for (int j = 0 ; j < noColumnas; j++) {
				String a = Integer.toString(mapa[i][j].getTerreno().getIdTerreno());
				String b = Float.toString(mapa[i][j].getTerreno().getCosto()) ;
				System.out.print(fixedLengthString(a +":" +b, 5 ));
				System.out.print(" | " );
			}
			System.out.println();
		}
	}
	public static String fixedLengthString(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}

}
