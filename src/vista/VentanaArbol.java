package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

public class VentanaArbol extends JFrame {

	private JPanel contentPane;

	public VentanaArbol(JTree arbol,String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //super("Arbol Generado");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
                JScrollPane scrPane = new JScrollPane(contentPane);
		JButton button = new JButton("New button");
		contentPane.add(arbol, BorderLayout.CENTER);
                getContentPane().add(scrPane);
	}

}
