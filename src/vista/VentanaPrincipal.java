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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.Coordenada;

public class VentanaPrincipal extends JFrame implements KeyListener {
    
    TableroIU tableroIU;
    Controlador controlador;
    JPanel jDatos;
    private JPanel contentPane;
    JScrollPane scrollPaneAreaEntrada;
   
    JTextArea txtarea31;
    JTextField jtf31;
    JTextField jtf32;
    
    public VentanaPrincipal(Controlador controlador)
    {
        this.controlador = controlador;
        tableroIU = new TableroIU(controlador);
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50,40,500,500);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        //TABLERO
        tableroIU.setNumeroDeColumnas(controlador.getTamanioTerrenoColumnas());
        tableroIU.setNumeroDeFilas(controlador.getTamanioTerrenoRenglones());
        tableroIU.inicializar();
        contentPane.add(tableroIU, BorderLayout.CENTER);
        
        //OTROS DATOS
        
        jDatos = new JPanel();
        jDatos.setBorder(BorderFactory.createLineBorder(Color.black));
        //AGREGANDO COMPONENTES DE PANEL pDatos
        JLabel pDlab1 = new JLabel("Verificar Terreno", SwingConstants.CENTER);
        pDlab1.setFont(new java.awt.Font("Tahoma", 0, 20));
        txtarea31 = new JTextArea(5,15);
        txtarea31.setEditable(false);
        
        //LIMITAR ESCRITURA EN TEXTFIELD
        int limite = 2;
        jtf31 = new JTextField("Renglón: ",7);
        jtf31.addKeyListener(new KeyListener(){
 
            public void keyTyped(KeyEvent e)
            {
                if (jtf31.getText().length()== limite)
                    e.consume();
            }
 
            public void keyPressed(KeyEvent arg0) {
            }
 
            public void keyReleased(KeyEvent arg0) {
            }
        });
        
        //LIMITAR ESCRITURA TEXTFIELD 2
        int limite2 = 1;
        jtf32 = new JTextField("Columna: ",7);
        jtf32.addKeyListener(new KeyListener(){
 
            public void keyTyped(KeyEvent ev)
            {
                if (jtf32.getText().length()== limite2)
                    ev.consume();
            }
 
            public void keyPressed(KeyEvent arg0) {
            }
 
            public void keyReleased(KeyEvent arg0) {
            }
        });
    
        jDatos.add(pDlab1);
        jDatos.add(jtf31);
        jDatos.add(jtf32);
        
        JButton jbtn31 = new JButton("Consultar");
        jbtn31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jDatos.add(jbtn31);
        txtarea31.addKeyListener(this);
        
        scrollPaneAreaEntrada = new JScrollPane();
        scrollPaneAreaEntrada.setBounds(10, 100, 513, 70);
        scrollPaneAreaEntrada.setViewportView(txtarea31);
        
        jDatos.add(scrollPaneAreaEntrada);
        contentPane.add(jDatos, BorderLayout.SOUTH); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
 
    /**Este metodo se ejecuta cuando se suelta una tecla*/
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txtarea31)
        {
            if (e.VK_ESCAPE==e.getKeyCode())
            {
                int respuesta = JOptionPane.showConfirmDialog(this,
                "Esta seguro que desea salir?", "Confirmación",
                JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_NO_OPTION)
                {
                    System.exit(0);
                }
            }
            if(e.VK_DOWN ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla abajo");
                hacerMovimientoAbajo(2,0);
            }
            if(e.VK_LEFT ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla izquierda");
                hacerMovimientoIzquierda(0,2);
            }
            if(e.VK_RIGHT ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla derecha");
                hacerMovimientoDerecha(1,2);
            }
            if(e.VK_UP ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla arriba");
                hacerMovimientoArriba(0,2);
            }
        } 
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void hacerMovimientoArriba(int renglon, int columna)
    {
        if(renglon !=0)
        {
            /*Casilla[][] casillas = controlador.getTablero().getMapa();
            int idTerreno = casillas[renglon-1][columna].getTerreno().getIdTerreno();*/
            System.out.println("JUGADOR MOVIDO ARRIBA");
        }
    }
    
    public void hacerMovimientoAbajo(int renglon, int columna)
    {
        if(renglon !=controlador.getTablero().getNoRenglones()-1)
        {
            /*Casilla[][] casillas = controlador.getTablero().getMapa();
            int idTerreno = casillas[renglon-1][columna].getTerreno().getIdTerreno();*/
            System.out.println("JUGADOR MOVIDO ABAJO");
        }
    }
    
    public void hacerMovimientoIzquierda(int renglon, int columna)
    {
        if(columna !=0)
        {
            /*Casilla[][] casillas = controlador.getTablero().getMapa();
            int idTerreno = casillas[renglon-1][columna].getTerreno().getIdTerreno();*/
            System.out.println("JUGADOR MOVIDO IZQUIERDA");
        }
    }
    
    public void hacerMovimientoDerecha(int renglon, int columna)
    {
        if(columna != controlador.getTablero().getNoColumnas()-1)
        {
            /*Casilla[][] casillas = controlador.getTablero().getMapa();
            int idTerreno = casillas[renglon-1][columna].getTerreno().getIdTerreno();*/
            System.out.println("JUGADOR MOVIDO DERECHA");
        }
    }
    
    public static void main(String[] args) {	
        Controlador controlador = new Controlador();
	VentanaPrincipal frame = new VentanaPrincipal(controlador);
	frame.setVisible(true);			
    }
}