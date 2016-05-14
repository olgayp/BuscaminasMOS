package Grafica;
/**
 * Clase que controla todos los eventos sobre los botones de la aplicacion Buscaminas
 */
import Model.Casilla;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.BoxLayout;

import java.awt.*;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.*;

import java.awt.event.*;

/**
 * 
 * @author Susana Abascal
 *
 */


public class ControlEventos implements ActionListener, MouseListener{

	public ControlEventos(){
		
	}
	
	/**
	 * Eventos de pulsacion de Boton Reinicio, Boton aceptar-datos Entrada, Botones de Casillas
	 * y opciones de Menu
	 */
	public void actionPerformed(ActionEvent e){
	
		JButton reinic = null;
		JButton AcepLog = null;
		JButton fin = null;
		JButton nuevo = null;
		JButton acepTop10 = null;
		try{
		    reinic = Buscaminas.getBuscaminas().getTablero().getReinicio();
		}
		catch(Exception ex1){
		}
		try{
		    AcepLog = Buscaminas.getBuscaminas().getLogueo().getBtnAceptar();
		}
		catch(Exception ex1){
		}
		try{
		    fin = Buscaminas.getBuscaminas().getTablero().getFin();
		    nuevo = Buscaminas.getBuscaminas().getTablero().getNuevo();
		}
		catch(Exception ex1){
		}
		try{
		    acepTop10 = Buscaminas.getBuscaminas().getTop10().getBtnAceptar();
		}
		catch(Exception ex1){
		}
		
		if (e.getSource().equals(reinic)){
	         int n = Buscaminas.getBuscaminas().getLogueo().getComboBox().getSelectedIndex()+1;
	    	 Buscaminas.getBuscaminas().getTablero().dispose();
	    	 if ( Buscaminas.getBuscaminas().getTablero().getContador() != 0)
	    	     Buscaminas.getBuscaminas().getTablero().getTimer().cancel();
		     Buscaminas.getBuscaminas().createTablero(n);  
		}
		
		if (e.getSource().equals(AcepLog)){
				Buscaminas.getBuscaminas().getLogueo().dispose();
				Buscaminas.getBuscaminas().createUsuario();
				Buscaminas.getBuscaminas().createTablero
					(Buscaminas.getBuscaminas().getLogueo().getComboBox().getSelectedIndex()+1);
		}
		
		if (e.getSource().equals(fin)){
			Buscaminas.getBuscaminas().getTop10().visualiazarTop10();
			Buscaminas.getBuscaminas().getTablero().getFin().setEnabled(false);
			Buscaminas.getBuscaminas().getTablero().getReinicio().setEnabled(false);
			Buscaminas.getBuscaminas().getTablero().getJuegoReiniciar().setEnabled(false);
	    }
		
		if (e.getSource().equals(nuevo)){
			Buscaminas.getBuscaminas().getTablero().dispose();
	    	Buscaminas.getBuscaminas().iniciarJuego();
	    }
		
		if (e.getSource().equals(acepTop10)){
			Buscaminas.getBuscaminas().getTop10().dispose();
	    }
		
		if (e.getSource() instanceof GrafCasilla){
			GrafCasilla grafCas = (GrafCasilla)e.getSource();
			Casilla cas = ((GrafCasilla)e.getSource()).getCasilla();
			String sv = "";
			sv = Integer.toString(cas.getValor());
			Container cter = Buscaminas.getBuscaminas().getTablero().getPCasillas();
			int posCas = cter.getComponentZOrder(grafCas);
			//casilla pulsada--> el juego comienza, se da la orden iniciar al observador
			cas.iniciar();
			if (cas.getEstado() == 0){ //si no marcada se acepta el evento
			   if (cas.getValor() == -9){  // Casilla mina, el juego ha terminado
				   grafCas.destaparCasillaMina();
			       //
			       // El juego ha terminado (se ha sacado una mina) --> descubrir resto de casillas
				   //
				   Component listCas [] = cter.getComponents();
				   for (int i = 0; i < listCas.length; i++){
				       if (!(listCas[i] instanceof JLabel)){
				          if (((GrafCasilla)listCas[i]).getCasilla().getValor() == -9){
				        	  ((GrafCasilla)listCas[i]).destaparCasilla();
				          }
					      else{
					    	((GrafCasilla)listCas[i]).taparCasilla();
					      }
				       }
				     }
				     //dar orden de parar el juego al modelo Casilla
				     cas.parar(-9);	    	
			  }
			  else{  // casilla no es mina, por tanto sacar su valor   	
				 grafCas.destaparCasilla();
				 grafCas.getCasilla().setEstado(1);
				 if (sv.equals("0")){  //si es cero es una casilla vacia - SPACES
				    int fil = ((GridLayout)cter.getLayout()).getRows();  
				    int col = ((GridLayout)cter.getLayout()).getColumns();
				    int x = cas.getI();
				    int y = cas.getJ();
				    pulsarCasillasVacias(fil,col,posCas,cter,x,y);	
			     }
			  }
		   } 
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoNuevo")){
		    	 Buscaminas.getBuscaminas().getTablero().dispose();
		    	 Buscaminas.getBuscaminas().iniciarJuego();
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoReiniciar")){
			     int n = Buscaminas.getBuscaminas().getLogueo().getComboBox().getSelectedIndex()+1;
		    	 Buscaminas.getBuscaminas().getTablero().dispose();
		    	 if ( Buscaminas.getBuscaminas().getTablero().getContador() != 0)
		    	     Buscaminas.getBuscaminas().getTablero().getTimer().cancel();
			     Buscaminas.getBuscaminas().createTablero(n);  
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoDetener")){
				 if ( Buscaminas.getBuscaminas().getTablero().getContador() != 0){
					 if (Buscaminas.getBuscaminas().getTablero().getEstadoJuego()!=2){
				        Buscaminas.getBuscaminas().getTablero().getTimerTask().cancel();
				        Buscaminas.getBuscaminas().getTablero().getTimer().cancel();
				        Buscaminas.getBuscaminas().getTablero().setEstadoJuego(1);
				        Buscaminas.getBuscaminas().getTablero().activarDesactivarCasillas(false);
					 }
				 }
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoContinuar")){
				 if ( Buscaminas.getBuscaminas().getTablero().getContador() != 0){
					 if (Buscaminas.getBuscaminas().getTablero().getEstadoJuego() == 1){
					     Buscaminas.getBuscaminas().getTablero().gestionDeTiempo();
					     Buscaminas.getBuscaminas().getTablero().setEstadoJuego(0);
					     Buscaminas.getBuscaminas().getTablero().activarDesactivarCasillas(true);
					 }
				 }
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("acercaDe")){
				 new Ayuda();
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoPista")){
				Tablero t = Buscaminas.getBuscaminas().getTablero();
				if (t.getEstadoJuego()==0){
				   int ps = t.getListaMinas().pedirPista();
				   ((GrafCasilla)t.getPCasillas().getComponent(ps)).marcarCasilla();
				}
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoSalir")){
				System.exit(0);
				
			}
		}
	}
		
	/**
	 * Eventos de control de boton derecho sobre las Casillas (Marcar/Desmarcar)
	 */
	public void mousePressed(MouseEvent me) {  //si boton derecho marcar con bandera
		GrafCasilla grafCas = (GrafCasilla)me.getSource();
		Casilla cas = ((GrafCasilla)me.getSource()).getCasilla();
		if (((GrafCasilla)me.getSource()).isEnabled()){
			if ((me.getModifiers() & 
			     InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			   if (cas.getEstado() != 2){  //si boton derecho y no marcada
				   grafCas.marcarCasilla();
		       }
		       else{  //si marcada y boton derecho --> desmarcar y dejar icono inicial
		    	   grafCas.desmarcarCasilla();
			   }
		 	}
		 }
	}
	public void mouseReleased(MouseEvent me) {
	}
	public void mouseExited(MouseEvent me) {
	}
	public void mouseEntered(MouseEvent me) {
	}
	public void mouseClicked(MouseEvent me) {
	}
	
	/**
	 * Pulsar Casillas con evento con doClick()
	 * Al encontrar una casilla vacia se pulsan todas sus vecinas recursivamente con 
     * doClick() hasta encontrar una casilla vecina que contenga una mina.
	 * @param fil - filas del contenedor de la casilla pulsada
	 *        col - columnas del contenedor de la casilla pulsada
	 *          p - posicion absoluta de la casilla pulsada
     *    ctainer - contenerdor (JPanel) de la casillal pulsada
	 *          i - posicion relativa x de la casilla pulsada dentro de la matriz
     *          j - posicion relativa j de la casilla pulsada dentro de la matriz 
	 *
	 */
	public void pulsarCasillasVacias(int fil, int col, int p, Container ctainer,int i, int j){
		 
		// casilla siguiente 
		   if ( p+1<ctainer.getComponentCount() &&
				   !(ctainer.getComponent(p+1) instanceof JLabel))
				   
			   if (((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p+1))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getJ() < col &&
                  (i == ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getI()) && 
                  (j+1 == ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getJ())
                  )
			         ((GrafCasilla)(ctainer.getComponent(p+1))).doClick();
		   
		   //casilla anterior
		   if ( p-1 >=0 && !(ctainer.getComponent(p-1) instanceof JLabel))
				 if (((GrafCasilla)(ctainer.getComponent(p-1))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p-1))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p-1))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p-1))).getCasilla().getJ() >= 0  &&
                  (i == ((GrafCasilla)(ctainer.getComponent(p-1))).getCasilla().getI()) && 
                  (j-1 == ((GrafCasilla)(ctainer.getComponent(p-1))).getCasilla().getJ()
                  ) 
                 
                  )
			   ((GrafCasilla)(ctainer.getComponent(p-1))).doClick();
		   
		   //casilla arriba
		   if ( p-col >= 0 && !(ctainer.getComponent(p-col) instanceof JLabel))
				 if (((GrafCasilla)(ctainer.getComponent(p-col))).getCasilla().getEstado() == 0 &&
                  ((GrafCasilla)(ctainer.getComponent(p-col))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p-col))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p-col))).getCasilla().getI() >= 0  &&
                  (i-1 == ((GrafCasilla)(ctainer.getComponent(p-col))).getCasilla().getI()) && 
                  (j == ((GrafCasilla)(ctainer.getComponent(p-col))).getCasilla().getJ()
                   )
                  )
			   ((GrafCasilla)(ctainer.getComponent(p-col))).doClick();
		
		   //casilla abajo
		   if (p+col<ctainer.getComponentCount() && !(ctainer.getComponent(p+col) instanceof JLabel))
				if ( ((GrafCasilla)(ctainer.getComponent(p+col))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p+col))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p+col))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p+col))).getCasilla().getI() < fil  &&
                  (i+1 == ((GrafCasilla)(ctainer.getComponent(p+col))).getCasilla().getI()) && 
                  (j == ((GrafCasilla)(ctainer.getComponent(p+col))).getCasilla().getJ()
                 )
                  )
			   ((GrafCasilla)(ctainer.getComponent(p+col))).doClick();
		   
		   //casilla superior izquierda
		   if (p-col-1 >= 0 && !(ctainer.getComponent(p-col-1) instanceof JLabel))
				 if (((GrafCasilla)(ctainer.getComponent(p-col-1))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p-col-1))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p-col-1))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p-col-1))).getCasilla().getI() >= 0 &&
                  ((GrafCasilla)(ctainer.getComponent(p-col-1))).getCasilla().getJ() >= 0 &&
                  (i-1 == ((GrafCasilla)(ctainer.getComponent(p-col-1))).getCasilla().getI()) && 
                  (j-1 == ((GrafCasilla)(ctainer.getComponent(p-col-1))).getCasilla().getJ()
                 )
                  )
			   ((GrafCasilla)(ctainer.getComponent(p-col-1))).doClick();
		   
		   //casilla superior derecha
		   if (p-col+1 >=0 && !(ctainer.getComponent(p-col+1) instanceof JLabel))
				 if ( ((GrafCasilla)(ctainer.getComponent(p-col+1))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p-col+1))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p-col+1))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p-col+1))).getCasilla().getI() >= 0 &&
                  ((GrafCasilla)(ctainer.getComponent(p-col+1))).getCasilla().getJ() < col &&
                  (i-1 == ((GrafCasilla)(ctainer.getComponent(p-col+1))).getCasilla().getI()) && 
                  (j+1 == ((GrafCasilla)(ctainer.getComponent(p-col+1))).getCasilla().getJ()
                 )
                  )
			   ((GrafCasilla)(ctainer.getComponent(p-col+1))).doClick();
		   
		   //casilla inferior izquierda
		   if (p+col-1 < ctainer.getComponentCount() && !(ctainer.getComponent(p+col-1) instanceof JLabel))
				 if (((GrafCasilla)(ctainer.getComponent(p+col-1))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p+col-1))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p+col-1))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p+col-1))).getCasilla().getI() < fil &&
                  ((GrafCasilla)(ctainer.getComponent(p+col-1))).getCasilla().getJ() >= 0 &&
                  (i+1 == ((GrafCasilla)(ctainer.getComponent(p+col-1))).getCasilla().getI()) && 
                  (j-1 == ((GrafCasilla)(ctainer.getComponent(p+col-1))).getCasilla().getJ()
                 )
                  )
			   ((GrafCasilla)(ctainer.getComponent(p+col-1))).doClick();
		   
		   //casilla inferior derecha
		   if (p+col+1 < ctainer.getComponentCount() && !(ctainer.getComponent(p+col+1) instanceof JLabel))
				 if (((GrafCasilla)(ctainer.getComponent(p+col+1))).getCasilla().getEstado() == 0 &&
				   ((GrafCasilla)(ctainer.getComponent(p+col+1))).getCasilla().getValor() != -9 &&
                  ((GrafCasilla)(ctainer.getComponent(p+col+1))).isEnabled() &&
                  ((GrafCasilla)(ctainer.getComponent(p+col+1))).getCasilla().getI() < fil &&
                  ((GrafCasilla)(ctainer.getComponent(p+col+1))).getCasilla().getJ() < col &&
                  (i+1 == ((GrafCasilla)(ctainer.getComponent(p+col+1))).getCasilla().getI()) && 
                  (j+1 == ((GrafCasilla)(ctainer.getComponent(p+col+1))).getCasilla().getJ()
                 )
                  )
			   ((GrafCasilla)(ctainer.getComponent(p+col+1))).doClick();
		}

}
