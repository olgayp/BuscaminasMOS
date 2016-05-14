package Grafica;

import  Model.Casilla;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class GrafCasilla extends JButton{
		private Casilla cas;
		public static final int TAMX=22;  //Anchura de la casilla
		public static final int TAMY=22;  //Altura de la casilla
		private JLabel etqVal; //Etiqueta con su valor
		
		//
		// Constructor
		//
		public GrafCasilla (Casilla c, JLabel etq){
			cas = c;
			etqVal = etq;
			setVisible(true);
			setEnabled(true);
			setPreferredSize(new Dimension(TAMX,TAMY));
		}
		/**
		 * Devuelve la casilla
		 * @return cas
		 */
		public Casilla getCasilla(){
			return cas;
		}
		
		/**
		 * Devuelve la etiqueta valor de la casilla (1-8 numerica, o etiq mina si valor -9)
		 * @return etqVal
		 */
		public JLabel getEtiqueta(){
			return etqVal;
		}
		
		/**
		 * Cambiar el valor de la etiqueta con el nuevo valor newEtiq
		 * @param newEtiq
		 */
		public void setEtiqueta(String newEtiq){
			JLabel nl = new JLabel();
			nl.setIcon(new ImageIcon(newEtiq));
			etqVal = nl;
		}
		/**
		 * Destapar Casilla
		 */
		public void destaparCasilla(){
		     String sv = "";
		     int posCas = getParent().getComponentZOrder(this);
		     if (this.getCasilla().getEstado() == 2){
		    	 String se = getClass().getClassLoader().getResource("99X.png").toString();
		      	 this.setEtiqueta(se.substring(6));
		     }
			 sv = Integer.toString(cas.getValor());
			 setText(sv);
        	 setEnabled(false);
        	 setVisible(false);
        	 getParent().add(this.getEtiqueta(), posCas);
		     getParent().remove(posCas+1);
		    
		}
		/**
		 * Destapar Casilla marcada con mina
		 */
		public void destaparCasillaMina(){
			String se = getClass().getClassLoader().getResource("99R.png").toString();
      		this.setEtiqueta(se.substring(6));
			destaparCasilla();
		}
		/**
		 * Tapar Casilla 
		 */
		public void taparCasilla(){
			String se = null;
	        if ( this.getCasilla().getEstado() == 2){
	        	se = getClass().getClassLoader().getResource("B11.png").toString();
	        }		
	        else{
	        	se = getClass().getClassLoader().getResource("0B.png").toString();
	        }
	        this.setEtiqueta(se.substring(6));
	        int posCas = getParent().getComponentZOrder(this);
	        getParent().add(this.getEtiqueta(), posCas);
		    getParent().remove(posCas+1);
	    }
        
		/**
		 * Marcar Casilla
		 */
		public void marcarCasilla(){
			 cas.iniciar();
			 int min = Integer.parseInt(Buscaminas.getBuscaminas().getTablero().getMinas().getText());
			 if (min > 0){
		         cas.setIante((ImageIcon)this.getIcon());
		         String s = getClass().getClassLoader().getResource("B11.png").toString();
   		         this.setIcon(new ImageIcon(s.substring(6)));
   		         cas.marcar();   // estado = 2 (marcada con bandera)
			 }
		}
		/**
		 * Desmarcar Casilla()
		 */
		public void desmarcarCasilla(){
			this.setIcon((ImageIcon)cas.getIcante());
		    cas.desmarcar(); //casilla oculta
		    int p = this.getParent().getComponentZOrder(this);
		    Buscaminas.getBuscaminas().getTablero().getListaMinas().setNoPista(p);
		}
		
}

		