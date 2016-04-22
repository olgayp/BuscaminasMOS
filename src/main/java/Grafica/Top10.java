package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import Model.Buscaminas;
import Model.ControlEventos;
import Model.User;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.Hashtable;
import java.io.*;

public class Top10 extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblMejoresResultados;
	private JLabel label;
	private JLabel lblNewLabel;
	private JButton aceptar;
	private ArrayList<User> top10;
	public static final int MEJORES=10; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Top10 frame = new Top10();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param top 
	 */
	public Top10(){
		this.top10 = new ArrayList<User>();
		inicializarTop10();
		initialize();
	}
	
	private void initialize() {
		setTitle("Buscaminas: Los/as 10 mejores");
		String im = getClass().getClassLoader().getResource("imgBusc.jpg").toString();
		ImageIcon iconoBusc = new ImageIcon(im.substring(6));
		this.setIconImage(((ImageIcon)iconoBusc).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(850, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textArea = new JTextArea();
		textArea.setBounds(20, 60, 350, 200);
		textArea.setEditable(false);
		textArea.setBorder((new MyBorder(true)).getBorde1());
		contentPane.setLayout(null);
		contentPane.add(getLblMejoresResultados());
		contentPane.add(textArea);
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(130, 280, 89,23);
		aceptar.addActionListener(new ControlEventos());
		contentPane.add(aceptar);
		getLabel();
		setResizable(false);
	}

	private JLabel getLblMejoresResultados() {
		if (lblMejoresResultados == null) {
			lblMejoresResultados = new JLabel("MEJORES RESULTADOS");
			lblMejoresResultados.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblMejoresResultados.setBounds(78, 11, 310, 60);
		}
		return lblMejoresResultados;
	}
	
	public void getLabel() {
		for(int i=1;i<11;i++){
			label = new JLabel(i+"\u00BA");
			label.setBounds(10, 10+(i*15), 34, 14);
			textArea.add(label);
			label.setVisible(true);
		}
		
	}
	
	public void getLblNewLabel() {
		int i=0;
		for (User u:top10){
			i++;
			lblNewLabel = new JLabel(String.valueOf(u.getRecord()));
			lblNewLabel.setBounds(35, 10+(i*15), 50, 14);
			textArea.add(lblNewLabel);
			lblNewLabel = new JLabel(u.getNombre());
			lblNewLabel.setBounds(90, 10+(i*15), 150, 14);
			textArea.add(lblNewLabel);
			String fecha = u.getFechRecord().get(Calendar.DATE) +"-"+
					       (u.getFechRecord().get(Calendar.MONTH)+1)+"-"+
					       u.getFechRecord().get(Calendar.YEAR);
	     
			lblNewLabel = new JLabel(String.valueOf(fecha));
			lblNewLabel.setBounds(220, 10+(i*15), 120, 14);
			textArea.add(lblNewLabel);
			lblNewLabel.setVisible(true);
			
		}
	}
	
	public JButton getBtnAceptar() {
		return aceptar;
	}
	
	public void inicializarTop10(){
		leerFichero();
	
	}
	
	public void visualiazarTop10(){
	
		comprobarRecord();
		getLblNewLabel();
		this.setVisible(true);
	}
	
	public void comprobarRecord(){
		boolean enc = false;
		for (int i = 0; i < MEJORES && !enc; i++){
		   if (Buscaminas.getBuscaminas().getUser().getRecord() <
				top10.get(i).getRecord()){
			    top10.add(i,Buscaminas.getBuscaminas().getUser());
			    top10.remove(MEJORES);
		        enc = true;   
		   }
		}
		actualizarFichero();
	}
	
	public void actualizarFichero(){
		try{
		   //String file
		   //= getClass().getClassLoader().getResource("fichero.obj").toString().substring(6);
		   FileOutputStream fileOut = new FileOutputStream("fichero.obj");
	       ObjectOutputStream salida=new ObjectOutputStream(fileOut);
	       for (int j = 0; j < MEJORES; j++){
	            salida.writeObject(top10.get(j));
	       }
	       salida.close();
		}catch(Exception e){};
	}


	public void leerFichero(){
		try{
			//String file
			//= getClass().getClassLoader().getResource("fichero.obj").toString().substring(6);
			int i = 0;
	    	FileInputStream fileIn = new FileInputStream("fichero.obj");
	    	ObjectInputStream entrada=new ObjectInputStream(fileIn);
	    	Object u = null;
	    	u = entrada.readObject();
	    	while(u != null){
	    		top10.add(i,(User)u);
	    		i = i+1;
	            u = entrada.readObject();
	    	}
	        entrada.close();
		}catch(Exception e){};
	}
}
