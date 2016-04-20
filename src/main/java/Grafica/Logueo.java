package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.server.Operation;

public class Logueo extends JFrame {

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
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the frame.
	 */
	public Logueo() {
		initialize();
		setVisible(true);
		setResizable(false);
	}
	private void initialize() {
		
		setTitle("Bienvenido al buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
	
		String im = getClass().getClassLoader().getResource("imgBusc.jpg").toString();
		this.setIconImage(new ImageIcon(im.substring(6)).getImage());
		setLocationRelativeTo(null);   //centra el JFrame
		contentPane.setBorder((new MyBorder(false)).getBorde());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//contentPane.add(getLblPorFavorRellena());
		//contentPane.add(getLblDeseasJugar());
		contentPane.add(getLblTuNombre());
		contentPane.add(getLblDificultad());
		contentPane.add(getComboBox());
		contentPane.add(getTextField());
		contentPane.add(getBtnAceptar());
		
		
	}
	private JLabel getLblPorFavorRellena() {
		if (lblPorFavorRellena == null) {
			lblPorFavorRellena = new JLabel("Por favor, rellena los huecos con tu nombre y el nivel de dificultad que");
			lblPorFavorRellena.setBounds(10, 11, 395, 44);
		}
		return lblPorFavorRellena;
	}
	private JLabel getLblDeseasJugar() {
		if (lblDeseasJugar == null) {
			lblDeseasJugar = new JLabel("deseas jugar.Despues pulsa 'Aceptar'.");
			lblDeseasJugar.setBounds(10, 41, 352, 23);
		}
		return lblDeseasJugar;
	}
	private JLabel getLblTuNombre() {
		if (lblTuNombre == null) {
			lblTuNombre = new JLabel("Tu nombre:");
			lblTuNombre.setFont(new Font("Dialog",Font.BOLD,12));
			//lblTuNombre.setBounds(88, 75, 89, 23);
			lblTuNombre.setBounds(37, 45, 89, 23);
		}
		return lblTuNombre;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			//lblDificultad = new JLabel("Dificultad:");
			lblDificultad = new JLabel ("Nivel de juego:");
			lblDificultad.setFont(new Font("Dialog",Font.BOLD,12));
			//lblDificultad.setBounds(88, 109, 63, 14);
			//lblDificultad.setBounds(88, 109, 83, 14);
			lblDificultad.setBounds(37, 79, 83, 14);
		}
		return lblDificultad;
	}
	public JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"F\u00E1cil", "Medio", "Dif\u00EDcil"}));
			comboBox.setToolTipText("");
			//comboBox.setBounds(187, 106, 63, 20);
			comboBox.setBounds(136, 76, 63, 20);
		}
		return comboBox;
	}
	
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			//textField.setBounds(187, 76, 86, 20);
			textField.setBounds(136, 46, 120, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	public JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			//btnAceptar.setBounds(149, 187, 89, 23);
			btnAceptar.setBounds(107, 167, 89, 23);
			btnAceptar.setName("aceptLog");
		}
		return btnAceptar;
	}
}
