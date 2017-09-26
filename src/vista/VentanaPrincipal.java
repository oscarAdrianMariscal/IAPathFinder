package vista;

import controlador.Controlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Tablero;

public class VentanaPrincipal extends JFrame {
    
    TableroIU tablero = new TableroIU();
    Controlador controlador;
    JPanel jDatos = new JPanel();
   
    JTextArea txtarea31;
    JTextField jtf31;
    JTextField jtf32;
    JButton jbtn31;
    
    public VentanaPrincipal(Controlador controlador)
    {
        this.controlador = controlador;
        tablero = new TableroIU();
        
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //TABLERO
        tablero.setNumeroDeColumnas(11);
        tablero.setNumeroDeFilas(10);
        tablero.inicializar();
        this.add(tablero, BorderLayout.CENTER);
        
        //OTROS DATOS
        this.add(panelDatos(), BorderLayout.SOUTH);
           
        this.setBounds(50,40,700,700);
        //this.setVisible(true);
    }
    
     public JPanel panelDatos(){
        
        jDatos = new JPanel();
       
        jDatos.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //AGREGANDO COMPONENTES DE PANEL pDatos
        JLabel pDlab1 = new JLabel("Verificar Terreno", SwingConstants.CENTER);
        pDlab1.setFont(new java.awt.Font("Tahoma", 0, 20));
        
        txtarea31 = new JTextArea(5,15);
        txtarea31.setEditable(false);
        //pane31 = new JScrollPane(txtarea31, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        jtf31 = new JTextField("Renglón: ",15);
        jtf32 = new JTextField("Columna: ",15);
        
        jDatos.add(pDlab1);
        jDatos.add(jtf31);
        jDatos.add(jtf32);
        jDatos.add(jbtn31);
        jDatos.add(txtarea31);

        jbtn31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return jDatos;
    }
}