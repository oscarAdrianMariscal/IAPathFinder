package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelo.Casilla;
import modelo.Coordenada;
import modelo.Jugador;
import modelo.Tablero;
import modelo.Terreno;
import vista.PanelModulo;
import vista.TableroIU;

public class Controlador implements ActionListener{
 
    PanelModulo pm;
    Main m;
    private Random r = new Random();
    
    public Controlador(PanelModulo pm) {
        this.pm = pm;
        
        pm.escuchaBotonOne(this);
        pm.escuchaBotonTwo(this);
    }
    
    private Color getRandColor() {
    
        return new Color( r.nextInt(255), r.nextInt(255), r.nextInt(255) );
        
    }
    
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
}
