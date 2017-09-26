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
    
    public void crearTablero() {
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
    	tablero =new Tablero(renglones, columnas, mapaConNombres, terrenos, jugadoresArray, new Coordenada(1, 1), new Coordenada(2, 2));
    	System.out.println("");
    }
    
    public void parsearArchivo(String direccion) {
    	ParsearArchivo parseador = new ParsearArchivo(direccion);
    	mapaSinDatos = parseador.getMapa();
    	terrenoSinPesos= parseador.dameTerrenos();
    }
    
    public void inicializaTablero(int noRenglones, int noColumnas, Casilla[][] mapa, Terreno[] terrenos, Jugador[] jugadores, Coordenada inicio, Coordenada fin, int tamanioI, int tamanioJ)
    {
        //gettablero = new Tablero(noRenglones, noColumnas, mapa, terrenos, jugadores, inicio, fin, tamanioI, tamanioJ);
    }
    
    /*
     @Override
     public void actionPerformed(ActionEvent e) {
            if(e.getSource() == pm.jbtn11)
            {
                //pm.setVisible(false);
                pm.getContentPane().removeAll();
                pm.getContentPane().removeAll();
                pm.getContentPane().removeAll();
                pm.add(pm.panelCostos());
                pm.setVisible(true);

            }   
            if(e.getSource() == pm.jbtn21)
            {
                pm.setVisible(false);
                
                //VENTANA PRINCIPAL
                TableroIU mTablero = new TableroIU();
                JFrame pPrincipal = new JFrame();
                
                pPrincipal.setLayout(new BorderLayout());
                pPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //TABLERO
                mTablero.setNumeroDeColumnas(pm.tableroL.getNoColumnas());
                mTablero.setNumeroDeFilas(pm.tableroL.getNoRenglones());
                mTablero.inicializar();
                pPrincipal.add(mTablero, BorderLayout.CENTER );
                
                //OTROS DATOS
                pPrincipal.add(pm.panelDatos(), BorderLayout.SOUTH);
           
                pPrincipal.setBounds(50,40,700,700);
                pPrincipal.setVisible(true);
                
                
            }
            if(e.getSource() == pm.jbtn31)
            {
               
            }  
    }
     */


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

}
