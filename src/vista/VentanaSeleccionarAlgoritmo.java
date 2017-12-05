package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class VentanaSeleccionarAlgoritmo extends JFrame{
    
    Controlador controlador;
    JPanel jDatos;
    private JPanel contentPane;    
    private JButton btnNewButton;
    private JComboBox combo, combo2;
    JLabel jlab1;
    
    public VentanaSeleccionarAlgoritmo(Controlador controlador)
    {
        super("Algoritmo");
        this.controlador = controlador;
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500,300,300,200);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        jlab1 = new JLabel("ELEGIR ALGORITMO"); 
        jlab1.setFont(new Font("Dialog", Font.ITALIC, 20));
        combo = new JComboBox();
		combo.addItem("Backtracking");
		combo.addItem("A Estrella");
                
        combo2 = new JComboBox();
		combo2.addItem("Distancia Euclideana");
		combo2.addItem("Distancia Manhattan");
                
        jDatos = new JPanel();
        jDatos.setBorder(BorderFactory.createLineBorder(Color.black));
        jDatos.setLayout(new BorderLayout());
        jDatos.add(combo2, BorderLayout.NORTH);
        btnNewButton = new JButton("Avanzar");
        //jDatos.add(btnNewButton);
        contentPane.add(jlab1/*, BorderLayout.NORTH*/);
        contentPane.add(combo/*, BorderLayout.CENTER*/);
        contentPane.add(btnNewButton/*, BorderLayout.SOUTH*/);
        contentPane.add(jDatos);
        jDatos.setVisible(false);
        
        
        combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(combo.getSelectedItem().toString() == "A Estrella")
                                {
                                    jDatos.setVisible(true);
                                }
                                else
                                {
                                    jDatos.setVisible(false);
                                }
			}
		});
       
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    if(combo.getSelectedItem().toString() == "A Estrella")
                    {
                        controlador.setTipoAlgoritmo(1);
                        if(combo2.getSelectedItem().toString() == "Distancia Euclideana")
                        {
                            controlador.setTipoDistancia(0);
                        }
                        else
                        {
                            controlador.setTipoDistancia(1);
                        }
                    }
                    else 
                    {
                        controlador.setTipoAlgoritmo(0);
                    }
                    VentanaPrincipal ventanaPrincipal =  new VentanaPrincipal(controlador);
                    controlador.setvP(ventanaPrincipal);
                    ventanaPrincipal.setVisible(true);
                    getContentPane().setVisible(false);
        	}
        });
    }
    
    public static void main(String[] args) {	
        Controlador controlador = new Controlador();
	VentanaSeleccionarAlgoritmo frame = new VentanaSeleccionarAlgoritmo(controlador);
	frame.setVisible(true);			
    }
}