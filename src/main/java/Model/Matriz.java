package Model;

import java.util.Random;

public class Matriz extends MatrizBuilder{
	private static Matriz mMatriz=new Matriz();
	private Matriz() {}
	public static Matriz getMatriz(){
		return mMatriz;
	}
	public Matriz(NivelDificultad nivel){
		matriz=this.construirMatriz(nivel);
	}
	
	public Casilla[][] construirMatriz(NivelDificultad nivel){
		Casilla[][] matriz=new Casilla[nivel.getNumFilas()][nivel.getNumColumnas()];
		//coloca las casillas vacias
		for(int i=0;i<nivel.getNumFilas();i++){
			for(int j=0;j<nivel.getNumColumnas();j++){
				matriz[i][j] = new Casilla(0,"",i,j);
			}
		}
		//coloca las minas
		PosiMina[] minas=this.generarMinasTablero(nivel);
		Buscaminas.getConBuildTablero().setPosiMina(minas);
		for(int k=0;k<minas.length;k++){
			matriz[minas[k].getFbomb()][minas[k].getCbomb()].setValor(-9);
		}
		//coloca los numeros
		matriz =generarValoresTablero(nivel.getNumFilas(), nivel.getNumColumnas(), matriz);
		
		for(int i=0;i<nivel.getNumFilas();i++){
			for(int j=0;j<nivel.getNumColumnas();j++){
				matriz[i][j].cambiarImagen();
			}
		}
		return matriz;
	}
	
	
	
	
	public PosiMina[] generarMinasTablero(NivelDificultad nivel){
		int vf=0;
		int vc=0;
		int f=nivel.getNumFilas();
		int c=nivel.getNumColumnas();
		int numMinas=nivel.getNivel()*c;
	
	//	numMinas = c*nivel;   //numero de minas es el producto de columnas del panel * nivel de juego
		Random r = new Random();  //funcion ramdom() para generar posiciones de minas
		PosiMina[] pm = new PosiMina[numMinas];
		for (int s = 0; s < numMinas; s++){
			pm[s]= new PosiMina(0,0);
		}
		int ipm = 0;
		for (int k = 0; k < numMinas; k++){
			vf = r.nextInt(f);
			vc = r.nextInt(c);
			int l = 0;
			while (l < numMinas){ //controlar que ramdom no ha dado una posicion de mina ya existente
				PosiMina psm = new PosiMina(vf,vc);  // se genera una poscion de mina
				
				if ((pm [l].getFbomb() == psm.getFbomb()) &&  //ha dado la misma posicion
				    (pm [l].getCbomb() == psm.getCbomb()) ){  //buscar otra posicion
					vf = r.nextInt(f);
				    vc = r.nextInt(c);
				    l = 0;
				}
				else
			       l = l + 1;
				     
			}
			pm [ipm] = new PosiMina(vf,vc);   //Guardar posicion de mina generada
			ipm = ipm + 1;
		}
		
		return pm;
	}
	
	
	
	
	
	
	public Casilla[][] generarValoresTablero(int f, int c,Casilla[][] tablero){
		for (int i = 0; i < f ; i++){
			for (int j = 0; j < c; j++){
				if (tablero[i][j].getValor() == -9){ //si hay una mina sumar uno a sus casillas vecinas
					if (j+1 < c && tablero[i][j+1].getValor()!=-9)
						//siguiente
						tablero[i][j+1].setValor(tablero[i][j+1].getValor()+1);
					if (j-1 >= 0 && tablero[i][j-1].getValor()!=-9)
				    	//anterior 
						tablero[i][j-1].setValor(tablero[i][j-1].getValor()+1);
					if (i-1 >= 0 && tablero[i-1][j].getValor()!=-9)
				    	//arriba
						tablero[i-1][j].setValor(tablero[i-1][j].getValor()+1);
				    if (i+1 < f && tablero[i+1][j].getValor()!=-9 )
				    	//abajo
				    	tablero[i+1][j].setValor(tablero[i+1][j].getValor()+1);
				    if (i-1 >= 0 && j+1 < c && tablero[i-1][j+1].getValor()!=-9)
				    	//superior derecha
				    	tablero[i-1][j+1].setValor(tablero[i-1][j+1].getValor()+1);	
				    if (i-1 >=0 && j-1 >= 0 && tablero[i-1][j-1].getValor()!=-9)
				    	//superior izquierda
				    	tablero[i-1][j-1].setValor(tablero[i-1][j-1].getValor()+1);
				    if (i+1 < f && j+1 < c && tablero[i+1][j+1].getValor()!=-9)
				    	//inferior derecha
				    	tablero[i+1][j+1].setValor(tablero[i+1][j+1].getValor()+1);
				    if (i+1 < f && j-1 >= 0 && tablero[i+1][j-1].getValor()!=-9)
				    	//inferior izquierda
				    	tablero[i+1][j-1].setValor(tablero[i+1][j-1].getValor()+1);
				}
		
			}
		}
		return tablero;	
	}
	

}
