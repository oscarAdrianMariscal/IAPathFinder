package modelo;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.text.ComponentView;
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
	private int xActual ;
	private int yActual ;
	private String ordenExpansion;
	TreeNode<String> arbol;
	TreeNode<String> nodoActual;



	public Tablero(int noRenglones, int noColumnas, Casilla[][] mapa, Terreno[] terrenos, Jugador[] jugadores, Coordenada inicio, Coordenada fin) {
		this.noRenglones = noRenglones;
		this.noColumnas = noColumnas;
		this.mapa = mapa;
		this.terrenos = terrenos;
		this.jugadores = jugadores;
		this.inicio = inicio;
		this.fin = fin;
		this.ordenExpansion ="URDL";
		int x = inicio.getCoordenadaJ();
		int y = inicio.getCoordenadaI();
		llenarMapaConLosPesosDelJugadorActual();
		
		//inicializarArbol(x, y);
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
		
		int x = inicio.getCoordenadaJ();
		int y = inicio.getCoordenadaI();
		return (mapa[x][y].getTerreno().getCosto()!=-1);
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


	private void llenarMapaConLosPesosDelJugadorActual() {
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



	public void hacerMovimiento(int x, int y)
	{
		if (arbol==null) {
			inicializarArbol(x, y);
		}
		if (nodoActual.parent != null && nodoActual.parent.data.equals(convertXYaCoord(x, y))) {
			//panico panico se regreso en su camino. 
			nodoActual = nodoActual.parent;
			return;
		}
		for (TreeNode<String> nodo : nodoActual.children) {
			String nombreNodo = convertXYaCoord(x, y);
			if (nodo.data.equals(nombreNodo)) {
				nodoActual = nodo;
			}
		}
		boolean esRaiz = (nodoActual.parent == null); 
		for (char direccion: ordenExpansion.toCharArray()) {
			if (direccion == 'U') {
				String arriba = convertXYaCoord(x,y-1);
				if (esValidoArriba(x, y) && (esRaiz || ! nodoActual.parent.data.equals(arriba))  ) {
					if (! yaExisteEnLosHijos(nodoActual, arriba))
						nodoActual.addChild(arriba);
				}
			}
			if (direccion == 'R') {
				String derecha= convertXYaCoord(x+1,y);
				if (esValidoDerecha(x, y) &&  (esRaiz || ! nodoActual.parent.data.equals(derecha) )) {
					if (! yaExisteEnLosHijos(nodoActual, derecha))
						nodoActual.addChild(derecha);
				}
			}
			if (direccion == 'D') {
				String abajo = convertXYaCoord(x,y+1);
				if (esValidoAbajo(x, y) && (esRaiz || ! nodoActual.parent.data.equals(abajo) )) {
					if (! yaExisteEnLosHijos(nodoActual, abajo))
						nodoActual.addChild(abajo);
				}
			}
			if (direccion == 'L') {
				String izquierda = convertXYaCoord(x-1,y);
				if (esValidoIzquierda(x, y) && (esRaiz || ! nodoActual.parent.data.equals(izquierda))) {
					if (! yaExisteEnLosHijos(nodoActual, izquierda))
						nodoActual.addChild(izquierda);
				}
			}
		}
		
		xActual=x;
		yActual=y;
	}
	
	public boolean yaExisteEnLosHijos(TreeNode<String> nodo, String nombreHijo) {
		for (TreeNode<String> hijo: nodo.children ){
			if (hijo.data.equals(nombreHijo))
				return true;
		}
		return false;
	}
	
	public void inicializarArbol(int x, int y) {
		arbol = new TreeNode<String>(convertXYaCoord(x,y));
		nodoActual= arbol;
		/*for (char direccion: ordenExpansion.toCharArray()) {
			if (direccion == 'U') {
				if (esValidoArriba(x, y)) {
					nodoActual.addChild(convertXYaCoord(x,y-1));
				}
			}
			if (direccion == 'R') {
				if (esValidoDerecha(x, y)) {
					nodoActual.addChild(convertXYaCoord(x,y+1));
				}
			}
			if (direccion == 'D') {
				if (esValidoAbajo(x, y)) {
					nodoActual.addChild(convertXYaCoord(x+1,y));
				}
			}
			if (direccion == 'L') {
				if (esValidoIzquierda(x, y)) {
					nodoActual.addChild(convertXYaCoord(x-1,y));
				}
			}
		}
		*/
	}

	public boolean esValidoIzquierda(int x, int y) {
		
		return x>0 && mapa[y][x-1].getTerreno().getCosto()!=-1;
	}

	public boolean esValidoDerecha(int x, int y) {
		
		return x<noRenglones-1 && mapa[y][x+1].getTerreno().getCosto()!=-1;
	}
	public boolean esValidoArriba(int x, int y ) {
		return y>0 && mapa[y-1][x].getTerreno().getCosto()!=-1;
	}
	public boolean esValidoAbajo(int x, int y ) {
		return y<noRenglones-1 && mapa[y+1][x].getTerreno().getCosto()!=-1;

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
	
	public String convertXYaCoord(int x, int y) {
		char letras =  (char)("A".codePointAt(0) + x);
		return String.valueOf(letras) + String.valueOf(y+1);
	}

}
