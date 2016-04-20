package Model;
import java.util.*;
/**
 * 
 * @author Susana Abascal
 *
 */
public class ListaMinas extends ArrayList<PosiMina> {
	/**
	 * clase que contiene una lista de minas del tablero de juego
	 */
	
	private NivelDificultad nivel;
	
	public ListaMinas(NivelDificultad nivel){
		new ArrayList();
		this.nivel= nivel;
	}
	
	/**
	 * El usuario pedira una pista y devolvera el numero de casilla donde hay una mina
	 * @return numero de casilla con mina
	 */
	public int pedirPista(){
		PosiMina pos = new PosiMina(0,0);
		int c = 0;
		boolean encontrado = false;
		for (int i = 0; i < this.size() && !encontrado; i++){
		    if (!this.get(i).getPista()){
		    	pos = this.get(i);
		    	this.get(i).setPista(true);
		    	encontrado = true;
		    }
		}
		c = pos.getFbomb()*nivel.getNumColumnas()+pos.getCbomb();
		return c;
	}
	/**
	 * poner como no dada en pista
	 */
	public void setNoPista(int p){
		int f = 0;
		int c = 0;
		f = p/nivel.getNumColumnas();
		c = p%nivel.getNumColumnas();
		for (int i = 0; i < this.size(); i++){
			if (this.get(i).getFbomb() == f &&
				this.get(i).getCbomb() == c)
				this.get(i).setPista(false);
		}
	}
}
