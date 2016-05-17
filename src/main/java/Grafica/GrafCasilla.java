package Grafica;

import Model.Buscaminas;
import  Model.Casilla;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class GrafCasilla extends JButton{
		/**
	 * 
	 */
	private static final long serialVersionUID = -3520758611746529990L;
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
			//
			//Evento action listener al pulsar una casilla
			//
			addActionListener(new ActionListener(){  // al pulsar boton-casilla se inicia evento
			
			     public void actionPerformed(ActionEvent e) {
			    	 String sv = "";
			         sv = Integer.toString(cas.getValor());
			         setFocusable(false); 
			         Container cter = ((GrafCasilla)e.getSource()).getParent();
			         //casilla pulsada--> el juego comienza, se da la orden iniciar al observador
			         cas.iniciar();
			         if (cas.getEstado() == 0){ //si no marcada se acepta el evento
				         if (cas.getValor() == -9){  // Casilla mina, el juego ha terminado
				        	 setOpaque(false);
				        	 setIcon(new ImageIcon(cas.getImagen()));  //imagen de mina
				        	 setBackground(Color.RED);
				        	 setEnabled(false);
				        	 String smr = getClass().getClassLoader().getResource("99R.png").toString();
				        	 ((GrafCasilla)e.getSource()).setEtiqueta(smr.substring(6));
				        	 int pm1 = cter.getComponentZOrder((GrafCasilla)e.getSource());
			        		 cter.add(((GrafCasilla)e.getSource()).getEtiqueta(), pm1);
			        		 cter.remove(pm1+1);
			        		 
			        		 //
			        		 // El juego ha terminado (se ha sacado una mina) --> descubrir resto de casillas
				          	 //
				        	 Component listCas [] = cter.getComponents();
				        	 for (int i = 0; i < listCas.length; i++){
				        		 if (!(listCas[i] instanceof JLabel)){
				        			 if (((GrafCasilla)listCas[i]).getCasilla().getValor() == -9){
				        				 if (((GrafCasilla)listCas[i]).getCasilla().getEstado() == 2){
				        			    	 String se = getClass().getClassLoader().getResource("99X.png").toString();
				        			    	 ((GrafCasilla)listCas[i]).setEtiqueta(se.substring(6));
				        			     }
				        			    //((GrafCasilla)listCas[i]).getCasilla().setEstado(1);//marcar a destapada ESTADO=1
				        			 
				        			 }
					        		 else{
					        			 //cambiar a etiqueta de tapada
					        			 if (((GrafCasilla)listCas[i]).getCasilla().getEstado() == 2){
					        				 String se = getClass().getClassLoader().getResource("B11.png").toString();
						        			 ((GrafCasilla)listCas[i]).setEtiqueta(se.substring(6));
					        			 }
					        			 else{
					        			    String se = getClass().getClassLoader().getResource("0B.png").toString();
					        			    ((GrafCasilla)listCas[i]).setEtiqueta(se.substring(6));
					        			 }
					        		 }
				        			//*************** Sustituir botones por etiquetas al descubrir casilla
				        			 int p1 = cter.getComponentZOrder((GrafCasilla)listCas[i]);
					        		 cter.add(((GrafCasilla)listCas[i]).getEtiqueta(), p1);
					        		 cter.remove(p1+1);
					        		//*************** Sustituir botones por etiquetas al descubrir casilla
				        	     }
				        	 }
				        	 //dar orden de parar el juego al modelo Casilla
			
	
				        	 cas.parar(-9);
						    	
				         }
				         else{  // casilla no es mina, por tanto sacar su valor
				        	 setOpaque(false);
				        	 if (sv.equals("0")){ //si es cero es una casilla vacia - SPACES
				        		 sv = "";
				        		 setText(sv);
				        		 setEnabled(false);
				        		 
				        		 //*************** Sustituir botones por etiquetas al descubrir casilla
				        		    //filas del JPanel que contiene la casilla
				        		 int fil = ((GridLayout)cter.getLayout()).getRows();  
				        		   //columnas del JPanel que contiene la casilla
				        		 int col = ((GridLayout)cter.getLayout()).getColumns();
				        		 int p1 = cter.getComponentZOrder((GrafCasilla)e.getSource());
				        		 int x = cas.getI();
				        		 int y = cas.getJ();
				        		 cter.add(etqVal, p1);
				        		 cter.remove(p1+1);
				        		 cas.setEstado(1);
				        		 //*************** Sustituir botones por etiquetas al descubrir casilla				        		 
				        		 //pulsar casillas vacias vecinas con doClick() recursivamente
				        		 pulsarCasillasVacias(fil,col,p1,cter,x,y);
				        		 
				        	 }
				        	 else{ // si no es cero descubrir su valor
				        		 sv = "";
				        		 setText(sv);
				        		 setEnabled(false);
				        		 setVisible(false);
				        		
				        		 //*************** Sustituir botones por etiquetas al descubrir casilla
				        		 int p1 = cter.getComponentZOrder((GrafCasilla)e.getSource());
				        		 cter.add(etqVal, p1);
				        		 cter.remove(p1+1);
				        		//*************** Sustituir botones por etiquetas al descubrir casilla
				        		 cas.setEstado(1);   //marcar casilla como destapada ESTADO=1

				        	 }
				        	
				         }
			         }
			      }	
			});
			
			//
			//Evento Mouse listener para controlar el raton al pasar por un boton-casilla
			//
			addMouseListener(new MouseListener(){
				public void mousePressed(MouseEvent me) {  //si boton derecho marcar con bandera
					if (isEnabled()){
						if ((me.getModifiers() & 
						     InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
							
						   if (cas.getEstado() != 2) { 
						       if (Buscaminas.getConBuildTablero().getMinasMarcadas() <
						    	   Buscaminas.getConBuildTablero().getNivel().getNumMinas())
						          { 
						           cas.setIante((ImageIcon)getIcon());
						           String s = getClass().getClassLoader().
						    		     getResource("B11.png").toString();
				     		       setIcon(new ImageIcon(s.substring(6)));
						           cas.setEstado(2);  // ESTADO = 2 (Marcada)
						       }
					       }
					       else{  //si marcada y boton derecho --> desmarcar y dejar icono inicial
						       setIcon((ImageIcon)cas.getIcante());
						       cas.setEstado(0);  //casilla oculta
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
			});
			
		}
		//
		// Al encontrar una casilla vacia se pulsan todas sus vecinas recursivamente con 
		// doClick() hasta encontrar una casilla vecina que contenga una mina.
		// @param fil - filas del contenedor de la casilla pulsada
		//        col - columnas del contenedor de la casilla pulsada
		//          p - posicion absoluta de la casilla pulsada
		//    ctainer - contenerdor (JPanel) de la casillal pulsada
		//          i - posicion relativa x de la casilla pulsada dentro de la matriz
		//          j - posicion relativa j de la casilla pulsada dentro de la matriz 
		//
		public void pulsarCasillasVacias(int fil, int col, int p, Container ctainer,int i, int j){
		
  		   // casilla siguiente 
  		   if ( p+1<ctainer.getComponentCount() &&
  				   !(ctainer.getComponent(p+1) instanceof JLabel))
  				   
  			   if (((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getEstado() == 0 &&
  				   ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getValor() != -9 &&
                     ((GrafCasilla)(ctainer.getComponent(p+1))).isEnabled() &&
                     ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getJ() < col &&
                     (i == ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getI()) && 
                     (j+1 == ((GrafCasilla)(ctainer.getComponent(p+1))).getCasilla().getJ()                                       )
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

		//
		// Devolver el valor de la casilla
		// @return valor de la casilla
		//
		public Casilla getCasilla(){
			return cas;
		}
		
		//
		// Devolver el valor de la etiqueta
		// @return etiqueta de la casilla
		//
		public JLabel getEtiqueta(){
			return etqVal;
		}
		
		//
		// Cambiar el valor de la etiqueta
		// @param nueva etiqueta para la casilla
		//
		public void setEtiqueta(String newEtiq){
			JLabel nl = new JLabel();
			nl.setIcon(new ImageIcon(newEtiq));
			etqVal = nl;
		}

}

