package Grafica;



import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Grafica.MyBorder;
import Grafica.Tablero;
import Model.Buscaminas;
import Model.NivelDificultad;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Logueo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPorFavorRellena;
	private JLabel lblDeseasJugar;
	private JLabel lblTuNombre;
	private JLabel lblDificultad;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logueo frame = new Logueo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logueo() {
		initialize();
		setLocationRelativeTo(null);   //centra el JFrame
	}
	private void initialize() {
		setTitle("Bienvenido al buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 280);
		contentPane = new JPanel();
		String im = getClass().getClassLoader().getResource("mine.png").toString();
		this.setIconImage(new ImageIcon(im.substring(6)).getImage());
		contentPane.setBorder((new MyBorder(false)).getBorde());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPorFavorRellena());
		contentPane.add(getLblDeseasJugar());
		contentPane.add(getLblTuNombre());
		contentPane.add(getLblDificultad());
		contentPane.add(getComboBox());
		contentPane.add(getTextField());
		contentPane.add(getBtnAceptar());
		setResizable(false);
	}
	private JLabel getLblPorFavorRellena() {
		if (lblPorFavorRellena == null) {
			lblPorFavorRellena = new JLabel("Por favor, rellena tu nombre y el nivel de dificultad que");
			lblPorFavorRellena.setBounds(30, 11, 395, 44);
		}
		return lblPorFavorRellena;
	}
	private JLabel getLblDeseasJugar() {
		if (lblDeseasJugar == null) {
			lblDeseasJugar = new JLabel("deseas jugar. Luego pulsa 'Aceptar'.");
			lblDeseasJugar.setBounds(30, 41, 352, 23);
		}
		return lblDeseasJugar;
	}
	private JLabel getLblTuNombre() {
		if (lblTuNombre == null) {
			lblTuNombre = new JLabel("Tu nombre:");
			lblTuNombre.setBounds(88, 75, 89, 23);
		}
		return lblTuNombre;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			lblDificultad = new JLabel("Dificultad:");
			lblDificultad.setBounds(88, 109, 63, 14);
		}
		return lblDificultad;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"F\u00E1cil", "Medio", "Dif\u00EDcil"}));
			comboBox.setToolTipText("");
			comboBox.setBounds(187, 106, 63, 20);
		}
		return comboBox;
	}
	
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(187, 76, 86, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnAceptar(){
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			getBtnAceptar().addActionListener(new ActionListener() {
				private Window tablero2;

				public void actionPerformed(ActionEvent e) {
					if(textField.getText().equals("")){
						    textField.setText("Desconocido");
					}
					Tablero tablero=new Tablero();
					if(comboBox.getSelectedIndex()==0){	
						Buscaminas gt = Buscaminas.getConBuildTablero();
						gt.guardarDatos(NivelDificultad.FACIL, textField.getText(), tablero);
					}
					if(comboBox.getSelectedIndex()==1){	
						
						Buscaminas gt = Buscaminas.getConBuildTablero();
						gt.guardarDatos(NivelDificultad.MEDIO, textField.getText(), tablero);

					}
					if(comboBox.getSelectedIndex()==2){	
					
						Buscaminas gt = Buscaminas.getConBuildTablero();
						gt.guardarDatos(NivelDificultad.DIFICIL, textField.getText(), tablero);

					}
					Logueo.this.dispose();	
					tablero.inicio();							
				}
			});
			btnAceptar.setBounds(149, 187, 89, 23);
			
		}
		return btnAceptar;
	}

}
