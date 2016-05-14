package Grafica;
import java.awt.BorderLayout;

import Model.NivelDificultad;

import java.util.Timer;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.BoxLayout;

import java.awt.*;

import javax.swing.border.SoftBevelBorder;

import Model.Casilla;
import Model.PosiMina;

import javax.swing.border.BevelBorder;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Model.ListaMinas;
/**
 * 
 * @author 
 *
 */
public class Tablero extends JFrame{
	
	//Panel general
	private JPanel contentPane;
	// numero de casillas activas 
	private int casActivas;  
	//Panel para casillas
	private JPanel pCasillas;
	//Matriz de Casillas
	private Casilla [][] tablero ;
	// Barra de menu
	// Campos para elementos de Menu
	private JMenuBar barraMenu;
	private JMenu juego;
	private JMenu ayuda;
	private JMenuItem juegoNuevo;
	private JMenuItem juegoReiniciar;
	private JMenuItem juegoDetener;
	private JMenuItem juegoContinuar;
	private JMenuItem juegoPista;
	private JMenuItem juegoSalir;
	private JMenuItem acercaDe;
	//Panel para marcador
	//Campos utilizados para el marcador, minas, tiempo y boton de reinicio
	private JPanel marcador;
	private JLabel min;
	private JLabel minas;
	private JLabel tiempo;
	private JLabel crono;
	private JButton reinicio;
	private int minasDescubiertas = 0;
    // Campos para usuario y fin partida
	private JPanel jugador;
	private JLabel nombJug;
	private JButton fin;
	//Campos para nueva partida
	private JButton nuevo;
	// Campos para contador de tiempo de partida
	private  int cont = 0;
	private Timer timer = null; 
	private TimerTask timerTask;
	//estado del juego 0 - normal, 1 - detenido/parado  2 - finalizado
	private int estadoJuego;
	//User es el nombre del jugardor
	private String user;
	//Nivel de dificultad de juego (FACIL, MEDIO o DIFICIL)
	private NivelDificultad nivel;
	//Icono personalizado
	private Icon iconoBusc;
	//Lista de minas del tablero
	private ListaMinas lm;
	
	//Buscaminas inf: Singleton
	//estadoJuego
	//usuario, timer, nivel, innicializar,
	
	//Builder Casilla ---> crear matriz
	
	//Dependencias a traves de Buscaminas(opc) 
	
