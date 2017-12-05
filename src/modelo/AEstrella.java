package modelo;

import com.tree.TreeNodeS;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import controlador.Controlador;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

public class AEstrella implements Runnable{

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

	TreeNodeS<String> arbol;
	TreeNodeS<String> nodoActual;
	private Casilla[][] mapa;
	Tablero tablero;
	String ordenExpansion;
	boolean seEncontroLaMeta;
	Coord inicio;
	Coord meta;
	Controlador controlador;
	static int visitaActual;
        int tipoDistancia;
        private ListaNodosOrdenadas listaAbierta;
       
	//Deberia de crear una clase de utilerias en java, para no reescribir este c�digo.
	public String convertXYaCoord(int x, int y) {
		char letras =  (char)("A".codePointAt(0) + x);
		return String.valueOf(letras) + String.valueOf(y+1);
	}

	public Coord coordAXy(String coordenada) {
		String numero = coordenada.substring(1,coordenada.length());
		Coord c = new Coord(coordenada.codePointAt(0)-"A".codePointAt(0), Integer.parseInt(numero)-1 );
		return c;
	}

	public AEstrella(Tablero t, Controlador c) {
                tablero = t;
		visitaActual=1;
		inicio = new Coord(tablero.getInicio().getCoordenadaI(),tablero.getInicio().getCoordenadaJ());
		meta = new Coord( tablero.getFin().getCoordenadaI(),tablero.getFin().getCoordenadaJ());
		arbol = new TreeNodeS<String>(convertXYaCoord(inicio.x,inicio.y));
		//arbol.visitas.add(visitaActual);
		//visitaActual++;
		nodoActual= arbol;
		seEncontroLaMeta =false;
		controlador = c;
		ordenExpansion = controlador.getOrdenDeExpansion();
                tipoDistancia = controlador.getTipoDistancia();
	}
        
