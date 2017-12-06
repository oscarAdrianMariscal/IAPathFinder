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
   private int mNumeroDeFilas = 15; 
   private int mNumeroDeColumnas = 15;
   private int mSeparacion = 2;
   private static int movimiento;
   private JButton[][] mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
   private JButton[] Columnas= new JButton[mNumeroDeColumnas];
   private JButton[] Filas= new JButton[mNumeroDeColumnas];
   int xActual;
   int yActual;
    
   public void acomodar() {
        int ancho = this.getWidth();
        int alto = this.getHeight()-40;
        int dimensionMenor = Math.min( ancho , alto ); 
        int anchoDeCasilla = dimensionMenor / mNumeroDeColumnas ; 
        int altoDeCasilla = dimensionMenor / mNumeroDeFilas ;
        int xOffset = (ancho - dimensionMenor) / 2 ; 
        int yOffset = (alto - dimensionMenor) / 2 ; 
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
                JButton temp2 = Filas[fila] ;
                temp2.setBounds(10, yOffset +fila* altoDeCasilla+45, anchoDeCasilla - mSeparacion+9, altoDeCasilla - mSeparacion );
               
            }
        for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                JButton temp3 = Columnas[columna] ;
                temp3.setBounds(xOffset + columna * anchoDeCasilla,1, anchoDeCasilla - mSeparacion+4, altoDeCasilla - mSeparacion-10 );
            }
        
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {  
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                JButton temp = mCasillas[fila][columna] ;
                temp.setBounds(xOffset + columna * anchoDeCasilla, yOffset + fila * altoDeCasilla+45, anchoDeCasilla - mSeparacion+1, altoDeCasilla - mSeparacion);
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
                        JButton temp2 = new JButton(String.valueOf(fila+1));
            Filas[fila]=temp2;
                this.add(temp2, fila);
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                JButton temp = new JButton();
                String a = new String();
            if(columna==0){a="A";
            }if(columna==1){ a="B";
            }if(columna==2){a="C";
            }if(columna==3){a="D";
            }if(columna==4){a="E";
            }if(columna==5){a="F";
            }if(columna==6){a="G";
            }if(columna==7){a="H";
            }if(columna==8){a="I";
            }if(columna==9){a="J";
            }if(columna==10){a="K";
            }if(columna==11){a="L";
            }if(columna==12){a="M";
            }if(columna==13){a="N";
            }if(columna==14){a="O";
            }if(columna==15){a="P";
            }
JButton temp3 = new JButton(String.valueOf(a));
                Columnas[columna]=temp3; 
                this.add(temp3, columna);
               
               
                
                 
                temp.addActionListener(this);
                String nombre = new Integer(fila).toString();
                nombre = nombre + "," + new Integer(columna).toString();
                
                //PINTAR NIEBLA
                Color colorNiebla = new Color(229, 230, 250);
                temp.setBackground(colorNiebla);
                
                //AGREGAR INICIO
                if(fila == controlador.getTablero().getInicio().getCoordenadaJ() && columna 
                        == controlador.getTablero().getInicio().getCoordenadaI())
                {
                    //INSERTAR A JUGADOR EN POSICION INICIAL
                    ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
                    temp.setIcon(jugadores.get(0).getImagen());
                    temp.setText("I");
                    controlador.getTablero().getCoordenadaEspecial(fila, columna).setUsado(true);
                    
                    //ACTUALIZAR MOVIMIENTO
                    int x = controlador.getTablero().getCoordenadaEspecial(fila, columna).getCoordenada().getCoordenadaJ();
                    int y = controlador.getTablero().getCoordenadaEspecial(fila, columna).getCoordenada().getCoordenadaI();
                    controlador.getTablero().hacerMovimiento(x,y);
                    
                    //AGREGAR NUMERO DE VISITA
                    controlador.getTablero().getCoordenadaEspecial(fila, columna).setNoVisitas(movimiento);
                    movimiento++;
                    
                    //PINTAR MAPA EN POSICION INICIAL
                    filaI = controlador.getTablero().getInicio().getCoordenadaJ();
                    columnaI = controlador.getTablero().getInicio().getCoordenadaI();                    
                }
                
                //AGREGAR FIN
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
       //OBTENER TERRENO DE POSICION ARRIBA 
       int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).getTerreno().getIdTerreno();
       //OBTENER JUGADOR ACTUAL
       ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
       //OBTENER COSTOS DE JUGADOR ACTUAL
       ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
       
       //OBTENER POSICION ACTUAL DE JUGADOR
       int posicion = 0;
       for(int i=0; i<terrenos.size(); i++)
       {
           if(terrenos.get(i).getIdTerreno() == idTerreno)
           {
               posicion = i;
           }
       }
       
       //SI JUGADOR SE PUEDE MOVER A ESE TERRENO
       if(terrenos.get(posicion).getCosto() != -1)
       {
            //PINTAR COLOR DE VECINOS
            Casilla[][] aux = controlador.getTablero().getMapa();
            pintarColor(renglon-1, columna, mCasillas, aux);
            
            //PINTAR IMAGEN DE JUGADOR EN POSICION ARRIBA
            ImageIcon icono = new ImageIcon("");;  
            mCasillas[renglon][columna].setIcon(icono);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
            controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).setNoVisitas(movimiento);
            movimiento++;
            controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).setUsado(true);
            mCasillas[renglon-1][columna].setIcon(jugadores.get(0).getImagen());

            //ACTUALIZAR MOVIMIENTO
            int x = controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).getCoordenada().getCoordenadaJ();
            int y = controlador.getTablero().getCoordenadaEspecial(renglon-1, columna).getCoordenada().getCoordenadaI();
            controlador.getTablero().hacerMovimiento(x,y);
            
            //SI ES POSICION FINAL A LA QUE SE MOVIO TERMINA Y LANZA MENSAJE DE TERMINADO
            if(controlador.getTablero().getFin().getCoordenadaJ() == renglon-1 && controlador.getTablero().getFin().getCoordenadaI() == columna)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                //abrirArbol();
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
       }
    }
    
    public void abrirArbol(JTree arbol,String titulo) {
    	VentanaArbol ventana = new VentanaArbol(arbol,titulo);
        ventana.setVisible(true);
        controlador.getTablero().imprimirArbol();
    }
    
    public void hacerMovimientoArbitrario (int x, int y ) {
    	ImageIcon icono = new ImageIcon("");
        mCasillas[yActual][xActual].setIcon(icono);
        ImageIcon jugador = controlador.getArregloJugadores().get(0).getImagen();
        mCasillas[y][x].setIcon(jugador);
        xActual = x;
        yActual = y;
    	
    }
    
    public void moverAbajo(int renglon, int columna)
    {
        //OBTENER TERRENO DE POSICION ABAJO
        int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).getTerreno().getIdTerreno();
        //OBTENER JUGADOR ACTUAL
        ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
        //OBTENER COSTOS DE JUGADOR ACTUAL
        ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
        
        //OBTENER POSICION ACTUAL DE JUGADOR
        int posicion = 0;
        for(int i=0; i<terrenos.size(); i++)
        {
            if(terrenos.get(i).getIdTerreno() == idTerreno)
            {
               posicion = i;
            }
        }
        
        //SI JUGADOR SE PUEDE MOVER A ESE TERRENO
        if(terrenos.get(posicion).getCosto() != -1)
        {
           //PINTAR COLOR DE VECINOS
           Casilla[][] aux = controlador.getTablero().getMapa();
           pintarColor(renglon+1, columna, mCasillas, aux);
           
           //PINTAR IMAGEN DE JUGADOR EN POSICION ABAJO
           ImageIcon icono = new ImageIcon("");;  
           mCasillas[renglon][columna].setIcon(icono);
           controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
           controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).setNoVisitas(movimiento);
           movimiento++;
           controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).setUsado(true);
           mCasillas[renglon+1][columna].setIcon(jugadores.get(0).getImagen());
           
           //ACTUALIZAR MOVIMIENTO
           int x = controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).getCoordenada().getCoordenadaJ();
           int y = controlador.getTablero().getCoordenadaEspecial(renglon+1, columna).getCoordenada().getCoordenadaI();
           controlador.getTablero().hacerMovimiento(x,y);
       
           //SI ES POSICION FINAL A LA QUE SE MOVIO TERMINA Y LANZA MENSAJE DE TERMINADO
           if(controlador.getTablero().getFin().getCoordenadaJ() == renglon+1 && controlador.getTablero().getFin().getCoordenadaI() == columna)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                //abrirArbol();
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
        }
    }
    
    public void moverIzquierda(int renglon, int columna)
    { 
        //OBTENER TERRENO DE POSICION IZQUIERDA
        int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).getTerreno().getIdTerreno();
        //OBTENER JUGADOR ACTUAL
        ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
        //OBTENER COSTOS DE JUGADOR ACTUAL
        ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
        
        //OBTENER POSICION ACTUAL DE JUGADOR
        int posicion = 0;
        for(int i=0; i<terrenos.size(); i++)
        {
            if(terrenos.get(i).getIdTerreno() == idTerreno)
            {
               posicion = i;
            }
        }
        
        //SI JUGADOR SE PUEDE MOVER A ESE TERRENO
        if(terrenos.get(posicion).getCosto() != -1)
        {
            //PINTAR COLOR DE VECINOS
            Casilla[][] aux = controlador.getTablero().getMapa();
            pintarColor(renglon, columna-1, mCasillas, aux);
            
            //PINTAR IMAGEN DE JUGADOR EN POSICION IZQUIERDA
            ImageIcon icono = new ImageIcon("");;  
            mCasillas[renglon][columna].setIcon(icono);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).setNoVisitas(movimiento);
            movimiento++;
            controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).setUsado(true);
            mCasillas[renglon][columna-1].setIcon(jugadores.get(0).getImagen());
            
            //ACTUALIZAR MOVIMIENTO
            int x = controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).getCoordenada().getCoordenadaJ();
            int y = controlador.getTablero().getCoordenadaEspecial(renglon, columna-1).getCoordenada().getCoordenadaI();
            controlador.getTablero().hacerMovimiento(x,y);
            
            //SI ES POSICION FINAL A LA QUE SE MOVIO TERMINA Y LANZA MENSAJE DE TERMINADO
            if(controlador.getTablero().getFin().getCoordenadaJ() == renglon && controlador.getTablero().getFin().getCoordenadaI() == columna-1)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                //abrirArbol();
                controlador.eliminarJugador(0);
                controlador.reiniciaArregloVisitas();
                controlador.getTablero().reiniciarCasillasUsadas();
            }
        }
    }
    
    public void moverDerecha(int renglon, int columna)
    {
        //OBTENER TERRENO DE POSICION DERECHA
        int idTerreno = controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).getTerreno().getIdTerreno();
        //OBTENER JUGADOR ACTUAL
        ArrayList<Jugador> jugadores = controlador.getArregloJugadores();
        //OBTENER COSTOS DE JUGADOR ACTUAL
        ArrayList<Terreno> terrenos = jugadores.get(0).getTerrenosPesos();
        
        //OBTENER POSICION ACTUAL DE JUGADOR
        int posicion = 0;
        for(int i=0; i<terrenos.size(); i++)
        {
            if(terrenos.get(i).getIdTerreno() == idTerreno)
            {
               posicion = i;
            }
        }
        
        //SI JUGADOR SE PUEDE MOVER A ESE TERRENO
        if(terrenos.get(posicion).getCosto() != -1)
        {
            //PINTAR COLOR DE VECINOS
            Casilla[][] aux = controlador.getTablero().getMapa();
            pintarColor(renglon, columna+1, mCasillas, aux);
            
            //PINTAR IMAGEN DE JUGADOR EN POSICION DERECHA
            ImageIcon icono = new ImageIcon("");;  
            mCasillas[renglon][columna].setIcon(icono);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna).setUsado(false);
            controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).setNoVisitas(movimiento);
            movimiento++;
            controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).setUsado(true);
            mCasillas[renglon][columna+1].setIcon(jugadores.get(0).getImagen());
            
            //ACTUALIZAR MOVIMIENTO
            int x = controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).getCoordenada().getCoordenadaJ();
            int y = controlador.getTablero().getCoordenadaEspecial(renglon, columna+1).getCoordenada().getCoordenadaI();
            controlador.getTablero().hacerMovimiento(x,y);          
            
            //SI ES POSICION FINAL A LA QUE SE MOVIO TERMINA Y LANZA MENSAJE DE TERMINADO
            if(controlador.getTablero().getFin().getCoordenadaJ() == renglon && controlador.getTablero().getFin().getCoordenadaI() == columna+1)
            {
                JOptionPane.showMessageDialog(null, "Felicidades, ha llegado a la meta");
                //abrirArbol();
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