	/**
	 * Launch the application.
	 */
	//public static void main(String[] args,final int fila,  final int columna,final String user,final int nivel) {
	public static void main (String [] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Tablero2 frame = new Tablero2(fila,columna,user,nivel );
					Tablero frame = new Tablero(NivelDificultad.FACIL,"hola");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create el Frame - Vista del tablero de juego
	 * @param fila
	 * @param columna
	 * @param user
	 * @param nivel
	 */
	public Tablero(NivelDificultad niv, String user) {
		
		int fila = niv.getNumFilas();
		int columna = niv.getNumColumnas();
		if (user.equals(""))
			this.user = "Desconocido";
		else
		   this.user=user;
		nivel= niv;
		casActivas = fila*columna;
		lm = new ListaMinas(nivel);
		
		//personalizar Icono
		String im = getClass().getClassLoader().getResource("imgBusc.jpg").toString();
		iconoBusc = new ImageIcon(im.substring(6));
		this.setIconImage(((ImageIcon)iconoBusc).getImage());
		
		//Generar Barra de Menu
		generarMenu();
		//inicializar
		initialize(fila, columna);
		pack();
	}
	private void initialize(int fila,int columna) {
		
		setTitle("Buscaminas "+nivel.name());
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder((new MyBorder(false)).getBorde());
		setContentPane(contentPane);
	
		marcador = new JPanel();
		minas = new JLabel(String.valueOf(nivel.getNumMinas()));
	    minas.setForeground(Color.RED);
		minas.setFont(new Font("Agency FB", Font.BOLD, 25));
		minas.setOpaque(true);
		minas.setBackground(Color.BLACK);
		minas.setBorder((new MyBorder(true)).getBorde1());
		
		min = new JLabel();
		String s = getClass().getClassLoader().getResource("min.png").toString();
		min.setIcon(new ImageIcon(s.substring(6)));
		
		tiempo = new JLabel("0");
		tiempo.setBorder((new MyBorder(true)).getBorde1());
	    tiempo.setForeground(Color.RED);
	    tiempo.setFont(new Font("Agency FB", Font.BOLD, 25));
		tiempo.setOpaque(true);
		tiempo.setBackground(Color.BLACK);
		
		crono = new JLabel();
		String s1 = getClass().getClassLoader().getResource("Crono.png").toString();
		crono.setIcon(new ImageIcon(s1.substring(6)));

		reinicio = new JButton();
		reinicio.setBorder((new MyBorder(false)).getBorde1());
		String s2 = getClass().getClassLoader().getResource("nueva.png").toString();
		reinicio.setIcon(new ImageIcon(s2.substring(6)));
		reinicio.setName("reinicio");
		
		marcador.setLayout(null);
	    marcador.setSize(60,62);
		marcador.setPreferredSize(new Dimension(60,62));
		
		min.setSize(18,18);
		marcador.add(min);
		min.setBounds(13, 18, 18, 18);
		marcador.add(minas);
		minas.setSize(39,35);
		minas.setBounds(32, 15, 39, 35);
		marcador.add(reinicio);
		int rest = 0;
		rest = 10*(nivel.getNumColumnas()%2);
		int p = 10+((nivel.getNumColumnas()/2)*21)+rest+1;
		reinicio.setBounds(p, 15, 35, 35);
	    marcador.add(tiempo);
	    tiempo.setSize(39,35);
	    p = 21+((nivel.getNumColumnas()-2)*21);
	    tiempo.setBounds(p, 15, 39,35);
	    p = tiempo.getX()+tiempo.getWidth();
	    crono.setSize(18,18);
	    crono.setBounds(p, 18, 18, 18);
		marcador.add(crono);
		marcador.setBorder((new MyBorder(false)).getBorde());
		add(marcador);
		
		
		pCasillas = new JPanel(); 
		pCasillas.setBorder((new MyBorder(false)).getBorde());
		pCasillas.setLayout(new GridLayout(fila, columna, 0, 0)); 

	
		// Inicializar  matriz de  casillas vacias
		tablero  = new Casilla [fila][columna];
		for (int i = 0; i < fila; i++){
			for (int j = 0; j < columna; j++){
				tablero[i][j] = new Casilla(0,"",i,j);
			}
		}
		// Posicionar minas en tablero
		generarMinasTablero(fila,columna);
		//generar valores de las casillas del tablero
		generarValoresTablero(fila,columna);
		// añadir casillas-botones al panel 
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				//Generar Etiqueta Vacia para el boton-Casilla
				JLabel l1 = generarEtiquetaValor(tablero[i][j]);
				//crear boton-casilla para añadir al panel con su etiqueta
				GrafCasilla gc = new GrafCasilla(tablero[i][j], l1);
				pCasillas.add(gc);   
				//if (gc.getCasilla().getValor() == -9){
					//gc.setBackground(Color.RED);
				//}
			}
		}
		add(pCasillas);
		
