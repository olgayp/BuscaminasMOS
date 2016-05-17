package Grafica;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Model.Buscaminas;
import Model.NivelDificultad;

public class GrafReinicio extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4067517291921991432L;
	public static final int TAMX=35;  //Anchura del boton
	public static final int TAMY=35;  //Altura del boton
	
	
	public GrafReinicio(final NivelDificultad nivel, final String nombreJ){
		setVisible(true);
		setEnabled(true);
		setPreferredSize(new Dimension(TAMX,TAMY));
		String imagenR=getClass().getClassLoader().getResource("nueva.png").toString();
		this.setIcon(new ImageIcon(imagenR.substring(6)));
		
		addActionListener(new ActionListener(){  // al pulsar boton-reinicio se inicia evento
		   
			public void actionPerformed(ActionEvent e) {
				Buscaminas.getConBuildTablero().cerrarTablero();
				Tablero tablero=new Tablero();
				Buscaminas.getConBuildTablero().deleteObservers();
				Buscaminas.getConBuildTablero().guardarDatos(nivel, nombreJ, tablero);
				tablero.inicio();
				setVisible(false);   
				
			}
		});
}
	public void cambiarImagen(String imagen){
		String s = getClass().getClassLoader().getResource(imagen).toString();
		this.setIcon(new ImageIcon(s.substring(6)));
	}
}
