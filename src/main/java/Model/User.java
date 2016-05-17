package Model;

public class User implements Comparable<User> {
	private String nombre;
	private int puntuacion;
	public User(String nombre,int puntuacion){
		this.nombre=nombre;
		this.puntuacion=puntuacion;
	}
	public String getNombre(){
		return nombre;
	}
	public int getPuntuacion(){
		return puntuacion;
	}
	public void setNombre(String n){
		this.nombre=n;
	}
	public void setResultado(int r){
		this.puntuacion=r;
	}
	
	
public int compareTo(User u){
		if(puntuacion<u.getPuntuacion()){
			return -1;
		}
		if(puntuacion>u.getPuntuacion()){
			return 1;
		}
		return 0;
	}
}
