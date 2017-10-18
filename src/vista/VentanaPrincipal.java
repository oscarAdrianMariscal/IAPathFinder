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
    
    public VentanaPrincipal(Controlador controlador)
    {
        this.controlador = controlador;
        tableroIU = new TableroIU(controlador);
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        JScrollBar jsb;
        
        //LIMITAR ESCRITURA EN TEXTFIELD
        int limite = 2;
        jtf31 = new JTextField("Renglon: ",7);
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String columnaS = (jtf32.getText());
                String renglonS = (jtf31.getText());
                jtf31.setText("Renglon");
                jtf32.setText("Columna");
                
                int coordenadaX = columnaS.toUpperCase().codePointAt(0) - "A".codePointAt(0);
                int renglon = Integer.parseInt(renglonS)-1;
                //int columna = transformaCoordenadaY(columnaS);
                System.out.println("RENGLON: " + renglon);
                System.out.println("COLUMNA: " + coordenadaX);
                
                
                //
                //int renglonMostrar = renglon + 1
                String terreno = controlador.getTablero().getCoordenadaEspecial(renglon, coordenadaX).getTerreno().toString();
                Coordenada c = controlador.getTablero().getCoordenadaEspecial(renglon,coordenadaX).getCoordenada();
                txtarea31.setText(c.toString() + "\n" + terreno);     
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
    
    private int transformaCoordenadaY(String coordenadaY)
    {
        int cI;
        
        switch(coordenadaY)
        {
            case "A":
                cI = 0;
            case "B":
                cI = 1;
                break;
            case "C":
                cI = 2;
                break;
            case "D":
                cI = 3;
                break;
            case "E":
                cI = 4;
                break;
            case "F":
                cI = 5;
                break;
            case "G":
                cI = 6;
                break;
            case "H":
                cI = 7;
                break;
            case "I":
                cI = 8;
                break;
            case "J":
                cI = 9;
                break;
            case "K":
                cI = 10;
                break;
            case "L":
                cI = 11;
                break;
            case "M":
                cI = 12;
                break;
            case "N":
                cI = 13;
                break;
            case "O":
                cI = 14;
                break;
            default:
                cI = -1;
                break;
        }
        return cI;
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