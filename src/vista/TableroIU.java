package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;
import javax.swing.*;
import modelo.Casilla;
import modelo.Coordenada;

public class TableroIU extends JPanel implements ComponentListener, ActionListener{
    
   Controlador controlador; 
   private JButton[][] mCasillas = null ;
   private int mNumeroDeFilas = 10 ; 
   private int mNumeroDeColumnas = 10 ;
   private int mSeparacion = 2;
   private static int movimiento;
    
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
                if(columna == 0)
                {
                    JLabel temp2 = new JLabel("1");
                    temp2.setBounds(xOffset + columna * anchoDeCasilla - 10, yOffset + fila * altoDeCasilla, anchoDeCasilla - mSeparacion, altoDeCasilla - mSeparacion );
                }
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
        
        mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
        movimiento = 1; 
        
        Casilla[][] aux = controlador.getTablero().getMapa();
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                JButton temp = new JButton();
                temp.addActionListener(this);
                String nombre = new Integer(fila).toString();
                nombre = nombre + "," + new Integer(columna).toString();
                //temp.setText("[" + fila + "],[" + columna + "]");
                
                //PINTAR COLOR
                Color color = aux[fila][columna].getTerreno().getColorRgb();
                temp.setBackground(color);
                
                //AGREGAR INICIO Y FIN
                if(fila == controlador.getTablero().getInicio().getCoordenadaI() && columna 
                        == controlador.getTablero().getInicio().getCoordenadaJ())
                {
                    //ImageIcon icono = new ImageIcon("agua.gif");;
                    temp.setIcon(new ImageIcon(TableroIU.class.getResource("Homero.gif")));
                    temp.setText("INICIO");
                    controlador.getTablero().getCoordenadaEspecial(fila, columna).setUsado(true);
                    controlador.getTablero().getCoordenadaEspecial(fila, columna).setNoVisitas(movimiento);
                    movimiento++;
                }
                
                if(fila == controlador.getTablero().getFin().getCoordenadaI() && columna 
                        == controlador.getTablero().getFin().getCoordenadaJ())
                {
                    //ImageIcon icono = new ImageIcon("Homero.gif");;
                    //temp.setIcon(icono);
                    temp.setText("FINAL");
                }
                mCasillas[fila][columna] = temp;
                mCasillas[fila][columna].setActionCommand(nombre);
                this.add(temp);
            }
        }
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

    private Random r = new Random();
    
    private Color getRandColor() {
    
        return new Color( r.nextInt(255), r.nextInt(255), r.nextInt(255) );
        
    }

    public void actionPerformed(ActionEvent e) {        
        
        if( e.getSource() instanceof JButton ) {
            JButton temp = (JButton) e.getSource() ;
            //temp.setBackground( getRandColor() );
            //temp.setBackground(Color.cyan);
            
            //CALCULAR POSICION
            String casilla = temp.getActionCommand();
            String opciones[];
            opciones = casilla.split(",");
            Coordenada coordenada = new Coordenada(Integer.parseInt(opciones[0]), Integer.parseInt(opciones[1]));
            JOptionPane.showMessageDialog(null, controlador.muestraDatosCasilla(coordenada));
        }  
    }
}