package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import Model.Buscaminas;
import Model.PosiMina;


public class ControlEventos implements ActionListener, MouseListener{

	public ControlEventos(){
		
	}
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() instanceof JMenuItem){
				if (((JMenuItem)e.getSource()).getName().equals("juegoNuevo")){
					Buscaminas.getConBuildTablero().cerrarTablero();
					Buscaminas.getConBuildTablero().deleteObservers();
					Tablero tablero = new Tablero();
					tablero.setVisible(false);
					Logueo log = new Logueo();
					log.setVisible(true);
				}
			}
			
			
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoReiniciar")){
		   	Buscaminas.getConBuildTablero().cerrarTablero();
			Tablero tablero=new Tablero();
			Buscaminas.getConBuildTablero().deleteObservers();
			Buscaminas.getConBuildTablero().guardarDatos(
					Buscaminas.getConBuildTablero().getNivel(),
					Buscaminas.getConBuildTablero().getUser().getNombre(),
					tablero);
			tablero.inicio();
			tablero.setVisible(true);
			}		
			   
	      }	
	
		
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoDetener")){
				 if ( Buscaminas.getConBuildTablero().getTablero().getContador() != 0){
					 System.out.println("Contador "+ Buscaminas.getConBuildTablero().getTablero().getContador() );
					 if (Buscaminas.getConBuildTablero().getTablero().getEstadoJuego()!=2){
				         Buscaminas.getConBuildTablero().getTablero().pararTimer();
			            // Buscaminas.getBuscaminas().getTablero().getTimer().cancel();
			             Buscaminas.getConBuildTablero().getTablero().setEstadoJuego(1);
				         Buscaminas.getConBuildTablero().getTablero().activarDesactivarCasillas(false);
					 }
				 }
			}
		}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoContinuar")){
				 if ( Buscaminas.getConBuildTablero().getTablero().getContador() != 0){
					 if (Buscaminas.getConBuildTablero().getTablero().getEstadoJuego() == 1){
					     Buscaminas.getConBuildTablero().getTablero().gestionDeTiempo();
					     Buscaminas.getConBuildTablero().getTablero().setEstadoJuego(0);
					     Buscaminas.getConBuildTablero().getTablero().activarDesactivarCasillas(true);
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
    			if (Buscaminas.getConBuildTablero().getTablero().getEstadoJuego() != 1){
    			PosiMina [] posMin = Buscaminas.getConBuildTablero().getPm();
    			boolean enc = false;
    			for (int i = 0; i < posMin.length && !enc; i++) {
    				if (!posMin[i].getPista()){
    					posMin[i].setPista(true);
    					enc = true;
    					int n = posMin[i].getFbomb()*Buscaminas.getConBuildTablero().getNivel().getNumColumnas()+
    							posMin[i].getCbomb();
    					GrafCasilla gc =(GrafCasilla) (Buscaminas.getConBuildTablero().getTablero()).
    					getPCasillas().getComponent(n);
				        String s = getClass().getClassLoader().
				    		     getResource("B11.png").toString();
		     		     gc.setIcon(new ImageIcon(s.substring(6)));
		     		     gc.getCasilla().iniciar();
				         gc.getCasilla().setEstado(2);  // ESTADO = 2 (Marcada)
    					
    				}
    				
    			}
    			}
    		}
    	}
		
		if (e.getSource() instanceof JMenuItem){
			if (((JMenuItem)e.getSource()).getName().equals("juegoSalir")){
				System.exit(0);
				
			}
		}
		
		if (e.getSource() instanceof JButton){
			if (((JButton)e.getSource()).getName().equals("nuevo")){
				System.exit(0);
			}
		}
		
		if (e.getSource() instanceof JButton){
			if (((JButton)e.getSource()).getName().equals("fin")){
				Top10.getTop10().inicializarTabla(true);
				Top10.getTop10().leerFichero();
				Top10.getTop10().initialize();
				Top10.getTop10().setVisible(true);
			}
		}
					
	}
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

