package Model;
import java.util.Calendar;

//Almacena los usuarios nuevos junto con sus records

public class User implements java.io.Serializable{
	private String nombre;
	private String  nivel;
	private int record;
	private Calendar fechRecord;
	
	public User(String nombre, String n, int record) {
		this.nombre=nombre;
		this.record=record;
		nivel = n;
		fechRecord = Calendar.getInstance();
	}
	
	//Actualiza el record
	public void nuevoRecord(int nRecord){
		if(nRecord<=record){
			record=nRecord;
			fechRecord = Calendar.getInstance();
		}
	}
	public String getNombre(){
		return nombre;
	}
	public int getRecord(){
		return record;
	}
	public Calendar getFechRecord(){
		return fechRecord;
	}
	public void setNombre(String nomb){
		nombre = nomb;
	}
	public String getNivel(){
		return nivel;
	}

}
