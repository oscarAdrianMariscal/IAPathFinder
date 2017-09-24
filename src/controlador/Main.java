package controlador;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import modelo.Casilla;
import modelo.Coordenada;
import modelo.Jugador;
import modelo.Tablero;
import modelo.Terreno;
import vista.PanelModulo;

public class Main  {    
    public static void main (String [ ]args) {   
       Coordenada inicio =  new Coordenada(0, 0);
       Coordenada fin = new Coordenada(0,7);
       Terreno[] terrenos = new Terreno[5];
       Casilla[][] mapa = new Casilla[12][11];
       Jugador[] jugadores = new Jugador[3];
       Tablero tablero = new Tablero(10, 11, mapa, terrenos, jugadores, inicio, fin, 10, 11);
       PanelModulo pm = new PanelModulo(tablero);
       Controlador controla = new Controlador(pm);  
    } 
}