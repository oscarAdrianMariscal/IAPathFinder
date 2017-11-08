package modelo;

import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import com.tree.TreeNode;
import controlador.Controlador;

public class Backtracking implements Runnable{

	class Coord
	{
		public int x; 
		public int y;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			char letras =  (char)("A".codePointAt(0) + x);
			return String.valueOf(letras) + String.valueOf(y+1);
		}
	};

	TreeNode<String> arbol;
	TreeNode<String> nodoActual;
	private Casilla[][] mapa;
	Tablero tablero;
	String ordenExpansion;
	boolean seEncontroLaMeta;
	Coord inicio;
	Coord meta;
	Controlador controlador;
	//Coord actual;

	//Deberia de crear una clase de utilerias en java, para no reescribir este código.
	public String convertXYaCoord(int x, int y) {
		char letras =  (char)("A".codePointAt(0) + x);
		return String.valueOf(letras) + String.valueOf(y+1);
	}

	public Coord coordAXy(String coordenada) {
		String numero = coordenada.substring(1,coordenada.length());
		Coord c = new Coord(coordenada.codePointAt(0)-"A".codePointAt(0), Integer.parseInt(numero)-1 );
		return c;
	}

	public Backtracking(Tablero t , Controlador c) {
		tablero = t;

		//ordenExpansion = tablero.getOrdenExpansion();
		inicio = new Coord(tablero.getInicio().getCoordenadaJ(),tablero.getInicio().getCoordenadaI());
		meta = new Coord( tablero.getFin().getCoordenadaI(),tablero.getFin().getCoordenadaJ());
		arbol = new TreeNode<String>(convertXYaCoord(inicio.x,inicio.y));
		//actual = inicio;
		nodoActual= arbol;
		seEncontroLaMeta =false;
		controlador = c;
		
	}



	/*
	 	Backtracking
		 	si no se ha llegado a la meta
		 		tiene candidatos. (Abiertos)
		 			agarramos el primero.
		 				esValido
		 					ya esta en el arbol
		 						nos brincamos
		 					no esta en el arbol
		 						agregamos
		 						este es nodo actual
		 						Backtracking
		 				el siguiente en orden
				No tiene candidatos abiertos
					cierra esta nodo

	 */

	private void hacerBacktracking (Coord actual) {
		System.out.println(actual.toString());

		if (!seEncontroLaMeta) {
			Coord arriba,derecha,abajo,izquierda;
			arriba = 	new Coord(actual.x,actual.y-1);
			derecha = 	new Coord(actual.x+1,actual.y);
			abajo = 	new Coord(actual.x,actual.y+1);
			izquierda = new Coord(actual.x-1,actual.y);
			//checkarValidaciones(actual);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tablero.esValidoArriba(actual.x, actual.y)) {
				if (agregarHijo(arriba)) {
					controlador.moverArriba(actual.y, actual.x);
					hacerBacktracking(arriba);
					return;
				}
			}
			if (tablero.esValidoDerecha(actual.x, actual.y)) {
				if (agregarHijo(derecha)){
					controlador.moverDerecha(actual.y, actual.x);
					hacerBacktracking(derecha);
					return;
				}
			}
			if (tablero.esValidoAbajo(actual.x, actual.y)) {
				if (agregarHijo(abajo)) {
					controlador.moverAbajo(actual.y, actual.x);
					hacerBacktracking(abajo);
					return;	
				}
			}
			if (tablero.esValidoIzquierda(actual.x, actual.y)) {
				if (agregarHijo(izquierda)) {
					controlador.moverIzquierda(actual.y, actual.x);
					hacerBacktracking(izquierda);
					return;
				}
			}
			TreeNode<String> nodo = arbol.findTreeNode(actual.toString());
			nodo.abierto=false;
			nodoActual=nodo;
			moverVisualAlPadre(actual);
			hacerBacktracking(coordAXy(nodo.parent.data) );
		}
	}
	//Para debug
	public void checkarValidaciones(Coord actual) {
		Coord arriba,derecha,abajo,izquierda;
		arriba = 	new Coord(actual.x,actual.y-1);
		derecha = 	new Coord(actual.x+1,actual.y);
		abajo = 	new Coord(actual.x,actual.y+1);
		izquierda = new Coord(actual.x-1,actual.y);
		System.out.println("---------   " + actual.toString() + "    ------------");
		System.out.println("Arriba: " + tablero.esValidoArriba(arriba.x, arriba.y));
		System.out.println("Derecha: " + tablero.esValidoDerecha(derecha.x, derecha.y));
		System.out.println("Abajo: " + tablero.esValidoAbajo(abajo.x, abajo.y));
		System.out.println("izquierda: " + tablero.esValidoIzquierda(izquierda.x, izquierda.y));
		System.out.println("---------------------");

	}

	public boolean agregarHijo(Coord posicion) {
		//Esta tratando de agregar el padre.
		if (nodoActual.parent != null && nodoActual.data.equals(posicion.toString())) {
			return false;
		}
		TreeNode<String> nodo = arbol.findTreeNode(posicion.toString());
		if (nodo == null) {
			nodoActual = nodoActual.addChild(posicion.toString());
			if (nodoActual.data.equals(meta.toString())) {
				seEncontroLaMeta = true;
			}
			return true;

		}
		return false;
	}

	public void moverVisualAlPadre(Coord actual) {
		Coord arriba,derecha,abajo,izquierda;
		arriba = 	new Coord(actual.x,actual.y-1);
		derecha = 	new Coord(actual.x+1,actual.y);
		abajo = 	new Coord(actual.x,actual.y+1);
		izquierda = new Coord(actual.x-1,actual.y);
		
		if (nodoActual.parent.toString().equals(arriba.toString())){
			controlador.moverArriba(actual.y, actual.x);
		}
		else if (nodoActual.parent.toString().equals(derecha.toString())){
			controlador.moverDerecha(actual.y, actual.x);
		}
		else if (nodoActual.parent.toString().equals(abajo.toString())){
			controlador.moverAbajo(actual.y, actual.x);
		}
		else if (nodoActual.parent.toString().equals(izquierda.toString())){
			controlador.moverIzquierda(actual.y, actual.x);
			System.out.println("izquierda");
		}

	}

	public TreeNode<String> getArbol() {
		return arbol;
	}


	private DefaultMutableTreeNode agregarHijos (TreeNode<String> nodo, DefaultMutableTreeNode visualNodo) {	
		for (TreeNode<String> n : nodo.children) {
			String coordenada = n.data;
			String letra = coordenada.substring(0, 1);
			String numeros = coordenada.substring(1,coordenada.length());
			int x = letra.codePointAt(0) - "A".codePointAt(0) ;
			int y = Integer.parseInt(numeros)-1;
			mapa[y][x].getNoVisitas();

			DefaultMutableTreeNode rama = new DefaultMutableTreeNode(n.data + mapa[y][x].getNoVisitas());
			visualNodo.add(rama);
			agregarHijos(n, rama);
		}
		return visualNodo;	
	}

	public void moverJugadorDerecha(Coord cord) {
		controlador.moverDerecha(cord.x, cord.y);
	}


	public JTree dameJTree() {

		String coordenada = arbol.data;
		String letra = coordenada.substring(0, 1);
		String numeros = coordenada.substring(1,coordenada.length());
		int x = letra.codePointAt(0) - "A".codePointAt(0) ;
		int y = Integer.parseInt(numeros)-1;
		mapa = tablero.getMapa();
		DefaultMutableTreeNode rootVisual = new DefaultMutableTreeNode(arbol.data + mapa[y][x].getNoVisitas());
		DefaultMutableTreeNode modeloDeArbolCompleto = agregarHijos(arbol, rootVisual);
		return new JTree(modeloDeArbolCompleto);
	}
	
	public JTree dameSolucion() {
		TreeNode<String> nodo= nodoActual;
		DefaultMutableTreeNode rootVisual = new DefaultMutableTreeNode(nodo.data);
		DefaultMutableTreeNode rama = rootVisual; 
		do {
			nodo = nodo.parent;
			
			DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(nodo.data); 
			rama.add(hijo);
			rama = hijo;
			
		}while (!nodo.isRoot());
		return new JTree(rootVisual);
	}
	

	@Override
	public void run() {
		hacerBacktracking(inicio);
		
		controlador.mostrarArbol(dameJTree(), "El arbol de expansion");
		controlador.mostrarArbol(dameSolucion() , "La solución");
	}
}
