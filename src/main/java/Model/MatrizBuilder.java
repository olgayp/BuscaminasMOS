package Model;

public abstract class MatrizBuilder {
	protected static Casilla[][] matriz; 
	
	public void crearMatriz(NivelDificultad nivel){
		Matriz matriz=new Matriz(nivel);
	}
	
	public static Casilla[][] getMatrizBuilder(){
		return matriz;
	}
	public abstract Casilla[][] construirMatriz(NivelDificultad nivel);
}
