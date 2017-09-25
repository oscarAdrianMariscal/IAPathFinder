package controlador;

import vista.VentanaSeleccionarMapa;

public class Main  {    
    public static void main (String [ ]args) {
    	/*
       Coordenada inicio =  new Coordenada(0, 0);
       Coordenada fin = new Coordenada(0,7);
       Terreno[] terrenos = new Terreno[5];
       Casilla[][] mapa = new Casilla[12][11];
       Jugador[] jugadores = new Jugador[3];
       Tablero tablero = new Tablero(10, 11, mapa, terrenos, jugadores, inicio, fin, 10, 11);
       PanelModulo pm = new PanelModulo(tablero);
       Controlador controla = new Controlador(pm);  
       */
    	
    	Controlador controlador = new Controlador();
    	VentanaSeleccionarMapa ventana = new VentanaSeleccionarMapa(controlador);
    	ventana.setVisible(true);

    } 
}