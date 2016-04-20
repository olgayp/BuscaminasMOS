package Grafica;

import  Model.Casilla;
import  Model.Buscaminas;

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

		/**
		 * todo el control de eventos se ha pasado a la clase ControlEventos
		 */
		