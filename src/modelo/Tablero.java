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

	//Utilizado como utileria en el metodo dameJTree
	private DefaultMutableTreeNode agregarHijos (TreeNode<String> nodo, DefaultMutableTreeNode visualNodo) {	
		for (TreeNode<String> n : nodo.children) {
			//n.data;
			//n
			String coordenada = n.data;
			String letra = coordenada.substring(0, 1);
			String numeros = coordenada.substring(1,coordenada.length());
			int x = letra.codePointAt(0) - "A".codePointAt(0) ;
			int y = Integer.parseInt(numeros)-1;
			mapa[y][x].getNoVisitas();
			System.out.println(letra + "--" + numeros);
			DefaultMutableTreeNode rama = new DefaultMutableTreeNode(n.data + mapa[y][x].getNoVisitas());
			visualNodo.add(rama);
			agregarHijos(n, rama);
		}
		return visualNodo;	
	}

	//Este metodo regresa un JTree. que es usado para imprimir en pantalla el objeto TreeNode. utiliza el atributo arbol
	public JTree dameJTree() {

		String coordenada = arbol.data;
		String letra = coordenada.substring(0, 1);
		String numeros = coordenada.substring(1,coordenada.length());
		int x = letra.codePointAt(0) - "A".codePointAt(0) ;
		int y = Integer.parseInt(numeros)-1;
		mapa[y][x].getNoVisitas();
		
		DefaultMutableTreeNode rootVisual = new DefaultMutableTreeNode(arbol.data + mapa[y][x].getNoVisitas());
		DefaultMutableTreeNode modeloDeArbolCompleto = agregarHijos(arbol, rootVisual);

		return new JTree(modeloDeArbolCompleto);
	}


	//Este metodo es el crea crea y actualiza el arbol, recibimos las coordena x,y como si fuera un plano cartesiano
	public void hacerMovimiento(int x, int y)
	{
		//Lo tengo para que se cree el arbol en el primer movimiento.
		if (arbol==null) {
			inicializarArbol(x, y);
		}
		//Para revisar si el "movimiento" es igual al padre, es decir se regreso.
		if (nodoActual.parent != null && nodoActual.parent.data.equals(convertXYaCoord(x, y))) {
			//panico panico se regreso en su camino. 
			nodoActual = nodoActual.parent;
			return;
		}
		
		//Buscamos en todos los hijos del nodoActual, y obtenemos a cual nos vamos a mover. cuando lo obtenemos lo "convertimos"
		// en el nodo actual. 
		for (TreeNode<String> nodo : nodoActual.children) {
			String nombreNodo = convertXYaCoord(x, y);
			if (nodo.data.equals(nombreNodo)) {
				nodoActual = nodo;
			}
		}
		boolean esRaiz = (nodoActual.parent == null); 
		//Aquí estoy usando la cadena ordenExpansion para simular el orden de expansion, la cadena es algo como 
		// URDL = up , right, down left por sus siglas. 
		for (char direccion: ordenExpansion.toCharArray()) {
			if (direccion == 'U') {
				//Obtengo el nombre del nodo del de arriba este puede ser A1,C4... etc descripto por $CARACTER$DIGITOS
				String arriba = convertXYaCoord(x,y-1);
				//System.out.println( " "+arriba + " " );
				//Aquí validamos si es valido, es decir si no se desborda del tablero de datos o el costo == -1
				//también valido que no sea igual al padre, para no expandirlo.
				if (esValidoArriba(x, y) && (esRaiz || ! nodoActual.parent.data.equals(arriba))  ) {
					//Checo que el nodo no exista ya en los hijos, para no duplicar en el caso que se regrese en el camino
					if (! yaExisteEnLosHijos(nodoActual, arriba))
						nodoActual.addChild(arriba);
				}
			}
			
			if (direccion == 'R') {
				String derecha= convertXYaCoord(x+1,y);
				//System.out.println( " "+derecha + " " );
				if (esValidoDerecha(x, y) &&  (esRaiz || ! nodoActual.parent.data.equals(derecha) )) {
					if (! yaExisteEnLosHijos(nodoActual, derecha))
						nodoActual.addChild(derecha);
				}
			}
			if (direccion == 'D') {
				String abajo = convertXYaCoord(x,y+1);
				//System.out.println( " "+abajo + " " );
				if (esValidoAbajo(x, y) && (esRaiz || ! nodoActual.parent.data.equals(abajo) )) {
					if (! yaExisteEnLosHijos(nodoActual, abajo))
						nodoActual.addChild(abajo);
				}
			}
			if (direccion == 'L') {
				String izquierda = convertXYaCoord(x-1,y);
				System.out.println( " "+izquierda + " " );
				if (esValidoIzquierda(x, y) && (esRaiz || ! nodoActual.parent.data.equals(izquierda))) {
					if (! yaExisteEnLosHijos(nodoActual, izquierda))
						nodoActual.addChild(izquierda);
				}
			}
		}
		
		
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
	}

	//Valida costo sea diferente de -1 y que no nos desbordemos.
	public boolean esValidoIzquierda(int x, int y) {
		
		return x>0 && mapa[y][x-1].getTerreno().getCosto()!=-1;
	}

	public boolean esValidoDerecha(int x, int y) {
		
		return x<noColumnas-1 && mapa[y][x+1].getTerreno().getCosto()!=-1;
	}
	public boolean esValidoArriba(int x, int y ) {
		return y>0 && mapa[y-1][x].getTerreno().getCosto()!=-1;
	}
	public boolean esValidoAbajo(int x, int y ) {
		return y<noRenglones-1 && mapa[y+1][x].getTerreno().getCosto()!=-1;

	}

	//Imprime  el tablero en consola, usado para debugger nomas.
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
	
	//Convierte una coordenada (0,0) a A1 o (2,0) a C1 
	public String convertXYaCoord(int x, int y) {
		char letras =  (char)("A".codePointAt(0) + x);
		return String.valueOf(letras) + String.valueOf(y+1);
	}
	
	//Imprime el arbol en consola, solo para debuggear.
	public void imprimirArbol() {
		for (TreeNode<String> node : arbol) {
			String indent = createIndent(node.getLevel());
			System.out.println(indent + node.data);
		}
	}

	//Metodo de utileria, utilizado cmo auxiliar en imprimirArbol
	private static String createIndent(int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

}
