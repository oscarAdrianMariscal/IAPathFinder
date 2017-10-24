package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Coordenada;
import modelo.Terreno;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class VentanaSeleccionarJugadores extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreUno;
	private Controlador controlador;
	ImageIcon jugador1 =  new ImageIcon(VentanaSeleccionarJugadores.class.getResource("/vista/jugador1.gif"));
	ImageIcon jugador2 =  new ImageIcon(VentanaSeleccionarJugadores.class.getResource("/vista/jugador2.gif"));
	ImageIcon jugador3 =  new ImageIcon(VentanaSeleccionarJugadores.class.getResource("/vista/jugador3.gif"));
	private JTextField textFieldNombreDos;
	private JTextField textFieldNombreTres;
	ArrayList<JSpinner> spinnerJugadorUno = new ArrayList<>();
	ArrayList<JSpinner> spinnerJugadorDos = new ArrayList<>();
	ArrayList<JSpinner> spinnerJugadorTres = new ArrayList<>();
	private JTextField txtInicial;
	private JTextField textFinal;
	private JCheckBox chckbxUsarUno;
	private JCheckBox checkBoxDos;
	private JCheckBox checkBoxTres;



	public void toggleJugadorUno() {
		for (JSpinner spinner: spinnerJugadorUno) {
			spinner.setEnabled(chckbxUsarUno.isSelected());
		}
		txtNombreUno.setEnabled(chckbxUsarUno.isSelected());
		if (chckbxUsarUno.isSelected()==true) {
			checkBoxDos.setSelected(false);
			toggleJugadorDos();
			checkBoxTres.setSelected(false);
			toggleJugadorTres();
		}
	}
	
	public void toggleJugadorDos() {
		for (JSpinner spinner: spinnerJugadorDos) {
			spinner.setEnabled(checkBoxDos.isSelected());
		}
		textFieldNombreDos.setEnabled(checkBoxDos.isSelected());
		
		if (checkBoxDos.isSelected()==true) {
			chckbxUsarUno.setSelected(false);
			toggleJugadorUno();
			checkBoxTres.setSelected(false);
			toggleJugadorTres();
		}
	}
	
	public void toggleJugadorTres() {
		for (JSpinner spinner: spinnerJugadorTres) {
			spinner.setEnabled(checkBoxTres.isSelected());
		}
		textFieldNombreTres.setEnabled(checkBoxTres.isSelected());
		
		if (checkBoxTres.isSelected()==true) {
			checkBoxDos.setSelected(false);
			toggleJugadorDos();
			chckbxUsarUno.setSelected(false);
			toggleJugadorUno();
		}
	}
	
	public VentanaSeleccionarJugadores(Controlador controlador) {
		this.controlador =controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelJugadores = new JPanel();
		contentPane.add(panelJugadores, BorderLayout.CENTER);
		panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.X_AXIS));

		JPanel panelUno = new JPanel();
		panelUno.setBorder(new TitledBorder(null, "Jugador #1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelJugadores.add(panelUno);
		GridBagLayout gbl_panelUno = new GridBagLayout();
		gbl_panelUno.columnWidths = new int[]{0, 0, 0};
		gbl_panelUno.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelUno.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelUno.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelUno.setLayout(gbl_panelUno);

		chckbxUsarUno = new JCheckBox("Usar");
		chckbxUsarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleJugadorUno();
			}
		});
		GridBagConstraints gbc_chckbxUsarUno = new GridBagConstraints();
		gbc_chckbxUsarUno.gridwidth = 2;
		gbc_chckbxUsarUno.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUsarUno.gridx = 0;
		gbc_chckbxUsarUno.gridy = 0;
		panelUno.add(chckbxUsarUno, gbc_chckbxUsarUno);
		
		JLabel lblJugador = new JLabel("");
		lblJugador.setIcon(new ImageIcon(VentanaSeleccionarJugadores.class.getResource("/vista/jugador1.gif")));
		GridBagConstraints gbc_lblJugador = new GridBagConstraints();
		gbc_lblJugador.gridwidth = 2;
		gbc_lblJugador.insets = new Insets(0, 0, 5, 5);
		gbc_lblJugador.gridx = 0;
		gbc_lblJugador.gridy = 1;
		panelUno.add(lblJugador, gbc_lblJugador);

		JLabel lblNombreUno = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombreUno = new GridBagConstraints();
		gbc_lblNombreUno.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreUno.anchor = GridBagConstraints.EAST;
		gbc_lblNombreUno.gridx = 0;
		gbc_lblNombreUno.gridy = 2;
		panelUno.add(lblNombreUno, gbc_lblNombreUno);

		txtNombreUno = new JTextField();
		txtNombreUno.setText("Nombre");
		GridBagConstraints gbc_txtNombreUno = new GridBagConstraints();
		gbc_txtNombreUno.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombreUno.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreUno.gridx = 1;
		gbc_txtNombreUno.gridy = 2;
		panelUno.add(txtNombreUno, gbc_txtNombreUno);
		txtNombreUno.setColumns(10);


		//
		ArrayList<Terreno> terrenos =  controlador.getTerrenoConNombres();
		int gridYInicial = 4;
		for (Terreno t: terrenos) {

			JLabel lblTerrenoUno = new JLabel("#"+ t.getIdTerreno() +" " +  t.getNombreTerreno());
			GridBagConstraints gbc_lblTerrenoUno = new GridBagConstraints();
			gbc_lblTerrenoUno.insets = new Insets(0, 0, 0, 5);
			gbc_lblTerrenoUno.gridx = 0;
			gbc_lblTerrenoUno.gridy = gridYInicial;
			panelUno.add(lblTerrenoUno, gbc_lblTerrenoUno);

			JSpinner spinnerUno = new JSpinner();
			spinnerUno.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
			GridBagConstraints gbc_spinnerUno = new GridBagConstraints();
			gbc_spinnerUno.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerUno.gridx = 1;
			gbc_spinnerUno.gridy = gridYInicial;
			spinnerJugadorUno.add(spinnerUno);
			panelUno.add(spinnerUno, gbc_spinnerUno);
			gridYInicial++;
		}

		//
		JPanel panelDos = new JPanel();
		panelDos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Jugador #2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelJugadores.add(panelDos);
		GridBagLayout gbl_panelDos = new GridBagLayout();
		gbl_panelDos.columnWidths = new int[]{0, 0, 0};
		gbl_panelDos.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelDos.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelDos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDos.setLayout(gbl_panelDos);

		checkBoxDos = new JCheckBox("Usar");
		checkBoxDos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//jUGADOR 2
				toggleJugadorDos();
			}
		});
		GridBagConstraints gbc_checkBoxDos = new GridBagConstraints();
		gbc_checkBoxDos.gridwidth = 2;
		gbc_checkBoxDos.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxDos.gridx = 0;
		gbc_checkBoxDos.gridy = 0;
		panelDos.add(checkBoxDos, gbc_checkBoxDos);
		
		JLabel lblJugador_1 = new JLabel("");
		lblJugador_1.setIcon(new ImageIcon(VentanaSeleccionarJugadores.class.getResource("/vista/jugador2.gif")));
		GridBagConstraints gbc_lblJugador_1 = new GridBagConstraints();
		gbc_lblJugador_1.gridwidth = 2;
		gbc_lblJugador_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblJugador_1.gridx = 0;
		gbc_lblJugador_1.gridy = 1;
		panelDos.add(lblJugador_1, gbc_lblJugador_1);

		JLabel labelNombreDos = new JLabel("Nombre: ");
		GridBagConstraints gbc_labelNombreDos = new GridBagConstraints();
		gbc_labelNombreDos.anchor = GridBagConstraints.EAST;
		gbc_labelNombreDos.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombreDos.gridx = 0;
		gbc_labelNombreDos.gridy = 2;
		panelDos.add(labelNombreDos, gbc_labelNombreDos);

		textFieldNombreDos = new JTextField();
		textFieldNombreDos.setText("Nombre");
		textFieldNombreDos.setColumns(10);
		GridBagConstraints gbc_textFieldNombreDos = new GridBagConstraints();
		gbc_textFieldNombreDos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreDos.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreDos.gridx = 1;
		gbc_textFieldNombreDos.gridy = 2;
		panelDos.add(textFieldNombreDos, gbc_textFieldNombreDos);
		gridYInicial = 4;
		for (Terreno t: terrenos) {
			JLabel labelTerrenoDos = new JLabel("#"+ t.getIdTerreno() +" " +  t.getNombreTerreno());
			GridBagConstraints gbc_labelTerrenoDos = new GridBagConstraints();
			gbc_labelTerrenoDos.insets = new Insets(0, 0, 0, 5);
			gbc_labelTerrenoDos.gridx = 0;
			gbc_labelTerrenoDos.gridy = gridYInicial;
			panelDos.add(labelTerrenoDos, gbc_labelTerrenoDos);

			JSpinner spinnerDos = new JSpinner();
			spinnerDos.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
			GridBagConstraints gbc_spinnerDos = new GridBagConstraints();
			gbc_spinnerDos.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerDos.gridx = 1;
			gbc_spinnerDos.gridy = gridYInicial;
			panelDos.add(spinnerDos, gbc_spinnerDos);
			spinnerJugadorDos.add(spinnerDos);
			gridYInicial++;
		}

		JPanel panelTres = new JPanel();
		panelTres.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Jugador #3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelJugadores.add(panelTres);
		GridBagLayout gbl_panelTres = new GridBagLayout();
		gbl_panelTres.columnWidths = new int[]{0, 0, 0};
		gbl_panelTres.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelTres.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelTres.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTres.setLayout(gbl_panelTres);

		checkBoxTres = new JCheckBox("Usar");
		checkBoxTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleJugadorTres();
			}
		});
		GridBagConstraints gbc_checkBoxTres = new GridBagConstraints();
		gbc_checkBoxTres.gridwidth = 2;
		gbc_checkBoxTres.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxTres.gridx = 0;
		gbc_checkBoxTres.gridy = 0;
		panelTres.add(checkBoxTres, gbc_checkBoxTres);
		
		JLabel lblJugador_2 = new JLabel("");
		lblJugador_2.setIcon(new ImageIcon(VentanaSeleccionarJugadores.class.getResource("/vista/jugador3.gif")));
		GridBagConstraints gbc_lblJugador_2 = new GridBagConstraints();
		gbc_lblJugador_2.gridwidth = 2;
		gbc_lblJugador_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblJugador_2.gridx = 0;
		gbc_lblJugador_2.gridy = 1;
		panelTres.add(lblJugador_2, gbc_lblJugador_2);

		JLabel labelNombreTres = new JLabel("Nombre: ");
		GridBagConstraints gbc_labelNombreTres = new GridBagConstraints();
		gbc_labelNombreTres.anchor = GridBagConstraints.EAST;
		gbc_labelNombreTres.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombreTres.gridx = 0;
		gbc_labelNombreTres.gridy = 2;
		panelTres.add(labelNombreTres, gbc_labelNombreTres);

		textFieldNombreTres = new JTextField();
		textFieldNombreTres.setText("Nombre");
		textFieldNombreTres.setColumns(10);
		GridBagConstraints gbc_textFieldNombreTres = new GridBagConstraints();
		gbc_textFieldNombreTres.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreTres.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreTres.gridx = 1;
		gbc_textFieldNombreTres.gridy = 2;
		panelTres.add(textFieldNombreTres, gbc_textFieldNombreTres);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblCasillaInicial = new JLabel("Casilla inicial: ");
		GridBagConstraints gbc_lblCasillaInicial = new GridBagConstraints();
		gbc_lblCasillaInicial.insets = new Insets(0, 0, 5, 5);
		gbc_lblCasillaInicial.anchor = GridBagConstraints.EAST;
		gbc_lblCasillaInicial.gridx = 0;
		gbc_lblCasillaInicial.gridy = 0;
		panel.add(lblCasillaInicial, gbc_lblCasillaInicial);

		txtInicial = new JTextField();
		GridBagConstraints gbc_txtInicial = new GridBagConstraints();
		gbc_txtInicial.insets = new Insets(0, 0, 5, 0);
		gbc_txtInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInicial.gridx = 1;
		gbc_txtInicial.gridy = 0;
		panel.add(txtInicial, gbc_txtInicial);
		txtInicial.setColumns(10);

		JLabel lblCasillaFinal = new JLabel("Casilla Final: ");
		GridBagConstraints gbc_lblCasillaFinal = new GridBagConstraints();
		gbc_lblCasillaFinal.anchor = GridBagConstraints.EAST;
		gbc_lblCasillaFinal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCasillaFinal.gridx = 0;
		gbc_lblCasillaFinal.gridy = 1;
		panel.add(lblCasillaFinal, gbc_lblCasillaFinal);

		textFinal = new JTextField();
		GridBagConstraints gbc_textFinal = new GridBagConstraints();
		gbc_textFinal.insets = new Insets(0, 0, 5, 0);
		gbc_textFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFinal.gridx = 1;
		gbc_textFinal.gridy = 1;
		panel.add(textFinal, gbc_textFinal);
		textFinal.setColumns(10);

		JButton btnAvanzar = new JButton("Avanzar");
		btnAvanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(chckbxUsarUno.isSelected()) {

					String nombre = txtNombreUno.getText();
					ArrayList<Terreno> terrenos = controlador.getTerrenoConNombres();
					for (int i = 0; i < spinnerJugadorUno.size() ; i++) {

						float peso=(float)spinnerJugadorUno.get(i).getModel().getValue();
						terrenos.get(i).setCosto(peso);
					}
					
					controlador.agregarJugador(terrenos, nombre,jugador1);

				}
				if(checkBoxDos.isSelected()) {



					ArrayList<Terreno> terrenos = controlador.getTerrenoConNombres();
					for (int i = 0; i < spinnerJugadorDos.size() ; i++) {

						float peso=(float)spinnerJugadorDos.get(i).getModel().getValue();
						terrenos.get(i).setCosto(peso);
					}
					controlador.agregarJugador(terrenos, textFieldNombreDos.getText(),jugador2);					
				}
				if(checkBoxTres.isSelected()) {

					ArrayList<Terreno> terrenos = controlador.getTerrenoConNombres();
					for (int i = 0; i < spinnerJugadorTres.size() ; i++) {

						float peso=(float)spinnerJugadorTres.get(i).getModel().getValue();
						terrenos.get(i).setCosto(peso);
					}
					controlador.agregarJugador(terrenos, textFieldNombreTres.getText(),jugador3);
					textFieldNombreTres.getText();					
				}

				Coordenada inicial = new Coordenada(0, 0);
				Coordenada meta= new Coordenada(1, 1);
				inicial.setComoTexto(txtInicial.getText());
				meta.setComoTexto(textFinal.getText());
				
				controlador.crearTablero(inicial,meta);
				System.out.println("Casilla inicial: "+ inicial.getCoordenadaI() +","+ inicial.getCoordenadaJ());
				System.out.println("Casilla final: "+ meta.getCoordenadaI() +","+ meta.getCoordenadaJ());
				
				if( controlador.getTablero().posicionInicialEsValida()){
					controlador.getTablero().llenarMapaConLosPesosDelJugadorActual();
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador);
					ventanaPrincipal.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "posicion inicial no valida",  "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		GridBagConstraints gbc_btnAvanzar = new GridBagConstraints();
		gbc_btnAvanzar.gridwidth = 2;
		gbc_btnAvanzar.gridx = 0;
		gbc_btnAvanzar.gridy = 2;
		panel.add(btnAvanzar, gbc_btnAvanzar);

		gridYInicial =4;
		for (Terreno t: terrenos) {
			JLabel labelTerrenoTres = new JLabel("#"+ t.getIdTerreno() +" " +  t.getNombreTerreno());
			GridBagConstraints gbc_labelTerrenoTres = new GridBagConstraints();
			gbc_labelTerrenoTres.insets = new Insets(0, 0, 0, 5);
			gbc_labelTerrenoTres.gridx = 0;
			gbc_labelTerrenoTres.gridy = gridYInicial;
			panelTres.add(labelTerrenoTres, gbc_labelTerrenoTres);

			JSpinner spinnerTres = new JSpinner();
			spinnerTres.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
			GridBagConstraints gbc_spinnerTres = new GridBagConstraints();
			gbc_spinnerTres.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerTres.gridx = 1;
			gbc_spinnerTres.gridy = gridYInicial;
			panelTres.add(spinnerTres, gbc_spinnerTres);
			spinnerJugadorTres.add(spinnerTres);
			gridYInicial++;
		}
		
		toggleJugadorUno();
		toggleJugadorDos();
		toggleJugadorTres();	
	}
}
