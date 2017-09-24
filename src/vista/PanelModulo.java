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
import modelo.Tablero;


public class PanelModulo extends JFrame{
    
    public Tablero tableroL;
    
    JPanel jDatos;
    
    //ELEMENTOS DE INTERFAZ PARSEAR
    JLabel jlab11;
    JTextField jtf11, jtf12;
    public JButton jbtn11 = new JButton("ENVIAR");
    
    //ELEMENTOS DE INTERFAZ COSTOS
    public JButton jbtn21 = new JButton("ENVIAR");
    
    //ELEMENTOS DE INTERFAZ DATOS
    public JButton jbtn31 = new JButton("ENVIAR");
    JTextArea txtarea31;
    JScrollPane pane31;
 
    
    public PanelModulo(Tablero tablero){
        //super("Proyecto IA");
        this.tableroL = tablero;
        
        //INTERFAZ 1
        this.setLayout(new FlowLayout());
        jlab11 = new JLabel("CRUCIGRAMA", SwingConstants.CENTER);
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
        setBounds(0,40,400,500);
        setVisible( true );
    }
    
    public JPanel tablero(int nFilas, int nColumnas)
    {
        TableroIU tablero = new TableroIU();
        tablero.setNumeroDeFilas(nFilas);
        tablero.setNumeroDeColumnas(nColumnas);
        tablero.inicializar();
        
        return tablero;
    }
    
    public JPanel panelCostos(){
        JPanel pCostos = new JPanel();

        //Agregando los componentes a pCostos.
        pCostos.add(jbtn21);
        return pCostos;
    }
    
    public JPanel panelPrincipal(){
        JPanel pPrincipal = new JPanel();
        
        JPanel tablero = tablero(tableroL.getNoRenglones(), tableroL.getNoColumnas());
        jDatos = new JPanel();
        
        //AGREGANDO COMPONENTES DE PANEL pDatos
        JLabel pDlab1 = new JLabel("Verificar Terreno", SwingConstants.CENTER);
        pDlab1.setFont(new java.awt.Font("Tahoma", 0, 20));
        jDatos.add(pDlab1);
        
        txtarea31 = new JTextArea(5,15);
        txtarea31.setEditable(false);
        pane31 = new JScrollPane(txtarea31, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jDatos.add(pane31);
        
        
        //AGREGANDO TODOS LOS COMPONENTES A PANEL calculatoP, 2, 3, resultadoP, y JButton igualA; 
        pPrincipal.setLayout(null);
        pPrincipal.add(tablero);
        tablero.setBounds(20,15,1000,1000);
        pPrincipal.add(jDatos);
        jDatos.setBounds(180,15, 50, 132);
        jDatos.setBorder(BorderFactory.createLineBorder(Color.black));
        
        return pPrincipal;
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

