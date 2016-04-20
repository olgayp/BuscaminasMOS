package Model;

public class PosiMina {   //guardar mina por si el ramdon da la misma posicion y hay que buscar otra poscion
	
     private int fBomb;
	 private int cBomb;
	 private boolean pista;
	 public PosiMina(int f, int c){
	    	fBomb = f;
	    	cBomb = c;
	    	pista = false;
	 }
	 public int getFbomb(){
		 return fBomb;
	 }
	 public int getCbomb(){
		 return cBomb;
	 }
	 public boolean getPista(){
		 return pista;
	 }
	 public void setPista(boolean b){
		 pista = b;
	 }
	 
	
}
