package Model;
import java.awt.event.*;

import java.awt.*;

import javax.swing.*;

import java.util.*;

	//A cada hueco de la matriz que va a crear el tablero se le asigna una casilla


public class Casilla extends Observable{
	//Almacena el valor de la casilla (1-8 --> casilla normal)
	//                                (-9  --> casilla con mina)
	private int valor;
	//Almacena si esta tapada destapada o marcada
	//0=tapada 1=destapada 2=marcada
	private int estado;
	//Almacena si tiene una bomba
	//true = tiene mina false= no tiene mina
	private boolean mina; 
	//posicion i, j de la mina
	private int i;
	private int j;
	//Sirven para administrar la imagen de la casilla
	private String imagen;
	private Icon icanterior;
	
	public Casilla (boolean caboom){
		estado=0;
		mina=caboom;
		i = 0;
		j = 0;
	
	}
	
	public Casilla (int v, String img, int i, int j){
		valor = v;
		if (valor == -9)
			mina = true;
		imagen=img;
		icanterior = new ImageIcon(img);
		estado=0;
		this.i = i;
		this.j = j;
	}
	
	public int getValor(){
		return valor;
	}
	public int getEstado(){
		return estado;
	}
	public boolean getMina(){
		return mina;
	}
	public int getI(){
		return i;
	}
	public int getJ(){
		return j;
	}
	
	public String getImagen(){
		return imagen;
	}
	
	public Icon getIcante(){
		return icanterior;
	}
	public void setImg (String pImg){
		icanterior = new ImageIcon(imagen);
		imagen = pImg;
	}
	public void setValor(int v){
		valor = v;
	}
	public void setEstado(int e){
		int old=0;
		old = estado;
		estado = e;
		if (estado == 2){  //si marcada informar
			setChanged();
			if (valor == -9)
				notifyObservers("marcarMinaDescubierta");
			else
				notifyObservers("marcar");
	    }
		else{
			if (old == 2){
				setChanged();
				if (valor == -9)
					notifyObservers("desmarcarMinaOculta");
				else
				    notifyObservers("desmarcar");
			}
			if (estado == 1 && valor != -9){
				setChanged();
				notifyObservers("destapar");
			}
		}
		
	}
	public void marcar(){
		setEstado(2);
	}
	public void desmarcar(){
		setEstado(0);
	}
	public void setMina(boolean b){
		mina = b;
	}
	public void setI(int i){
		this.i = i;
	}
	public void setJ(int j){
		this.j = j;
	}
	public void setIante(ImageIcon img){
		icanterior = img;
	}
	
	public void parar(int n){
		System.out.println("PARAR JUEGO...");
		setChanged();
		if (n == -9)
		    this.notifyObservers("pararPierdo");
		else
			this.notifyObservers("pararGano");
	}
	public void iniciar(){
		System.out.println("INICIO RECIBIDO...");
		setChanged();
		this.notifyObservers("iniciar");
	}
	
	
}