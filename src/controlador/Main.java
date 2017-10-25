package controlador;

import vista.VentanaSeleccionarMapa;

public class Main  {    
    public static void main (String [ ]args) { 	
    	Controlador controlador = new Controlador();
    	VentanaSeleccionarMapa ventana = new VentanaSeleccionarMapa(controlador);
    	ventana.setVisible(true);

    } 
}