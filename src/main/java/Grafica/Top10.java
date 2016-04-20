package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import Model.ControlEventos;
import Model.User;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.Hashtable;

public class Top10 extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblMejoresResultados;
	private JLabel label;
	private JLabel lblNewLabel;
	private JButton aceptar;
	private User[] top10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Top10 frame = new Top10(null);
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
		this.top10 = new User[10];;
		inicializarTop10();
		initialize();
	}
	public Top10(User[] top) {
		this.top10=top;
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
		textArea.setBounds(65, 60, 280, 200);
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
		getLblNewLabel();
		setVisible(true);
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
			//label.setBounds(113, 67+(i*15), 34, 14);
			label.setBounds(30, 10+(i*15), 34, 14);
			textArea.add(label);
			//this.add(label);
			label.setVisible(true);
		}
		
	}
	public void getLblNewLabel() {
		int i=0;
		for (User u:top10){
			i++;
			lblNewLabel = new JLabel(String.valueOf(u.getRecord()));
			lblNewLabel.setBounds(70, 10+(i*15), 30, 14);
			textArea.add(lblNewLabel);
			lblNewLabel = new JLabel(u.getNombre());
			//lblNewLabel.setBounds(180, 67+(i*15), 148, 14);
			lblNewLabel.setBounds(115, 10+(i*15), 140, 14);
			textArea.add(lblNewLabel);
			//this.add(lblNewLabel);
			lblNewLabel.setVisible(true);
			
		}
	}
	public JButton getBtnAceptar() {
		return aceptar;
	}
	
	public void inicializarTop10(){
		for(int i=0;i<top10.length;i++)
			top10[i]=new User("Desconocido","FACIL",99999);
	}
	
	//Insertar algoritmo de ordenacion aqui
	public void ordenarTabla(){
		User jugador= new User(top10[1].getNombre(),top10[1].getNivel(),top10[1].getRecord());
		int cont = top10[1].getRecord();
		if(top10[9].getRecord()>=cont){
			top10[9]=jugador;
			for(int p=1;p<top10.length;p++){
				User tmp=top10[p];
				int j;
				for(j=p;j>0&&tmp.getRecord()<top10[j-1].getRecord();j--){
					top10[j]=top10[j-1];
				}
				top10[j]=tmp;
			}
		}
		
	}
}
