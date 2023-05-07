import java.util.Scanner;

// Dios por favor no me abandones

public class GenerarLaberinto{

	public static void main(String[] args){

		System.out.println("¡Bienvenid@ al generador de Laberintos!");
		// Scanner sc = new Scanner(System.in);

		// System.out.println("Introduzca el largo del tablero: ");
		// int largo = sc.nextInt();
		// System.out.println("Introduzca el ancho del tablero: ");
		// int ancho = sc.nextInt();

		int largo = Integer.parseInt(args[0]);
		int ancho = Integer.parseInt(args[1]);

		Laberinto l = new Laberinto(ancho, largo);
		l.generaTablero();
		l.generaLaberinto();
		System.out.println(l.printLaberinto());
		l.generaSolucion();
		System.out.println(l.printRespuesta());
	}
}
