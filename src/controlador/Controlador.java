package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
    Casilla [][]mapaSinDatos;
    ArrayList<Terreno> terrenoSinPesos;
    Tablero tablero;
    
    /*
    public Controlador() {
    }
    */
    
    public void parsearArchivo(String direccion) {
    	ParsearArchivo parseador = new ParsearArchivo(direccion);
    	mapaSinDatos = parseador.getMapa();
    	terrenoSinPesos= parseador.dameTerrenos();
    }
    
    public void inicializaTablero(int noRenglones, int noColumnas, Casilla[][] mapa, Terreno[] terrenos, Jugador[] jugadores, Coordenada inicio, Coordenada fin, int tamanioI, int tamanioJ)
    {
        tablero = new Tablero(noRenglones, noColumnas, mapa, terrenos, jugadores, inicio, fin, tamanioI, tamanioJ);
    }
    
    public ArrayList<Terreno> getTerrenoSinPesos() {
		return terrenoSinPesos;
    }
}
