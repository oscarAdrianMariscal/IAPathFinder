package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.awt.*;
import modelo.Casilla;
import modelo.Coordenada;
import modelo.Jugador;
import modelo.Tablero;
import modelo.Terreno;


public class PanelModulo extends JFrame{
    
    public Tablero tableroL;
    
    JPanel jDatos;
    
    //ELEMENTOS DE INTERFAZ PARSEAR
    JLabel jlab11;
    JTextField jtf11, jtf12;
    public JButton jbtn11 = new JButton("ENVIAR");
    
    //ELEMENTOS DE INTERFAZ COSTOS
    public JButton jbtn21 = new JButton("ENVIAR");
    JLabel jlab21;
    
    //ELEMENTOS DE INTERFAZ DATOS
    public JButton jbtn31 = new JButton("ENVIAR");
    JTextArea txtarea31;
    JScrollPane pane31;
    JTextField jtf31, jtf32;
 
    
    public PanelModulo(Tablero tablero){
        super("Proyecto IA");
        this.tableroL = tablero;
        
        //INTERFAZ 1
        this.setLayout(new FlowLayout());
        jlab11 = new JLabel("INTERFAZ PARSEAR", SwingConstants.CENTER);
        jlab11.setFont(new java.awt.Font("Tahoma", 0, 20));
        jtf11 = new JTextField("Nombre: ",30);
        //jtf1.setHorizontalAlignment(SwingConstants.CENTER); 
        jtf11.setHorizontalAlignment(SwingConstants.CENTER);
        jtf12 = new JTextField("Nombre: ",30);
        //jtf1.setHorizontalAlignment(SwingConstants.CENTER); 
        jtf12.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.add(jlab11);
        this.add(jtf11);
        this.add(jtf12);
        this.add(jbtn11);
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBounds(44,40,400,200);
        setVisible( true );
    }
    
    public JPanel panelCostos(){
        JPanel pCostos = new JPanel();
        jlab21 = new JLabel("INTERFAZ COSTOS", SwingConstants.CENTER);
        jlab21.setFont(new java.awt.Font("Tahoma", 0, 20));

        //Agregando los componentes a pCostos.
        pCostos.add(jlab21);
        pCostos.add(jbtn21);
        return pCostos;
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
        
        return jDatos;
    }
    
    public void escuchaBotonOne(ActionListener ae) {
        jbtn11.setActionCommand("1");
        jbtn11.addActionListener(ae);
    }
    
    public void escuchaBotonTwo(ActionListener ae) {
        jbtn21.setActionCommand("2");
        jbtn21.addActionListener(ae);
    }
    
    public void escuchaBotonThree(ActionListener ae) {
        jbtn31.setActionCommand("3");
        jbtn31.addActionListener(ae);
    }
 }

