package Grafica;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.Buscaminas;
import Model.NivelDificultad;

public class Continuar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblcontinuar;
	private JLabel lblv;
	private JLabel lbld;
	private JButton btnS;
	private JButton btnNo;
	private boolean resultado;
	//guarda el nivel de dificicultad en el que se estaba jugando
	private NivelDificultad nivel;
	private String nombreJ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Continuar continuar = new Continuar(NivelDificultad.FACIL, true, "");
		continuar.setVisible(true);
	
	}

	/**
	 * Create the frame.
	 * @param b 
	 */
	public Continuar(NivelDificultad nivel, boolean b, String nombreJ) {
		this.nivel=nivel;
		this.resultado=b;
		this.nombreJ=nombreJ;
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 250, 180);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//setLocationRelativeTo(null); 
		String im = getClass().getClassLoader().getResource("mine.png").toString();
		this.setIconImage(new ImageIcon(im.substring(6)).getImage());
		setResizable(false);
		contentPane.setBorder((new MyBorder(false)).getBorde());
		contentPane.add(getLblcontinuar());
		if(this.resultado==false){
			this.getLbld();
			contentPane.add(getLbld());
		}
		if(this.resultado==true){
			this.getLblv();
			contentPane.add(getLblv());
			
		}
		contentPane.add(getBtnS());
		contentPane.add(getBtnNo());
	}
	private JLabel getLblv(){
		lblv = new JLabel("¡VICTORIA!");
		lblv.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lblv.setBounds(57, 23, 235, 48);
		return lblv;
	}
	private JLabel getLbld(){
		lbld = new JLabel("DERROTA...");
		lbld.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		lbld.setBounds(60, 23, 235, 48);
		return lbld;
	}
	private JLabel getLblcontinuar() {
		if (lblcontinuar == null) {
			lblcontinuar = new JLabel("\u00BFCONTINUAR?");
			lblcontinuar.setFont(new Font("Sylfaen", Font.PLAIN, 18));
			lblcontinuar.setBounds(63, 63, 171, 52);
		}
		return lblcontinuar;
	}
	private JButton getBtnS() {
		if (btnS == null) {
			btnS = new JButton("S\u00ED");
			btnS.setBounds(31, 117, 59, 23);
			btnS.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Buscaminas.getConBuildTablero().cerrarTablero();
					Tablero tablero=new Tablero();
					Buscaminas.getConBuildTablero().deleteObservers();
					Buscaminas.getConBuildTablero().guardarDatos(nivel, nombreJ, tablero);
					tablero.inicio();
					setVisible(false);
				}
			});
		}
		return btnS;
	}
	
	private JButton getBtnNo() {
		if (btnNo == null) {
			btnNo = new JButton("No");
			btnNo.setBounds(160, 117, 59, 23);
			btnNo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Top10.getTop10().inicializarTabla(false);
					Top10.getTop10().leerFichero();
					if(resultado==true){
					Top10.getTop10().comprobarRecord(Buscaminas.getConBuildTablero().getUser());
					}
					Top10.getTop10().initialize();
					Top10.getTop10().setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnNo;
	}

}
