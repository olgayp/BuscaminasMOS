package Model;

public enum NivelDificultad {
	
	FACIL(1,7,10), MEDIO(2,10,15), DIFICIL(3,12,25);
	
	private int nivel;
	private int numFilas;
	private int numColumnas;
	private int numMinas;
	
	
	private NivelDificultad(int nivel, int numFilas, int numColumnas) {
		this.nivel = nivel;
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		//numero de minas 
		numMinas = numColumnas * nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public int getNumFilas() {
		return numFilas;
	}

	public int getNumColumnas() {
		return numColumnas;
	}

	public int getNumMinas() {
		return numMinas;
	}
	
	
	
	
	

}
