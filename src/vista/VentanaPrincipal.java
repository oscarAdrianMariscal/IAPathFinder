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
import modelo.Backtracking;
import modelo.Casilla;
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
    private JButton btnNewButton;
    
    public VentanaPrincipal(Controlador controlador)
    {
        this.controlador = controlador;
        tableroIU = new TableroIU(controlador);
        
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50,0,950,750);
        setResizable(false);
        
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
        jDatos.setLayout(new BorderLayout());
        
        //AGREGANDO COMPONENTES DE PANEL pDatos
        txtarea31 = new JTextArea(5,10);
        txtarea31.setEditable(false);
        txtarea31.addKeyListener(this);
        
        jDatos.add(txtarea31, BorderLayout.CENTER);
        contentPane.add(jDatos, BorderLayout.EAST);
        
        btnNewButton = new JButton("Ejecutar algoritmo");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ejecutarAlgoritmo();
        	}
        });
        jDatos.add(btnNewButton, BorderLayout.NORTH);
        
        

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    public void ejecutarAlgoritmo() {
    	Backtracking algoritmo =new Backtracking(controlador.getTablero(),controlador);
    	Thread hilo = new Thread(algoritmo);
    	hilo.start();
    	
    	
    	
    	//

    }
    
    public void mostarArbol(JTree arbol,String titulo) {
    	VentanaArbol ventana = new VentanaArbol(arbol,titulo);
        ventana.setVisible(true); 
    }
 
    /**Este metodo se ejecuta cuando se suelta una tecla*/
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txtarea31)
        {
            if (e.VK_ESCAPE==e.getKeyCode())
            {
                int respuesta = JOptionPane.showConfirmDialog(this,
                "Esta seguro que desea salir?", "Confirmaci�n",
                JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_NO_OPTION)
                {
                    System.exit(0);
                }
            }
            if(e.VK_DOWN ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla abajo");
                Coordenada c = getCasillaUsada();
                hacerMovimientoAbajo(c.getCoordenadaI(), c.getCoordenadaJ());
            }
            if(e.VK_LEFT ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla izquierda");
                Coordenada c = getCasillaUsada();
                hacerMovimientoIzquierda(c.getCoordenadaI(),c.getCoordenadaJ());
            }
            if(e.VK_RIGHT ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla derecha");
                Coordenada c = getCasillaUsada();
                hacerMovimientoDerecha(c.getCoordenadaI(),c.getCoordenadaJ());
            }
            if(e.VK_UP ==e.getKeyCode())
            {
                System.out.println("Soltaste tecla arriba");
                Coordenada c = getCasillaUsada();
                hacerMovimientoArriba(c.getCoordenadaI(),c.getCoordenadaJ());
            }
        } 
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Coordenada getCasillaUsada()
    {
        Coordenada c = null;
        int renglon, columna;
        Casilla[][] casillas = controlador.getTablero().getMapa();
        for(int i = 0; i<controlador.getTablero().getNoRenglones(); i++)
        {
            for(int j=0; j<controlador.getTablero().getNoColumnas(); j++)
            {
                if(casillas[i][j].isUsado() == true)
                {
                    renglon = i;
                    columna = j;
                    c = new Coordenada(renglon, columna);
                }
            }
        }
        return c;
    }
    
    public void hacerMovimientoArriba(int renglon, int columna)
    {
        if(renglon !=0)
        {
            System.out.println("JUGADOR MOVIDO ARRIBA");
            tableroIU.moverArriba(renglon, columna);
        }
    }
    
    public void hacerMovimientoAbajo(int renglon, int columna)
    {
        if(renglon !=controlador.getTablero().getNoRenglones()-1)
        {
            System.out.println("JUGADOR MOVIDO ABAJO");
            tableroIU.moverAbajo(renglon, columna);
        }
    }
    
    public void hacerMovimientoIzquierda(int renglon, int columna)
    {
        if(columna !=0)
        {
            System.out.println("JUGADOR MOVIDO IZQUIERDA");
            tableroIU.moverIzquierda(renglon, columna);
        }
    }
    
    public void hacerMovimientoDerecha(int renglon, int columna)
    {
        if(columna != controlador.getTablero().getNoColumnas()-1)
        {
            System.out.println("JUGADOR MOVIDO DERECHA");
            tableroIU.moverDerecha(renglon, columna);
        }
    }
    
    public static void main(String[] args) {	
        Controlador controlador = new Controlador();
	VentanaPrincipal frame = new VentanaPrincipal(controlador);
	frame.setVisible(true);			
    }
}
