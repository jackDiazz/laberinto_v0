import java.util.Scanner;

// Dios por favor no me abandones

public class GenerarLaberinto{

	public static void main(String[] args){
		System.out.println("¡Bienvenid@ al generador de Laberintos!");
		Scanner sc = new Scanner(System.in);
		int ancho;
		int largo;
		System.out.println("Introduzca el ancho del tablero: ");
		ancho = sc.nextInt();
		System.out.println("Introduzca el largo del tablero: ");
		largo = sc.nextInt();
		Laberinto l = new Laberinto(ancho, largo);
		l.generaTablero();
		l.generaLaberinto();
		System.out.println(l.printLaberinto());
	}

}