		// panel para los datos del jugador y boton fin de partida y nueva partida
		jugador = new JPanel();
		jugador.setBorder((new MyBorder(false)).getBorde());
		nombJug = new JLabel(user);
		nombJug.setFont(new Font("MV Boli", Font.BOLD, 10));
		fin = new JButton();
		fin.setBackground(Color.ORANGE);
		fin.setFont(new Font("Dialog",Font.BOLD, 8));
		fin.setName("fin");
		fin.setIcon(new ImageIcon(getClass().getClassLoader().getResource("btnFin.png").toString().substring(6)));
		fin.setBorderPainted(false);
		fin.setContentAreaFilled(false);
		fin.setSize(20,20);
		nuevo = new JButton();
		nuevo.setBackground(Color.ORANGE);
		nuevo.setFont(new Font("Dialog",Font.BOLD, 8));
		nuevo.setName("nuevo");
		nuevo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("btnNew.png").toString().substring(6)));
		nuevo.setBorderPainted(false);
		nuevo.setContentAreaFilled(false);
		nuevo.setSize(20,20);
		jugador.setLayout(new GridLayout(1,2,0,0));
		jugador.add(nombJug);
		jugador.add(fin);
		jugador.add(nuevo);
		jugador.setPreferredSize(new Dimension(60,50));
		add(jugador);
		
		contentPane.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		pack();
		
		// Centrar el JFrame y desactivar boton maximizar
		setLocationRelativeTo(null);   
		this.setResizable(false); 
		
	}
	/**
	 * Generar las posiciones de las minas en la matriz de casillas
	 * @param f filas del tablero 
	 * @param c columnas del tablero
	 */
	
	public void generarMinasTablero(int f, int c){
		int numMinas = 0;
		int vf = 0;
		int vc = 0;
		
		numMinas = nivel.getNumMinas();
		Random r = new Random();  //funcion ramdom() para generar posiciones de minas
		
		PosiMina [] pm = new PosiMina[numMinas];
		for (int s = 0; s < numMinas; s++){
			pm[s]= new PosiMina(0,0);
		}
		int ipm = 0;
		for (int k = 0; k < numMinas; k++){
			vf = r.nextInt(f);
			vc = r.nextInt(c);
			System.out.println (vf+" "+vc);
			int l = 0;
			while (l < numMinas){ //controlar que ramdom no ha dado una posicion de mina ya existente
				PosiMina psm = new PosiMina(vf,vc);  // se genera una poscion de mina
				
				if ((pm [l].getFbomb() == psm.getFbomb()) &&  //ha dado la misma posicion
				    (pm [l].getCbomb() == psm.getCbomb()) ){  //buscar otra posicion
					vf = r.nextInt(f);
				    vc = r.nextInt(c);
				    l = 0;
				    System.out.println ("aqui"+vf+" "+vc);
				}
				else
			       l = l + 1;
				     
			}
			String s = getClass().getClassLoader().getResource("9.png").toString();
      		tablero [vf][vc] = new Casilla(-9,s.substring(6),vf,vc);
			pm [ipm] = new PosiMina(vf,vc);   //Guardar posicion de mina generada
			ipm = ipm + 1;
			lm.add(new PosiMina(vf,vc));
		}
		
	}
	/**
	 * Generar los valores numericos en la matriz de casillas
	 * @param f filas del tablero
	 * @param c columnas del tablero
	 */
	public void generarValoresTablero(int f, int c){
		for (int i = 0; i < f ; i++){
			for (int j = 0; j < c; j++){
				if (tablero[i][j].getValor() == -9){ //si hay una mina sumar uno a sus casillas vecinas
					if (j+1 < c && tablero[i][j+1].getValor()!=-9)
						//siguiente
						tablero[i][j+1].setValor(tablero[i][j+1].getValor()+1);
					if (j-1 >= 0 && tablero[i][j-1].getValor()!=-9)
				    	//anterior 
						tablero[i][j-1].setValor(tablero[i][j-1].getValor()+1);
					if (i-1 >= 0 && tablero[i-1][j].getValor()!=-9)
				    	//arriba
						tablero[i-1][j].setValor(tablero[i-1][j].getValor()+1);
				    if (i+1 < f && tablero[i+1][j].getValor()!=-9 )
				    	//abajo
				    	tablero[i+1][j].setValor(tablero[i+1][j].getValor()+1);
				    if (i-1 >= 0 && j+1 < c && tablero[i-1][j+1].getValor()!=-9)
				    	//superior derecha
				    	tablero[i-1][j+1].setValor(tablero[i-1][j+1].getValor()+1);	
				    if (i-1 >=0 && j-1 >= 0 && tablero[i-1][j-1].getValor()!=-9)
				    	//superior izquierda
				    	tablero[i-1][j-1].setValor(tablero[i-1][j-1].getValor()+1);
				    if (i+1 < f && j+1 < c && tablero[i+1][j+1].getValor()!=-9)
				    	//inferior derecha
				    	tablero[i+1][j+1].setValor(tablero[i+1][j+1].getValor()+1);
				    if (i+1 < f && j-1 >= 0 && tablero[i+1][j-1].getValor()!=-9)
				    	//inferior izquierda
				    	tablero[i+1][j-1].setValor(tablero[i+1][j-1].getValor()+1);
				}
			}
		}	
	}
	
   
	/**
	 * Gestion de tiempo de la partida, crear un timer que actulizara
	 * un contador cada segundo.
	 */
	public void gestionDeTiempo(){
		timer = new Timer();
		timerTask = new TimerTask(){
			public void run (){
				sumarContador();	
				tiempo.setText(String.valueOf(getContador()));
			}
		};
	    timer.scheduleAtFixedRate(timerTask, cont, 1000);
	}
	/**
	 * Genera la etiqueta valor de la casilla, para ser visualizada en el panel de juego
	 * @param c casilla a la que generar su etiqueta
	 * @return
	 */
	public JLabel generarEtiquetaValor(Casilla c){
		String img = "00.png";
		if (c.getValor() == 0)
			img = "00.png";
		if (c.getValor() == 1)
			img = "11.png";
		if (c.getValor() == 2)
			img = "22.png";
		if (c.getValor() == 3)
			img = "33.png";
		if (c.getValor() == 4)
			img = "44.png";
		if (c.getValor() == 5)
			img = "55.png";
		if (c.getValor() == 6)
			img = "66.png";
		if (c.getValor() == 7)
			img = "77.png";
		if (c.getValor() == 8)
			img = "88.png";
		if (c.getValor() == -9)
			img = "99.png";
		String sl = getClass().getClassLoader().getResource(img).toString();
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(sl.substring(6)));
		return l;
	}
	
	/**
	 * sumar uno al contador de tiempo
	 */
	public void sumarContador(){
		cont = cont + 1;
	}
	
	/**
	 * Getters
	 * 
	 */
	//Devolver tarea de Reloj
	public TimerTask getTimerTask(){
		return timerTask;
	}
	//Devolver situacion de juego
	public int getEstadoJuego(){
		return estadoJuego;
	}
	
	//Devolver casillas activas
	public int getCasActivas(){
		return casActivas;
	}
	
	//Devuelve el contador de tiempo
	public int getContador(){
		return cont;
	}
	//Devolver minas
	public JLabel getMinas(){
		return minas;
	}
	//Devolver panel casillas
	public JPanel getPCasillas(){
		return pCasillas;
	}
	//Devolver panel marcador
	public JPanel getMarcador(){
		return marcador;
	}
	//Devolver boton reinicio
	public JButton getReinicio(){
		return reinicio;
	}
	//Devolver timer
	public Timer getTimer(){
		return timer;
	}
	//Devolver boton de fin de partida
	public JButton getFin(){
		return fin;
	}
	//Devolver el boton de nueva partida
	public JButton getNuevo(){
		return nuevo;
	}
	//Devolver jmenuitem reiniciar
	public JMenuItem getJuegoReiniciar(){
		return juegoReiniciar;
	}
	//Devolver iconoBuscaminas
	public Icon getIconoBusc(){
		return iconoBusc;
	}
	//Devolver minas descubiertas 
	public int getMinasDescubiertas(){
		return minasDescubiertas;
	}
	//Devolver nivel
	public NivelDificultad getNivel(){
		return nivel;
	}
	//Devolver lista de minas
	public ListaMinas getListaMinas(){
		return lm;
	}
	//restar numero de minas
	public void restarMinas(){
		int m = Integer.valueOf(minas.getText());
		m = m - 1;
		minas.setText(String.valueOf(m));

	}
	//sumar numero de minas
	public void sumarMinas(){
		int m = Integer.valueOf(minas.getText());
		m = m + 1;
		minas.setText(String.valueOf(m));
	}
	//sumar uno al contador de minas descubiertas
	public void sumarMinaDescubierta(){
		minasDescubiertas = minasDescubiertas + 1;
	}
	//restar uno al contador de minas descubiertas
	public void restarMinaDescubierta(){
		minasDescubiertas = minasDescubiertas - 1;
	}
	//poner marcador de juego detenido
	public void setEstadoJuego(int e){
		estadoJuego = e;
	}
	//actualizar casillas activas
	public void setCasillasActivas(int num){
		casActivas = num;
	}
	
	//Genera la barra de menu
	public void generarMenu(){
		barraMenu = new JMenuBar();
		juego=new JMenu("Juego");
		juego.setMnemonic('J');
		ayuda=new JMenu("Ayuda");
		ayuda.setMnemonic('u');
		barraMenu.add(juego);
		barraMenu.add(ayuda);
		juegoNuevo = new JMenuItem("Nuevo", 'N');
		juegoReiniciar = new JMenuItem("Reiniciar", 'R');
		juegoDetener = new JMenuItem("Detener", 'D');
		juegoContinuar = new JMenuItem("Continuar", 'C');
		juegoPista = new JMenuItem("Pista",'P');
		juegoSalir = new JMenuItem("Salir", 'S');
		juego.add(juegoNuevo);
		juego.add(juegoReiniciar);
		juego.add(juegoDetener);
		juego.add(juegoContinuar);
		juego.add(juegoPista);
		juego.add(juegoSalir);
		juegoNuevo.setName("juegoNuevo");
		juegoReiniciar.setName("juegoReiniciar");
		juegoDetener.setName("juegoDetener");
		juegoContinuar.setName("juegoContinuar");
		juegoPista.setName("juegoPista");
		juegoSalir.setName("juegoSalir");
		acercaDe = new JMenuItem("Acerca de ...", 'A');
		ayuda.add(acercaDe);
		acercaDe.setName("acercaDe");
		juegoNuevo.addActionListener(new ControlEventos());
		juegoReiniciar.addActionListener(new ControlEventos());
		juegoDetener.addActionListener(new ControlEventos());
		juegoContinuar.addActionListener(new ControlEventos());
		juegoPista.addActionListener(new ControlEventos());
		juegoSalir.addActionListener(new ControlEventos());
		acercaDe.addActionListener(new ControlEventos());
		setJMenuBar(barraMenu);
		
	}

	public void desactivarRestoCasillas(){
	     for (int i = 0; i < pCasillas.getComponentCount(); i++){
	    	 if (!(pCasillas.getComponent(i) instanceof JLabel)){
	    	     if (pCasillas.getComponent(i).isEnabled()){
			        //*************** Sustituir botones por etiquetas al descubrir casilla
	    	    	 String newEtiq = null;
	    	    	 if (((GrafCasilla)pCasillas.getComponent(i)).getCasilla().getEstado() == 2){
	    	    		 newEtiq = getClass().getClassLoader().getResource("B11.png").toString();
	    	    	 }
	    	    	 else{
	    	    		 newEtiq = getClass().getClassLoader().getResource("0B.png").toString();
	    	    	 }
	    	        ((GrafCasilla)pCasillas.getComponent(i)).setEtiqueta(newEtiq.substring(6));
				    pCasillas.add(((GrafCasilla)pCasillas.getComponent(i)).getEtiqueta(), i);
				    pCasillas.remove(i+1);
				    //*************** Sustituir botones por etiquetas al descubrir casilla
			    }
			 }
	     }
	}
	
	public void activarDesactivarCasillas(boolean actDes){
		for (int i = 0; i < pCasillas.getComponentCount(); i++){
	    	 if (!(pCasillas.getComponent(i) instanceof JLabel)){
	    		 pCasillas.getComponent(i).setEnabled(actDes);
			 }
	     }
		nuevo.setEnabled(actDes);
	}
}
