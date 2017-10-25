package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

public class VentanaArbol extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentanaArbol(JTree arbol) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton button = new JButton("New button");
		contentPane.add(arbol, BorderLayout.CENTER);
	}

}
