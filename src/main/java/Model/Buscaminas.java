package Model;

import Grafica.Reproductor;
import Grafica.Tablero;
import Grafica.Top10;
import Model.NivelDificultad;


import java.util.*;





/**
 * COSAS QUE HACER
 * usar un patron builder para construir la matriz de minas HECHO
 * 
 * sacar logueo de fuera,y usarlo para crear las clases tablero y buscaminas HECHO
 * 
 * tablero observa a buscaminas, cada vez que se toca una casilla el tablero invoca un metodo de buscaminas
 * (se puede hacer porque buscaminas es singleton)
 * y el buscaminas cambia su matriz de casillas y notifica del cambio a los observadores HECHO
 * 
 * un nuevo Top10 se crea en model y es singleton HECHO
 */





/**
 * 
 * Clase "Concreta Builder", Producto Concreto que extiende a BuilderTablero
 * @param <V>
 * @param <K>
 *
 */

public class Buscaminas extends Observable implements Observer{
	
	private static Buscaminas mConBuilderTablero = new Buscaminas();
	
	public Buscaminas(){
	}
	
	
	private NivelDificultad nivel;
	//guarda donde estan las minas
	private PosiMina[]pm;
	
	//Una copia de como es el tablero en el que vamos a jugar
	private Casilla[][]matriz;
	
	private String nombreJ;
	//fuera de aqui! private User[] top10=new User[10](singleton);
	
	private  int casActivas;
	private int minasMarcadas;
	private int cont;
	private Tablero tablero;
	private User user;
	
	
	public static Buscaminas getConBuildTablero(){
		return mConBuilderTablero;
	}
	
	public void guardarDatos(NivelDificultad nivel, String nombreJ,Tablero tablero) {
		this.nivel=nivel;
		this.nombreJ=nombreJ;
		minasMarcadas=0;
		casActivas=nivel.getNumFilas()*nivel.getNumColumnas()-1;
		this.tablero = tablero;
		user = new User(nombreJ, 0000);
		Top10.getTop10().inicializarTabla(true);
		Top10.getTop10().leerFichero();
		this.addObserver(tablero);
	}
	
	public void construirMatriz(){
		Casilla[][] MB= Matriz.getMatriz().construirMatriz(nivel);
		setChanged();
		notifyObservers(MB);
		
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg.equals("iniciar")){
				setChanged();
				notifyObservers("iniciar");
		   
		}
		if (arg.equals("pararPierdo")){
			setChanged();
			notifyObservers("pararPierdo");
		}
		if (arg.equals("marcar")){
			minasMarcadas = minasMarcadas + 1;
			setChanged();
			notifyObservers("marcar");
			this.ConsultarVictoria();
		}
        if (arg.equals("desmarcar")){
        	minasMarcadas = minasMarcadas - 1;
        	setChanged();
        	notifyObservers("desmarcar");
		}
        if (arg.equals("destapar")){
        	setChanged();
			notifyObservers("destapar");
        	this.ConsultarVictoria();
	    }
        
        if (arg.equals("marcarMinaDescubierta")){
        	minasMarcadas = minasMarcadas + 1;
        	setChanged();
			notifyObservers("marcarMinaDescubierta");
			this.ConsultarVictoria();
		}
		if (arg.equals("desmarcarMinaOculta")){
			minasMarcadas = minasMarcadas - 1;
			setChanged();
			notifyObservers("desmarcarMinaOculta");
        	this.ConsultarVictoria();
		}
     
	}
	public void cerrarTablero(){
		setChanged();
		notifyObservers("cerrar");
	}
	
    public PosiMina[] getPm (){
    	return pm;
    }
    public NivelDificultad getNivel() {
	     return nivel;
    }


    public User getUser() {
	     return user;
    }
    public int getMinasMarcadas(){
    	return minasMarcadas;
    }
	
	public void ConsultarVictoria(){
		if (tablero.getCasActivas() == nivel.getNumMinas()
		 || 
			tablero.getMinasDescubiertas() == nivel.getNumMinas()){
			setChanged();
			notifyObservers("victoria");
		}
	}
	
	public void setCont(int c){
		cont=c;
		user.setResultado(cont);
	}
	public int getCont(){
		return cont;
	}
	public Tablero getTablero(){
		return tablero;
	}
	public void setPosiMina(PosiMina[] pm){
		this.pm = pm;
	}

	public void musicaMaestro(boolean b){
		if (b)
		  Reproductor.getReproductor().reproducirVictoria();
		else
		  Reproductor.getReproductor().reproducirDerrota();
	}
	
	public String getFichero(){
		if(nivel==NivelDificultad.FACIL){
			return "fichero-l1.txt";
		}
		if(nivel==NivelDificultad.MEDIO){
			return "fichero-l2.txt";
		}
		if(nivel==NivelDificultad.DIFICIL){
			return "fichero-l3.txt";
		}
		return null;
	}
}
		

		


