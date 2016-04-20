package Grafica;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MyBorder {
	
	private Border borde;
	private Border borde1;
	
	public MyBorder (boolean interno){
		Border b1 = null;
		if (interno){
			b1 = BorderFactory.createBevelBorder(0,Color.GRAY,Color.WHITE); 
	    }
		else{
			b1 = BorderFactory.createBevelBorder(0,Color.WHITE,Color.GRAY);
		}
		borde = BorderFactory.createCompoundBorder(b1, new EmptyBorder(8,8,8,8));
		borde = BorderFactory.createCompoundBorder(new EmptyBorder(8,8,8,8),borde);
	    borde1 = b1;
			
	}
	public Border getBorde(){
		return borde;
	}
	
	public Border getBorde1(){
		return borde1;
	}
}
