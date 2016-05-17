package Grafica;


import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Reproductor {
	
	
	private static Reproductor mReproductor = new Reproductor();
	private Reproductor() {	}
	public static Reproductor getReproductor() {
		return mReproductor;
	}
	
	
	public void reproducirVictoria() {
		URL url=getClass().getClassLoader().getResource("Victory.wav");
		AudioClip clip=Applet.newAudioClip(url);
		clip.play();
		
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void reproducirDerrota() {
		URL url=getClass().getClassLoader().getResource("minaSound.wav");
		AudioClip clip=Applet.newAudioClip(url);
		clip.play();
		
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		Reproductor r = Reproductor.getReproductor();
//		System.out.println("Reproducir");
//		r.reproducirSonido("/Victory.wav");
//	}
}