        public void encuentraCaminoMasCorto(Coord actual)
        {
            System.out.println(actual.toString());
            listaAbierta = new ListaNodosOrdenadas();
            
            float GNP = 0 + nodoActual.getCosto();
            nodoActual.setGN(GNP);
            float HNP = obtenerHeuristica(actual, meta);
            nodoActual.setHN(HNP);
            float FNP = GNP + HNP;
            nodoActual.setFN(FNP);
            
            System.out.println("PADRE");
            System.out.println("GN: " + nodoActual.getGN());
            System.out.println("HN: " + nodoActual.getHN());
            System.out.println("FN: " + nodoActual.getFN());
            
            listaAbierta.add(nodoActual);
            System.out.println("LISTA ORDENADA");
            System.out.println("TAM:" + listaAbierta.size());
            
            while(!seEncontroLaMeta && listaAbierta.size() != 0) {
                
                TreeNodeS<String> nodo = listaAbierta.getFirst();
                nodoActual = arbol.findTreeNode(nodo.data);
                nodoActual.visitas.add(visitaActual);
                visitaActual++;
                listaAbierta.remove(nodo);
                System.out.println("TAM - removi:" + listaAbierta.size());
                System.out.println(actual.toString());
                actual = coordAXy(nodoActual.data);
                
                Coord arriba,derecha,abajo,izquierda;
                arriba = 	new Coord(actual.x,actual.y-1);
                derecha = 	new Coord(actual.x+1,actual.y);
                abajo = 	new Coord(actual.x,actual.y+1);
                izquierda = new Coord(actual.x-1,actual.y);
                
                for (char direccion: ordenExpansion.toCharArray()) {
                    if (direccion == 'U') {
                        if (tablero.esValidoArriba(actual.x, actual.y)) {
                            if (agregarHijo(arriba)) {
                                //controlador.moverArriba(actual.y, actual.x);
                                System.out.println("Lo expandi ARRIBA");
                                float GN = nodoActual.parent.getGN() + nodoActual.getCosto();
                                nodoActual.setGN(GN);
                                float HN = obtenerHeuristica(arriba, meta);
                                nodoActual.setHN(HN);
                                float FN = GN + HN;
                                nodoActual.setFN(FN);
                                System.out.println(nodoActual.data);
                                System.out.println("GN: " + nodoActual.getGN());
                                System.out.println("HN: " + nodoActual.getHN());
                                System.out.println("FN: " + nodoActual.getFN());
                                listaAbierta.add(nodoActual);
                                System.out.println("TAM:" + listaAbierta.size());
                                
                                //REGRESO EL NODO ACTUAL AL PADRE
                                nodoActual = nodoActual.parent;
                            }
                        }
                    }

                    if (direccion == 'R') {
                        if (tablero.esValidoDerecha(actual.x, actual.y)) {
                            if (agregarHijo(derecha)){
                                //controlador.moverDerecha(actual.y, actual.x);
                                System.out.println("Lo expandi DERECHA");
                                float GN = nodoActual.parent.getGN() + nodoActual.getCosto();
                                nodoActual.setGN(GN);
                                float HN = obtenerHeuristica(arriba, meta);
                                nodoActual.setHN(HN);
                                float FN = GN + HN;
                                nodoActual.setFN(FN);
                                System.out.println(nodoActual.data);
                                System.out.println("GN: " + nodoActual.getGN());
                                System.out.println("HN: " + nodoActual.getHN());
                                System.out.println("FN: " + nodoActual.getFN());
                                listaAbierta.add(nodoActual);
                                System.out.println("TAM:" + listaAbierta.size());
                                
                                //REGRESO EL NODO ACTUAL AL PADRE
                                nodoActual = nodoActual.parent;
                            }
                        }
                    }
                    if (direccion == 'D') {
                        if (tablero.esValidoAbajo(actual.x, actual.y)) {
                            if (agregarHijo(abajo)) {
                                //controlador.moverAbajo(actual.y, actual.x);
                                System.out.println("Lo expandi ABAJO");
                                float GN = nodoActual.parent.getGN() + nodoActual.getCosto();
                                nodoActual.setGN(GN);
                                float HN = obtenerHeuristica(arriba, meta);
                                nodoActual.setHN(HN);
                                float FN = GN + HN;
                                nodoActual.setFN(FN);
                                System.out.println(nodoActual.data);
                                System.out.println("GN: " + nodoActual.getGN());
                                System.out.println("HN: " + nodoActual.getHN());
                                System.out.println("FN: " + nodoActual.getFN());
                                listaAbierta.add(nodoActual);
                                System.out.println("TAM:" + listaAbierta.size());
                                
                                //REGRESO EL NODO ACTUAL AL PADRE
                                nodoActual = nodoActual.parent;
                            }
                        }
                    }
                    if (direccion == 'L') {
                        if (tablero.esValidoIzquierda(actual.x, actual.y)) {
                            if (agregarHijo(izquierda)) {
                                //controlador.moverIzquierda(actual.y, actual.x);
                                System.out.println("Lo expandi IZQUIERDA");
                                float GN = nodoActual.parent.getGN() + nodoActual.getCosto();
                                nodoActual.setGN(GN);
                                float HN = obtenerHeuristica(arriba, meta);
                                nodoActual.setHN(HN);
                                float FN = GN + HN;
                                nodoActual.setFN(FN);
                                System.out.println(nodoActual.data);
                                System.out.println("GN: " + nodoActual.getGN());
                                System.out.println("HN: " + nodoActual.getHN());
                                System.out.println("FN: " + nodoActual.getFN());
                                listaAbierta.add(nodoActual);
                                 System.out.println("TAM:" + listaAbierta.size());
                                
                                //REGRESO EL NODO ACTUAL AL PADRE
                                nodoActual = nodoActual.parent;
                            }
                        }
                    }
                }
                nodoActual.abierto = false;
            }    
        }
        
        private class ListaNodosOrdenadas {
                  //Lista de nodos.
                private ArrayList<TreeNodeS<String>> list = new ArrayList<TreeNodeS<String>>();
                
                public TreeNodeS getFirst() {
                        return list.get(0);
                }

                public void clear() {
                        list.clear();
                }

                public void add(TreeNodeS<String> node) {
                        list.add(node);
                        Collections.sort(list);
                }

                public void remove(TreeNodeS n) {
                        list.remove(n);
                }

                public int size() {
                        return list.size();
                }
                // devuelve si esta ese nodo en la lista.
                public boolean contains(TreeNodeS<String> n) {
                        return list.contains(n);
                }
                
