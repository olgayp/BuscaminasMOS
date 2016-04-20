package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.Buscaminas;
import Model.NivelDificultad;

public class Continuar extends JFrame {

	private JPanel contentPane;
	private JLabel lblcontinuar;
	private JLabel lblv;
	private JLabel lbld;
	private JButton btnS;
	private JButton btnNo;
	private boolean resultado;
	//guarda el nivel de dificicultad en el que se estaba jugando
	private static NivelDificultad nivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 * @param b 
	 */
	public Continuar(NivelDificultad nivel, boolean b) {
		this.nivel=nivel;
		this.resultado=b;
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblcontinuar());
		if(this.resultado=false){
			
			this.getLbld();
			contentPane.add(getLbld());
		}
		if(this.resultado=true){
			this.getLblv();
			contentPane.add(getLblv());
			
		}
		contentPane.add(getBtnS());
		contentPane.add(getBtnNo());
	}
	private JLabel getLblv(){
		lblv = new JLabel("¡VICTORIA!");
		lblv.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lblv.setBounds(126, 25, 235, 48);
		return lblv;
	}
	private JLabel getLbld(){
		lbld = new JLabel("DERROTA...");
		lbld.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lbld.setBounds(126, 25, 235, 48);
		return lbld;
	}
	private JLabel getLblcontinuar() {
		if (lblcontinuar == null) {
			lblcontinuar = new JLabel("\u00BFCONTINUAR?");
			lblcontinuar.setFont(new Font("Sylfaen", Font.PLAIN, 24));
			lblcontinuar.setBounds(126, 25, 235, 98);
		}
		return lblcontinuar;
	}
	private JButton getBtnS() {
		if (btnS == null) {
			btnS = new JButton("S\u00ED");
			btnS.setBounds(88, 132, 59, 23);
			btnS.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				/*	Buscaminas.getConBuildTablero().cerrarTablero();
					Buscaminas.getConBuildTablero().pararTimer();
					Buscaminas.getConBuildTablero().generarTablero2(nivel); */  
				}
			});
		}
		return btnS;
	}
	private JButton getBtnNo() {
		if (btnNo == null) {
			btnNo = new JButton("No");
			btnNo.setBounds(237, 132, 59, 23);
			btnNo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Top10 top10=new Top10();
					top10.setVisible(true);
					setVisible(false);
					/*Buscaminas gt = Buscaminas.getConBuildTablero();
					gt.cerrarTablero();*/
				}
			});
		}
		return btnNo;
	}
}
