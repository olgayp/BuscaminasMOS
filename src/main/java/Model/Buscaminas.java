package Model;
/**
 *  Builder de la aplicacion Buscaminas
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GraphicsConfiguration;
import java.util.*;
import java.net.*;
import java.applet.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

import Model.User;
import Grafica.Tablero;
import Grafica.Logueo;
import Grafica.GrafCasilla;
import Grafica.Top10;

import java.util.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.BoxLayout;


import javax.swing.border.SoftBevelBorder;

import Model.Casilla;
import Model.PosiMina;

import javax.swing.border.BevelBorder;

/**
 * 
 * @author Susana /Olga/Miguel
 *
 */

public class Buscaminas implements Observer{
	/**
	 * Builder Buscaminas
	 */
	private static Buscaminas mBuscaminas = new Buscaminas();
	
	private Tablero tablero;
	private User user;
	private static Logueo l;
	private Top10 top10;
	
	public Buscaminas(){
		
	}
	
	/**
	 * Devuelve la instancia unica de Buscaminas
	 * @return Buscaminas
	 */
	public static Buscaminas getBuscaminas(){
		if (mBuscaminas == null){
			mBuscaminas = new Buscaminas();
		}
		return mBuscaminas;
	}
	
	/**
	 * Inicio de juego con la pantalla de eleccion de nivel
	 */
	public void iniciarJuego(){
		top10 = new Top10();
		l = new Logueo();
		l.getBtnAceptar().addActionListener(new ControlEventos());
	}
	
	/**
	 * creador del tablero de juego
	 * @param tipo de tablero = nivel de juego (1 Facil, 2 Medio  o 3 Dificil)
	 */
	public void createTablero(int tipo){
		if (tipo == 1){
			tablero = new Tablero(NivelDificultad.FACIL,user.getNombre());  
		}
		if (tipo == 2){
			tablero = new Tablero(NivelDificultad.MEDIO,user.getNombre());
		}
		if (tipo == 3){
			tablero = new Tablero(NivelDificultad.DIFICIL,user.getNombre());
		}
		tablero.setVisible(true);
		tablero.getFin().addActionListener(new ControlEventos());
		tablero.getNuevo().addActionListener(new ControlEventos());
		tablero.getReinicio().addActionListener(new ControlEventos());
		tablero.getFin().setEnabled(false);
		for (int i = 0; i < tablero.getPCasillas().getComponentCount(); i++){
			GrafCasilla grafCas = (GrafCasilla)tablero.getPCasillas().getComponent(i);
		    grafCas.addActionListener(new ControlEventos());
		    grafCas.addMouseListener(new ControlEventos());
		    grafCas.getCasilla().addObserver(this);
		}
	}
	
	/**
	 * Creador de usuario de juego
	 */
	public void createUsuario(){
	      String usuario = "";
		  if (l.getTextField().getText().equals(""))
			  usuario = "Desconocido";
		  else
			  usuario = l.getTextField().getText();
		  int indice = l.getComboBox().getSelectedIndex();
		  String nameNivel=  NivelDificultad.values()[indice].name();
		  user = new User(usuario,nameNivel,99999);
    }
	
	/**
	 * getter de la pantalla de logueo
	 * @return Logueo
	 */
	public Logueo getLogueo(){
		return l;
	}
	
	/**
	 * getter del tablero de juego
	 * @return tablero2
	 */
	public Tablero getTablero(){
	     return tablero;
    }
	
	/**
	 * getter del ranking Top10
	 * @return top10
	 */
	public Top10 getTop10(){
	     return top10;
    }
	
	/**
	 * getter del usuario que esta jugando
	 * @return
	 */
	public User getUser(){
		return user;
	}
	
	
	/**
	 *************************************
	 *    parte OBSERVER - update        *
	 *                                   *
	 *************************************
	 */
	/**
	 *  update - observa el estado de cada casilla
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg.equals("iniciar")){
			if (tablero.getContador()==0){
			    tablero.gestionDeTiempo();
			    System.out.println("Juego iniciado...");
		    }
		}
		if (arg.equals("marcarMinaDescubierta")){
			tablero.restarMinas();	
			tablero.setCasillasActivas(tablero.getCasActivas() - 1);
			tablero.sumarMinaDescubierta();
			if (tablero.getMinasDescubiertas() == tablero.getNivel().getNumMinas() ||
				tablero.getCasActivas() == Integer.parseInt(tablero.getMinas().getText())){
				victoria();
			}
		}
		if (arg.equals("desmarcarMinaOculta")){
			tablero.sumarMinas();
        	tablero.setCasillasActivas(tablero.getCasActivas() + 1);
        	tablero.restarMinaDescubierta();
        	if (tablero.getCasActivas() == Integer.parseInt(tablero.getMinas().getText())){
        		victoria();
			}	
		}
		
		if (arg.equals("pararPierdo")){
			derrota();
		}
		if (arg.equals("marcar")){
			tablero.restarMinas();	
			tablero.setCasillasActivas(tablero.getCasActivas() - 1);
			if (tablero.getCasActivas() == Integer.parseInt(tablero.getMinas().getText())){
				victoria();
			}
		}
        if (arg.equals("desmarcar")){
        	tablero.sumarMinas();
        	tablero.setCasillasActivas(tablero.getCasActivas() + 1);
        	if (tablero.getCasActivas() == Integer.parseInt(tablero.getMinas().getText())){
        		victoria();
			}
			
		}
        if (arg.equals("destapar")){
        	tablero.setCasillasActivas(tablero.getCasActivas() - 1);
			if (tablero.getCasActivas() == Integer.parseInt(tablero.getMinas().getText())){
				victoria();
			}
	    }
	}
	/**
	 * El jugador ha ganado la partida 
	 */
	public void victoria(){
		tablero.getTimer().cancel(); 
	    String sreinic = getClass().getClassLoader().getResource("gano.png").toString();
	    tablero.getReinicio().setIcon(new ImageIcon(sreinic.substring(6)));
	    tablero.getFin().setEnabled(true);
	    tablero.desactivarRestoCasillas();
	    tablero.setEstadoJuego(2);
	    URL url=getClass().getClassLoader().getResource("Victory.wav");
	    AudioClip clip=Applet.newAudioClip(url);
	    clip.play();
	    user.nuevoRecord(tablero.getContador());
	    JOptionPane.showMessageDialog(tablero.getPCasillas(),"¡GANASTE...!" , "Ganador",
	    		JFrame.NORMAL, tablero.getIconoBusc());   
	}
	
	/**
	 * El jugador ha perdido la partida
	 */
	public void derrota(){
		tablero.getTimer().cancel(); 
		tablero.getFin().setEnabled(true);
		String sreinic = getClass().getClassLoader().getResource("perdio.png").toString();
		URL url=getClass().getClassLoader().getResource("minaSound.wav");
		AudioClip clip=Applet.newAudioClip(url);
		clip.play();
		tablero.getReinicio().setIcon(new ImageIcon(sreinic.substring(6))); 
		tablero.setEstadoJuego(2);
		JOptionPane.showMessageDialog(tablero.getPCasillas(),"¡PERDISTE...!" , "Perdedor",
		    		JFrame.NORMAL, tablero.getIconoBusc());
	}


	/**
	 * Main principal de inicio de juego
	 * @param args
	 */
	public static void main(String[] args){	
		Buscaminas.getBuscaminas().iniciarJuego();
	}
}
