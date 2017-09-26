package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Terreno;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaSeleccionarPesos extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtColor;
	private Controlador controlador;
	private ArrayList<JTextField> nombres = new ArrayList<JTextField>();
	private ArrayList<JTextField> colores = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaSeleccionarPesos(Controlador controlador) {
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnAvanzar = new JButton("Avanzar");
		btnAvanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Terreno> terrenos= controlador.getTerrenoSinPesos();
				int i=0;
				for (Terreno t: terrenos) {
					t.setColor(colores.get(i).getText());
					t.setNombreTerreno(nombres.get(i).getText());
					i++;
				}
				controlador.setTerrenoConNombres(terrenos);
				
                                setVisible(false);
				VentanaSeleccionarJugadores ventana = new VentanaSeleccionarJugadores(controlador);
				ventana.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnAvanzar, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Seleccionar pesos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblIdterreno = new JLabel("numeroId");
		GridBagConstraints gbc_lblIdterreno = new GridBagConstraints();
		gbc_lblIdterreno.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdterreno.gridx = 0;
		gbc_lblIdterreno.gridy = 0;
		panel.add(lblIdterreno, gbc_lblIdterreno);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 0;
		panel.add(lblNombre, gbc_lblNombre);
		
		JLabel lblColor = new JLabel("Color");
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblColor.gridx = 2;
		gbc_lblColor.gridy = 0;
		panel.add(lblColor, gbc_lblColor);
		
		int gridY=1;
		for (Terreno terreno : controlador.getTerrenoSinPesos()) {
			
			
			JLabel lblid = new JLabel("#" + terreno.getIdTerreno());
			GridBagConstraints gbc_lblid = new GridBagConstraints();
			gbc_lblid.anchor = GridBagConstraints.EAST;
			gbc_lblid.insets = new Insets(0, 0, 0, 5);
			gbc_lblid.gridx = 0;
			gbc_lblid.gridy = gridY;
			panel.add(lblid, gbc_lblid);
			
			txtNombre = new JTextField();
			
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 0, 5);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = gridY;
			panel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
			
			txtColor = new JTextField();
			
			GridBagConstraints gbc_txtColor = new GridBagConstraints();
			gbc_txtColor.insets = new Insets(0, 0, 0, 5);
			gbc_txtColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtColor.gridx = 2;
			gbc_txtColor.gridy = gridY;
			panel.add(txtColor, gbc_txtColor);
			txtColor.setColumns(10);
			nombres.add(txtNombre);
			colores.add(txtColor);
			gridY++;
			
		}
	}

}
