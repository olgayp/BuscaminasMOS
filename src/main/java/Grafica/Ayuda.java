package Grafica;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


import javax.swing.*;



public class Ayuda extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jtextarea;
	private JLabel    autores;
	private JPanel contentPane;

	
	/**
	 * Create the frame
	 */
	public Ayuda() {
		super("Acerca de Buscaminas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(760, 212, 250, 100);
		String im = getClass().getClassLoader().getResource("mine.png").toString();
		this.setIconImage(new ImageIcon(im.substring(6)).getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		jtextarea = new JTextArea("   ...");
		jtextarea.setText("Buscaminas: version 1.0");
	    jtextarea.setEditable(false);
	    jtextarea.setVisible(true);
	    jtextarea.setPreferredSize(new Dimension(200,200));
	    contentPane.add(jtextarea);
	    autores = new JLabel("Autores: Susana / Miguel / Olga");
	    autores.setVisible(true);
	    jtextarea.add(autores);
	    autores.setBounds(10, 20, 250, 14);
		setContentPane(contentPane);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args){	
		Ayuda a = new Ayuda();
		a.setVisible(true);
	}

}