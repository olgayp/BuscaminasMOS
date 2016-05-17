package Grafica;


import java.awt.EventQueue;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Buscaminas;
import Model.User;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Top10 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Top10 mtop;
	private JPanel contentPane;
	private JLabel lblMejoresResultados;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblValLabel;
	private JButton aceptar;
	private boolean continuar;
	private User[] top10=new User[10];

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
	 
	public static Top10 getTop10(){
		if(mtop==null){
			mtop=new Top10();
		}
		return mtop;
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 120, 440, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.add(getLblMejoresResultados());
		String im = getClass().getClassLoader().getResource("mine.png").toString();
		this.setIconImage(new ImageIcon(im.substring(6)).getImage());
		aceptar = new JButton("Aceptar");
		aceptar.setName("acepTop");
		aceptar.setBounds(160, 260, 89,23);
		aceptar.addActionListener(new ControlEventos());
		aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (continuar)
				   Top10.this.dispose();
				else{
					Top10.this.dispose();
					Buscaminas.getConBuildTablero().cerrarTablero();
					Buscaminas.getConBuildTablero().deleteObservers();
					Tablero tablero = new Tablero();
					tablero.setVisible(false);
					Logueo log = new Logueo();
					log.setVisible(true);
				}
			}
		});
		contentPane.add(aceptar);
		getLabel();
		getLblNewLabel();
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
			label.setBounds(100, 67+(i*15), 34, 14);
			this.add(label);
			label.setVisible(true);
		}
		
	}
	public void getLblNewLabel() {
		int i=0;
		for (User u:top10){
			i++;
			lblNewLabel = new JLabel(u.getNombre());
			lblNewLabel.setBounds(140, 67+(i*15), 110, 14);
			lblValLabel = new JLabel(String.valueOf(u.getPuntuacion()));
			lblValLabel.setBounds(240, 67+(i*15), 60, 14);
			this.add(lblNewLabel);
			this.add(lblValLabel);
			lblNewLabel.setVisible(true);
		}
	}
	public void inicializarTabla(boolean c){
		continuar = c;
		for(int i=0;i<top10.length;i++){
			top10[i]=new User("Desconocido", 9999);
		}
	}
	
	public void comprobarRecord(User jugador){
		if(top10[9].getPuntuacion()>=jugador.getPuntuacion()){
			System.out.println("puntuacionnnn:"+jugador.getPuntuacion());
			top10[9]=jugador;
			Arrays.sort(top10);
			nuevoRecord(jugador.getPuntuacion());
		}
		actualizarFichero();
		
	}
	public void actualizarFichero(){
		FileWriter fr = null;
		BufferedWriter bw = null;
		try{
			java.net.URL url=getClass().getClassLoader().getResource(Buscaminas.getConBuildTablero().getFichero());
	    	File file =new File(url.getPath());
	    	fr= new FileWriter(file);
	        bw = new BufferedWriter(fr);
	        for (int x=0;x<10;x++){
	            bw.write(top10[x].getNombre()+"\n");
	            bw.write(top10[x].getPuntuacion()+"\n");
	            System.out.println(top10[x].getNombre()+" "+top10[x].getPuntuacion());
	            
	        }
	        bw.close();
		}
		catch(Exception e){
        	System.out.println("No se ha actualizado");
		}
	}


	public void leerFichero(){
		FileReader fr = null;
	    BufferedReader br = null;
	    try
	    {
	        //ruta puede ser de tipo String o tipo File
	        //si usamos un File debemos hacer un import de esta clase
	    	java.net.URL url=getClass().getClassLoader().getResource(Buscaminas.getConBuildTablero().getFichero());
	    	File file =new File(url.getPath());
	        fr = new FileReader(file);
	        br = new BufferedReader( fr );
	        //Obtenemos el contenido del archivo linea por linea
	        String linea = br.readLine();
	        for (int i=0;i<10 && linea!=null;i++){
	        	top10[i].setNombre(linea);
	            top10[i].setResultado(Integer.parseInt(br.readLine()));
	            linea = br.readLine();
	        }
	    }
	        catch (Exception e) {
	        	System.out.println("No se ha leido");
	        	actualizarFichero();
	        	leerFichero();
	        	top10[0].setNombre("Desconocido");
	        }
	    
	}
	
	
	
	public void nuevoRecord(int cont){
		User jugador= new User(Buscaminas.getConBuildTablero().getUser().getNombre(),cont);
		int i;
		int j = 0;
		int c = 0;
		for(i=0;i<top10.length;i++){
			if(top10[i].getNombre().equals(jugador.getNombre())){
				if(top10[i].getPuntuacion()>=cont){
						top10[i].setResultado(cont);
						c = c + 1;
						if (c > 1){
							top10[i].setNombre("Desconocido");
							top10[i].setResultado(9999);
						}
					//	Arrays.sort(top10);
				//		actualizarFichero();
						j=1;
				}
				else{
					j=1;
				}
			}
		}
		if(j!=1){
			comprobarRecord(jugador);
		}
		else{
			Arrays.sort(top10);
			actualizarFichero();
		}
		
	}
}
