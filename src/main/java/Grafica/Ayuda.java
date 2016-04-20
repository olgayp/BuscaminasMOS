package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.util.*;
import javax.swing.*;

import Model.Buscaminas;


public class Ayuda extends JFrame {
	private JTextArea jtextarea;
	private JPanel contentPane;

	
	/**
	 * Create the frame
	 */
	public Ayuda() {
		super("Acerca de Buscaminas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(760, 212, 250, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		jtextarea = new JTextArea("   ...");
		jtextarea.setText("Buscaminas: version 1.0");
	    jtextarea.setEditable(false);
	    jtextarea.setVisible(true);
	    jtextarea.setPreferredSize(new Dimension(200,200));
	    contentPane.add(jtextarea);
		setContentPane(contentPane);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args){	
		Ayuda a = new Ayuda();
		a.setVisible(true);
	}

}