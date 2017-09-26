package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import controlador.Controlador;

public class VentanaSeleccionarMapa extends JFrame {

	private JPanel contentPane;
	private JTextField txtArchivo;
	private Controlador controlador;


	/**
	 * Create the frame.
	 */
	public VentanaSeleccionarMapa(Controlador controlador) {
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
                                setVisible(false);
				VentanaSeleccionarPesos ventana = new VentanaSeleccionarPesos(controlador);
				ventana.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		contentPane.add(btnAvanzar, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Selecciona Mapa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblSeleccionarMapa = new JLabel("Seleccionar mapa: ");
		GridBagConstraints gbc_lblSeleccionarMapa = new GridBagConstraints();
		gbc_lblSeleccionarMapa.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarMapa.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccionarMapa.gridx = 0;
		gbc_lblSeleccionarMapa.gridy = 0;
		panel.add(lblSeleccionarMapa, gbc_lblSeleccionarMapa);

		txtArchivo = new JTextField();
		txtArchivo.setEditable(false);
		txtArchivo.setText("archivo");
		GridBagConstraints gbc_txtArchivo = new GridBagConstraints();
		gbc_txtArchivo.insets = new Insets(0, 0, 5, 0);
		gbc_txtArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtArchivo.gridx = 1;
		gbc_txtArchivo.gridy = 0;
		panel.add(txtArchivo, gbc_txtArchivo);
		txtArchivo.setColumns(10);

		JButton btnFileChooser = new JButton("File Chooser");
		btnFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filePath;
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					filePath = selectedFile.getAbsolutePath();
					txtArchivo.setText(selectedFile.getAbsolutePath());
					controlador.parsearArchivo(selectedFile.getAbsolutePath());
					if (!controlador.esValidoElMapa) {
						JOptionPane.showMessageDialog(new JFrame(), "El mapa no es valido",  "Error",
							    JOptionPane.ERROR_MESSAGE);
						setVisible(false);
						dispose();
						System.exit(0);
					}
					System.out.println("");

				}
			}
		});
		GridBagConstraints gbc_btnFileChooser = new GridBagConstraints();
		gbc_btnFileChooser.gridwidth = 2;
		gbc_btnFileChooser.insets = new Insets(0, 0, 0, 5);
		gbc_btnFileChooser.gridx = 0;
		gbc_btnFileChooser.gridy = 1;
		panel.add(btnFileChooser, gbc_btnFileChooser);
	}

}
