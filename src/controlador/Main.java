package controlador;

import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
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
//import java.awt.event.*;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.File;


public class Main  {
    /*public void ventana(PanelModulo pm, Container c){                 
       c.add(pm);    
    }*/     
    public static void main (String [ ]args) {   
       Main m = new Main();
       Coordenada inicio =  new Coordenada(0, 0);
       Coordenada fin = new Coordenada(0,7);
       Terreno[] terrenos = new Terreno[5];
       Casilla[][] mapa = new Casilla[12][11];
       Jugador[] jugadores = new Jugador[3];
       Tablero tablero = new Tablero(10, 11, mapa, terrenos, jugadores, inicio, fin, 10, 11);
       PanelModulo pm = new PanelModulo(tablero);
       Controlador controla = new Controlador(pm, m);
       /*JFrame frame = new JFrame("Proyecto IA");
       m.ventana(pm, frame);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
       frame.setSize(510 ,280);         
       frame.setVisible(true);*/  
    } 
}