                @Override
                public String toString()
                {
                    return list.toString();
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
        
        public float obtenerHeuristica(Coord uno, Coord dos)
        {
            //DISTANCIA EUCLIDEANA
            if(tipoDistancia == 0)
            {
                return distanciaEuclideana(uno, dos);
            }
            else {  //DISTANCIA MANHATTAN
                return distanciaManhattan(uno, dos);
            }
        }
        
        public float distanciaEuclideana(Coord uno, Coord dos)
        {
            float distancia = (float) Math.sqrt(Math.pow((dos.x - uno.x),2)+
                                Math.pow((dos.y - uno.y),2));
            
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setRoundingMode( RoundingMode.DOWN);
            float distanciaF = Float.parseFloat(numberFormat.format(distancia));
            return distanciaF;                  
        }
        
        public float distanciaManhattan(Coord uno, Coord dos)
        {
            float distancia = (float) (Math.abs((uno.x - dos.x)) + 
                              Math.abs((uno.y - dos.y)));
            
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setRoundingMode( RoundingMode.DOWN);
            float distanciaF = Float.parseFloat(numberFormat.format(distancia));
            return distanciaF;
        }

	public boolean agregarHijo(Coord posicion) {
		if (nodoActual.parent != null && nodoActual.data.equals(posicion.toString())) {
			return false;
		}
		TreeNodeS<String> nodo = arbol.findTreeNode(posicion.toString());
		if (nodo == null) {
			nodoActual = nodoActual.addChild(posicion.toString());
                        nodoActual.setCosto(tablero.getCoordenadaEspecial(posicion.x, posicion.y).getTerreno().getCosto());
			//nodoActual.visitas.add(visitaActual);
			//visitaActual++;
			if (nodoActual.data.equals(meta.toString())) {
				seEncontroLaMeta = true;
                                nodoActual.visitas.add(visitaActual);
                                visitaActual++;
                                System.out.println("FELICIDADES");
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
        
        public void moverVisual(Coord actual) {
		Coord arriba,derecha,abajo,izquierda;
		arriba = 	new Coord(actual.x,actual.y-1);
		derecha = 	new Coord(actual.x+1,actual.y);
		abajo = 	new Coord(actual.x,actual.y+1);
		izquierda = new Coord(actual.x-1,actual.y);

		if (nodoActual.toString().equals(arriba.toString())){
			controlador.moverArriba(actual.y, actual.x);
		}
		else if (nodoActual.toString().equals(derecha.toString())){
			controlador.moverDerecha(actual.y, actual.x);
		}
		else if (nodoActual.toString().equals(abajo.toString())){
			controlador.moverAbajo(actual.y, actual.x);
		}
		else if (nodoActual.toString().equals(izquierda.toString())){
			controlador.moverIzquierda(actual.y, actual.x);
			System.out.println("izquierda");
		}

	}

	public TreeNodeS<String> getArbol() {
		return arbol;
	}


	private DefaultMutableTreeNode agregarHijos (TreeNodeS<String> nodo, DefaultMutableTreeNode visualNodo) {	
		for (TreeNodeS<String> n : nodo.children) {
			String coordenada = n.data;
			String letra = coordenada.substring(0, 1);
			String numeros = coordenada.substring(1,coordenada.length());
			int x = letra.codePointAt(0) - "A".codePointAt(0) ;
			int y = Integer.parseInt(numeros)-1;
			mapa[y][x].getNoVisitas();

			DefaultMutableTreeNode rama = new DefaultMutableTreeNode(n.data + n.visitas + n.abierto);
			
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
		mapa = controlador.getTablero().getMapa();
		DefaultMutableTreeNode rootVisual = new DefaultMutableTreeNode(arbol.data + arbol.visitas + " " + arbol.abierto);
		DefaultMutableTreeNode modeloDeArbolCompleto = agregarHijos(arbol, rootVisual);
		return new JTree(modeloDeArbolCompleto);
	}
	
	public int numeroDeHijos(Coord actual) {
		Coord arriba,derecha,abajo,izquierda;
		int numeroHijos=0;
		arriba = 	new Coord(actual.x,actual.y-1);
		derecha = 	new Coord(actual.x+1,actual.y);
		abajo = 	new Coord(actual.x,actual.y+1);
		izquierda = new Coord(actual.x-1,actual.y);
		
		if (tablero.esValidoArriba(actual.x, actual.y) && arbol.findTreeNode(arriba.toString())==null) {
			numeroHijos++;
		}
		if (tablero.esValidoDerecha(actual.x, actual.y) && arbol.findTreeNode(derecha.toString())==null) {
			numeroHijos++;
		}
		if ( tablero.esValidoAbajo(actual.x, actual.y) && arbol.findTreeNode(abajo.toString())==null) {
			numeroHijos++;
		}
		if( tablero.esValidoIzquierda(actual.x, actual.y) && arbol.findTreeNode(izquierda.toString())==null) {
			numeroHijos++;
		}
		return numeroHijos;
		
	}

	public JTree dameSolucion() {
		TreeNodeS<String> nodo= nodoActual;
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
		encuentraCaminoMasCorto(inicio);
		/*JTree arbol = dameJTree();
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Raiz");
		DefaultMutableTreeNode hoja = new DefaultMutableTreeNode("hoja");
		
		raiz.add(hoja);
		JTree prueba = new JTree(raiz);
		System.out.println("Hey");
		controlador.mostrarArbol(prueba, "El arbol de expansion");*/
		controlador.mostrarArbol(dameSolucion() , "La soluci�n");
		 
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}