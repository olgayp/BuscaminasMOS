package Grafica;


import Model.Casilla;
import Model.Buscaminas;

import java.util.*;
import java.util.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.*;


import javax.swing.*;



public class Tablero extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8899496547687623725L;
	//Panel general
	private JPanel contentPane;
	//
	private int casActivas;  // numero de casillas activas del panel
	private int minasDescubiertas;
	//
	//Panel para casillas
	//Campos utilizados para casillas
	//
	private JPanel pCasillas;
	
	//
	//Panel para marcador
	//Campos utilizados para el marcador, minas, tiempo y boton de reinicio
	//
	private JPanel marcador;
	private JLabel minas;
	private GrafReinicio reinicio;
	private JLabel tiempo;
	private JLabel min;
	private JLabel crono;
	
	// Campos para usuario y fin partida
	private JPanel jugador;
	private JLabel nombJug;
	private JButton fin;
	
	//Campos para nueva partida
	private JButton nuevo;
	
	//estado del juego 0 - normal, 1 - detenido/parado  2 - finalizado
	private int estadoJuego;
	
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
	
	//
	//User es el nombre del jugador que esta jugando, se debe pasar a la pantalla de continuar cuando
	//acabe el juego
	
	private String nombreJ;
	private int cont;
	private Timer timer;
	
	

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args,final int fila,  final int columna,final String user,final int nivel) {
	public static void main (String [] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la parte grafica
	 * @param reinicio 
	 * @param pm 
	 */
	
	public void inicio() {
		this.nombreJ=Buscaminas.getConBuildTablero().getUser().getNombre();
		casActivas = Buscaminas.getConBuildTablero().getNivel().getNumColumnas()*
			        	Buscaminas.getConBuildTablero().getNivel().getNumFilas();
		this.initialize();
		generarMenu();
		Buscaminas.getConBuildTablero().construirMatriz();
		this.setLocationRelativeTo(null);
	}
	
	private void initialize() {
		
		
		setTitle("Buscaminas "+Buscaminas.getConBuildTablero().getNivel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		String im = getClass().getClassLoader().getResource("mine.png").toString();
		this.setIconImage(new ImageIcon(im.substring(6)).getImage());
		contentPane.setBorder((new MyBorder(false)).getBorde());
		setContentPane(contentPane);
		cont = 0;

		marcador = new JPanel();
		minas = new JLabel(String.valueOf(Buscaminas.getConBuildTablero().getNivel().getNumMinas()));
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

		reinicio = new GrafReinicio(Buscaminas.getConBuildTablero().getNivel(),nombreJ);
		reinicio.setPreferredSize(new Dimension(35,35));
		reinicio.setBorder((new MyBorder(false)).getBorde1());
		
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
		rest = 10*(Buscaminas.getConBuildTablero().getNivel().getNumColumnas()%2);
		int p = 10+((Buscaminas.getConBuildTablero().getNivel().getNumColumnas()/2)*21)+rest+1;
		reinicio.setBounds(p, 15, 35, 35);
	    marcador.add(tiempo);
	    tiempo.setSize(39,35);
	    p = 21+((Buscaminas.getConBuildTablero().getNivel().getNumColumnas()-2)*21);
	    tiempo.setBounds(p, 15, 39,35);
	    p = tiempo.getX()+tiempo.getWidth();
	    crono.setSize(18,18);
	    crono.setBounds(p, 18, 18, 18);
		marcador.add(crono);
		marcador.setBorder((new MyBorder(false)).getBorde());
		add(marcador);
		
	}
	
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
	
	public void actualizarTablero(Casilla[][] tablero){
		pCasillas = new JPanel(); 
		pCasillas.setBorder((new MyBorder(false)).getBorde());
		pCasillas.setLayout(new GridLayout(Buscaminas.getConBuildTablero().getNivel().getNumFilas(), Buscaminas.getConBuildTablero().getNivel().getNumColumnas(), 0, 0)); 
		add(pCasillas);
		
		// añade las casillas segun la matriz que ha generado conBuildTablero
		for (int i = 0; i < Buscaminas.getConBuildTablero().getNivel().getNumFilas(); i++) {
			for (int j = 0; j < Buscaminas.getConBuildTablero().getNivel().getNumColumnas(); j++) {
				//Generar Etiqueta Vacia para el boton-Casilla
				JLabel l1 = generarEtiquetaValor(tablero[i][j]);
				//crear boton-casilla para añadir al panel con su etiqueta
				GrafCasilla gc = new GrafCasilla(tablero[i][j], l1);
				// poner a la casilla en observador
				//gc.getCasilla().addObserver(ConBuildTablero);
				pCasillas.add(gc);
				//if (gc.getCasilla().getValor() == -9)
				//    gc.setBackground(Color.RED);  //rojo minas para probar ganar
 			}
		}
	
		// panel para los datos del jugador y boton fin de partida y nueva partida
		jugador = new JPanel();
		jugador.setBorder((new MyBorder(false)).getBorde());
		nombJug = new JLabel(Buscaminas.getConBuildTablero().getUser().getNombre());
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
		fin.addActionListener(new ControlEventos());
		nuevo.addActionListener(new ControlEventos());
				
				
		contentPane.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);   //centra el JFrame
		this.setVisible(true);
		pack();
		setResizable(false); 
		
	}
	public void gestionDeTiempo(){
		this.sumarContador(String.valueOf(cont));
		timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			public void run (){
				cont = cont + 1;
				sumarContador(String.valueOf(cont));
			}
		};
		timer.scheduleAtFixedRate(timerTask, cont, 1000);
	}
	
	public void pararTimer(){
		timer.cancel();
	}
	public JPanel getPCasillas(){
		return pCasillas;
	}
	public int getEstadoJuego(){
		return estadoJuego;
	}
	public void setEstadoJuego(int b){
		estadoJuego = b;
		
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

	public JLabel generarEtiquetaValor(Casilla c){
		String sl = getClass().getClassLoader().getResource(c.getImagen()).toString();
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(sl.substring(6)));
		return l;
	}
	//Suma uno al contador de tiempo cada segundo
	public void sumarContador(String valueOf){
	     tiempo.setText(valueOf);
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
	
	public int getMinas(){
		return Integer.valueOf(minas.getText());
	}
	public int getCasActivas(){
		return casActivas;
	}
	public int getMinasDescubiertas(){
		return minasDescubiertas;
	}
	
	public int getContador(){
		return cont;
	}
	public void update(Observable o, Object arg) {
		
		if(arg.getClass()==Casilla[][].class){
		    this.actualizarTablero((Casilla[][]) arg);
		}
		
		if(arg.equals("iniciar")){
			if(cont==0){
				this.gestionDeTiempo();
			}
			else{
				Buscaminas.getConBuildTablero().ConsultarVictoria();

			}
		}
		
		if(arg.equals("victoria")){
			timer.cancel();
			reinicio.cambiarImagen("gano.png");
			desactivarRestoCasillas();
			Buscaminas.getConBuildTablero().musicaMaestro(true);
			Continuar continuar=new Continuar(Buscaminas.getConBuildTablero().getNivel(),true,nombreJ);
			continuar.setVisible(true);
			Buscaminas.getConBuildTablero().setCont(cont);
		}
		if(arg.equals("pararPierdo")){
			reinicio.cambiarImagen("perdio.png");
			Buscaminas.getConBuildTablero().musicaMaestro(false);
			Continuar continuar=new Continuar(Buscaminas.getConBuildTablero().getNivel(),false,nombreJ);
			continuar.setVisible(true);
			timer.cancel();
		}
		
		if(arg.equals("marcar")){
			this.restarMinas();
		}
		if(arg.equals("desmarcar")){
        	this.sumarMinas();

		}
		if (arg.equals("marcarMinaDescubierta")){
			restarMinas();	
			sumarMinaDescubierta();
		}
		
		if (arg.equals("desmarcarMinaOculta")){
			sumarMinas();
        	restarMinaDescubierta();
		}
		
		if (arg.equals("destapar")){
			casActivas = casActivas  - 1;
		}
		
		if(arg.equals("cerrar")){
			this.setVisible(false);
		}
	
	}
		
}
				

	