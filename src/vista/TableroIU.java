package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import modelo.Casilla;
import modelo.Coordenada;
import modelo.Jugador;
import modelo.Terreno;

public class TableroIU extends JPanel implements ComponentListener, ActionListener{
    
   Controlador controlador; 
   private int mNumeroDeFilas = 16; 
   private int mNumeroDeColumnas = 16;
   private int mSeparacion = 2;
   private static int movimiento;
   private JButton[][] mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
    
   public void acomodar() {
        int ancho = this.getWidth();
        int alto = this.getHeight();
        int dimensionMenor = Math.min( ancho , alto ); 
        int anchoDeCasilla = dimensionMenor / mNumeroDeColumnas ; 
        int altoDeCasilla = dimensionMenor / mNumeroDeFilas ;
        int xOffset = (ancho - dimensionMenor) / 2 ; 
        int yOffset = (alto - dimensionMenor) / 2 ; 
        
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {  
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                JButton temp = mCasillas[fila][columna] ;
                temp.setBounds(xOffset + columna * anchoDeCasilla, yOffset + fila * altoDeCasilla, anchoDeCasilla - mSeparacion, altoDeCasilla - mSeparacion );
            }
        }
    }
    public TableroIU(Controlador controlador) {        
        this.setBackground(Color.WHITE);
        this.addComponentListener(this);
        this.setLayout(null);   
        this.controlador = controlador;  
    }

    public void inicializar() {
        movimiento = 1; 
        int filaI = 0, columnaI = 0;
        Casilla[][] aux = controlador.getTablero().getMapa();
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                JButton temp = new JButton();
                temp.addActionListener(this);
                String nombre = new Integer(fila).toString();
                nombre = nombre + "," + new Integer(columna).toString();
                
                //PINTAR COLOR
                //Color color = aux[fila][columna].getTerreno().getColorRgb();
                //temp.setBackground(color);
                
                //PINTAR NIEBLA
                Color colorNiebla = new Color(229, 230, 250);
                temp.setBackground(colorNiebla);
                
                //AGREGAR INICIO Y FIN
                if(fila == controlador.getTablero().getInicio().getCoordenadaJ() && columna 
                        == controlador.getTablero().getInicio().getCoordenadaI())
                {
                    ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
                    temp.setIcon(jugadores.get(0).getImagen());
                    temp.setText("I");
                    controlador.getTablero().getCoordenadaEspecial(fila, columna).setUsado(true);
                     //ACTUALIZAR MOVIMIENTO
                    Coordenada c = new Coordenada(fila, columna);
                    Casilla actual = new Casilla(false, false, c, controlador.getTablero().getCoordenadaEspecial(fila, columna).getTerreno());
                    controlador.getTablero().hacerMovimiento(actual);
                    
                    controlador.getTablero().getCoordenadaEspecial(fila, columna).setNoVisitas(movimiento);
                    movimiento++;
                    
                    //POSICIONES INICIALES PARA PINTAR MAPA
                    filaI = controlador.getTablero().getInicio().getCoordenadaJ();
                    columnaI = controlador.getTablero().getInicio().getCoordenadaI();                    
                }
                
                if(fila == controlador.getTablero().getFin().getCoordenadaJ() && columna 
                        == controlador.getTablero().getFin().getCoordenadaI())
                {
                    temp.setText("F");
                }
                mCasillas[fila][columna] = temp;
                mCasillas[fila][columna].setActionCommand(nombre);
                this.add(temp);
            }
        }
        
        //PINTAR CON COLORES ORIGINALES CASILLAS CERCANAS AL INICIO
        pintarColor(filaI, columnaI, mCasillas, aux);
    }
    
    public void moverArriba(int renglon, int columna)
    {
       int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).getTerreno().getIdTerreno();
       ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
       ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
       int posicion = 0;
       for(int i=0; i<terrenos.size(); i++)
       {
           if(terrenos.get(i).getIdTerreno() == idTerreno)
           {
               posicion = i;
           }
       }
       if(terrenos.get(posicion).getCosto() != -1)
       {
            //PARA PINTAR COLOR ORIGINAL DE VECINOS
            Casilla[][] aux = controlador.getTablero().getMapa();
            pintarColor(renglon-1, columna, mCasillas, aux);
            
            ImageIcon icono = new ImageIcon("");;  
            mCasillas[renglon][columna].setIcon(icono);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
            controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).setNoVisitas(movimiento);
            movimiento++;
            controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).setUsado(true);
            //ACTUALIZAR MOVIMIENTO
            Coordenada c = new Coordenada(renglon-1, columna);
            Casilla actual = new Casilla(false, false, c, controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).getTerreno());
            controlador.getTablero().hacerMovimiento(actual);
            mCasillas[renglon-1][columna].setIcon(jugadores.get(0).getImagen());
            
            if(controlador.getTablero().getFin().getCoordenadaJ() == renglon-1 && controlador.getTablero().getFin().getCoordenadaI() == columna)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
       }
    }
    
    public void moverAbajo(int renglon, int columna)
    {
        int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).getTerreno().getIdTerreno();
        ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
        ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
        int posicion = 0;
        for(int i=0; i<terrenos.size(); i++)
        {
            if(terrenos.get(i).getIdTerreno() == idTerreno)
            {
               posicion = i;
            }
        }
        if(terrenos.get(posicion).getCosto() != -1)
        {
           //PARA PINTAR COLOR ORIGINAL DE VECINOS
           Casilla[][] aux = controlador.getTablero().getMapa();
           pintarColor(renglon+1, columna, mCasillas, aux);
            
           ImageIcon icono = new ImageIcon("");;  
           mCasillas[renglon][columna].setIcon(icono);
           controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
           controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).setNoVisitas(movimiento);
           movimiento++;
           controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).setUsado(true);
           //ACTUALIZAR MOVIMIENTO
           Coordenada c = new Coordenada(renglon+1, columna);
           Casilla actual = new Casilla(false, false, c, controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).getTerreno());
           controlador.getTablero().hacerMovimiento(actual);
           mCasillas[renglon+1][columna].setIcon(jugadores.get(0).getImagen());
           
           if(controlador.getTablero().getFin().getCoordenadaJ() == renglon+1 && controlador.getTablero().getFin().getCoordenadaI() == columna)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
        }
    }
    
    public void moverIzquierda(int renglon, int columna)
    { 
        int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).getTerreno().getIdTerreno();
        ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
        ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
        int posicion = 0;
        for(int i=0; i<terrenos.size(); i++)
        {
            if(terrenos.get(i).getIdTerreno() == idTerreno)
            {
               posicion = i;
            }
        }
        if(terrenos.get(posicion).getCosto() != -1)
        {
            //PARA PINTAR COLOR ORIGINAL DE VECINOS
            Casilla[][] aux = controlador.getTablero().getMapa();
            pintarColor(renglon, columna-1, mCasillas, aux);
            
            ImageIcon icono = new ImageIcon("");;  
            mCasillas[renglon][columna].setIcon(icono);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).setNoVisitas(movimiento);
            movimiento++;
            controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).setUsado(true);
            //ACTUALIZAR MOVIMIENTO
            Coordenada c = new Coordenada(renglon, columna-1);
            Casilla actual = new Casilla(false, false, c, controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).getTerreno());
            controlador.getTablero().hacerMovimiento(actual);
            mCasillas[renglon][columna-1].setIcon(jugadores.get(0).getImagen());
            
            if(controlador.getTablero().getFin().getCoordenadaJ() == renglon && controlador.getTablero().getFin().getCoordenadaI() == columna-1)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
        }
    }
    
    public void moverDerecha(int renglon, int columna)
    {
        int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).getTerreno().getIdTerreno();
        ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
        ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
        int posicion = 0;
        for(int i=0; i<terrenos.size(); i++)
        {
            if(terrenos.get(i).getIdTerreno() == idTerreno)
            {
               posicion = i;
            }
        }
        if(terrenos.get(posicion).getCosto() != -1)
        {
            //PARA PINTAR COLOR ORIGINAL DE VECINOS
            Casilla[][] aux = controlador.getTablero().getMapa();
            pintarColor(renglon, columna+1, mCasillas, aux);
            
            ImageIcon icono = new ImageIcon("");;  
            mCasillas[renglon][columna].setIcon(icono);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).setNoVisitas(movimiento);
            movimiento++;
            controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).setUsado(true);
            //ACTUALIZAR MOVIMIENTO
            Coordenada c = new Coordenada(renglon, columna+1);
            Casilla actual = new Casilla(false, false, c, controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).getTerreno());
            controlador.getTablero().hacerMovimiento(actual);
            mCasillas[renglon][columna+1].setIcon(jugadores.get(0).getImagen());
            
            if(controlador.getTablero().getFin().getCoordenadaJ() == renglon && controlador.getTablero().getFin().getCoordenadaI() == columna+1)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
        }   
    }
  
    public void pintarColor(int fila, int columna, JButton[][] temp, Casilla[][] aux)
    {
        //ARRIBA
        if(fila !=0)
        {
            Color color = aux[fila-1][columna].getTerreno().getColorRgb();
            temp[fila-1][columna].setBackground(color);
            controlador.getTablero().getCoordenadaEspecial(fila-1, columna).setTieneNiebla(false);
        }
        
        //ABAJO
        if(fila !=controlador.getTablero().getNoRenglones()-1)
        {
            Color color = aux[fila+1][columna].getTerreno().getColorRgb();
            temp[fila+1][columna].setBackground(color);
            controlador.getTablero().getCoordenadaEspecial(fila+1, columna).setTieneNiebla(false);
        }  
        
        //IZQUIERDA
        if(columna !=0)
        {
            Color color = aux[fila][columna-1].getTerreno().getColorRgb();
            temp[fila][columna-1].setBackground(color);
            controlador.getTablero().getCoordenadaEspecial(fila, columna-1).setTieneNiebla(false);
        }
        
        //DERECHA
        if(columna != controlador.getTablero().getNoColumnas()-1)
        {
            Color color = aux[fila][columna+1].getTerreno().getColorRgb();
            temp[fila][columna+1].setBackground(color);
            controlador.getTablero().getCoordenadaEspecial(fila, columna+1).setTieneNiebla(false);
        }
        
        //PARA CASILLA ACTUAL
        Color color = aux[fila][columna].getTerreno().getColorRgb();
        temp[fila][columna].setBackground(color);
        controlador.getTablero().getCoordenadaEspecial(fila, columna).setTieneNiebla(false);
    }

    public void componentResized(ComponentEvent e) {     
        this.acomodar();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void setNumeroDeFilas(int mNumeroDeFilas) {
        this.mNumeroDeFilas = mNumeroDeFilas;
    }

    public int getNumeroDeFilas() {
        return mNumeroDeFilas;
    }

    public void setNumeroDeColumnas(int mNumeroDeColumnas) {
        this.mNumeroDeColumnas = mNumeroDeColumnas;
    }

    public int getNumeroDeColumnas() {
        return mNumeroDeColumnas;
    }

    public void actionPerformed(ActionEvent e) {        
        
        if( e.getSource() instanceof JButton ) {
            JButton temp = (JButton) e.getSource() ;
            
            //CALCULAR POSICION
            String casilla = temp.getActionCommand();
            String opciones[];
            opciones = casilla.split(",");
            Coordenada coordenada = new Coordenada(Integer.parseInt(opciones[0]), Integer.parseInt(opciones[1]));
            JOptionPane.showMessageDialog(null, controlador.muestraDatosCasilla(coordenada));
        }  
    }
}
