package vista;

import controlador.Controlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Tablero;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {
    
    TableroIU tablero = new TableroIU();
    Controlador controlador;
    JPanel jDatos;
    private JPanel contentPane;
   
    JTextArea txtarea31;
    JTextField jtf31;
    JTextField jtf32;
    
    public VentanaPrincipal(Controlador controlador)
    {
        this.controlador = controlador;
        tablero = new TableroIU();
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50,40,700,700);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        //TABLERO
        tablero.setNumeroDeColumnas(11);
        tablero.setNumeroDeFilas(10);
        tablero.inicializar();
        contentPane.add(tablero, BorderLayout.CENTER);
        
        //OTROS DATOS
        
        jDatos = new JPanel();
        jDatos.setBorder(BorderFactory.createLineBorder(Color.black));
        //AGREGANDO COMPONENTES DE PANEL pDatos
        JLabel pDlab1 = new JLabel("Verificar Terreno", SwingConstants.CENTER);
        pDlab1.setFont(new java.awt.Font("Tahoma", 0, 20));
        txtarea31 = new JTextArea(5,15);
        txtarea31.setEditable(false);
        jtf31 = new JTextField("Renglón: ",15);
        jtf32 = new JTextField("Columna: ",15);
        jDatos.add(pDlab1);
        jDatos.add(jtf31);
        jDatos.add(jtf32);
        
        JButton jbtn31 = new JButton("Avanzar");
        jbtn31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jDatos.add(jbtn31);
        jDatos.add(txtarea31);
        contentPane.add(jDatos, BorderLayout.SOUTH); 
    }
    
    public static void main(String[] args) {
		
        Controlador controlador = new Controlador();
	VentanaPrincipal frame = new VentanaPrincipal(controlador);
	frame.setVisible(true);
				
    }
}