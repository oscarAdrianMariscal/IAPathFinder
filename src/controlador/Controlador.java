package controlador;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JButton;
import vista.PanelModulo;

public class Controlador implements ActionListener{
 
    PanelModulo pm;
    Main m;
    private Random r = new Random();
    
    public Controlador(PanelModulo pm, Main m) {
        this.pm = pm;
        this.m = m;
    }
    
    private Color getRandColor() {
    
        return new Color( r.nextInt(255), r.nextInt(255), r.nextInt(255) );
        
    }
    
     @Override
     public void actionPerformed(ActionEvent e) {
            if(e.getSource() == pm.jbtn11)
            {
                pm.getContentPane().removeAll();
                pm.add(pm.panelCostos());
                pm.setVisible(true);

            }   
            if(e.getSource() == pm.jbtn21)
            {
                
            }
            if(e.getSource() == pm.jbtn31)
            {
               
            }  
    }
}
