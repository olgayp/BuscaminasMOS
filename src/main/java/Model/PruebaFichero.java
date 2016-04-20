package Model;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import Model.NivelDificultad;
import Grafica.Top10;

public class PruebaFichero {
	public static void main(String[] args) {
		Top10 tp10 = null;
		Calendar f = Calendar.getInstance();
		try{
		 FileOutputStream fileOut = new FileOutputStream("fichero.obj");
	     ObjectOutputStream salida=new ObjectOutputStream(fileOut);
		
		for (int j = 0; j < 10; j++){
			String nom = "hola"+j;
			User u = new User(nom,NivelDificultad.FACIL.name(),20+j);
		    salida.writeObject(u);
		    
		}
		salida.close();
		}catch(Exception e1){System.out.println("error"+e1);};
		leerUsuario();
	}
	
	public static void leerUsuario(){
		int i = 0;
		int j = 5;
		try{
	    	FileInputStream fileIn = new FileInputStream("fichero.obj");
	    	ObjectInputStream entrada=new ObjectInputStream(fileIn);
	    	Object u = null;
	    	u = entrada.readObject();
	    	while(u!=null){
	            System.out.println("leerUsuario "+i);
	            i++;
	            u = entrada.readObject();
	    	}
	        entrada.close();
		}catch(Exception e){System.out.println("error"+e);};

	}
}
