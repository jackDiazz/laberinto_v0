import java.util.Scanner;

// Dios por favor no me abandones

public class GenerarLaberinto{

	public static void main(int ancho, int largo){

		System.out.println("¡Bienvenid@ al generador de Laberintos!");
		Scanner sc = new Scanner(System.in);

		// Comentar las 4 líneas siguientes si es necesario por lo de la entrada estándar
		/*System.out.println("Introduzca el largo del tablero: ");
		int largo = sc.nextInt();
		System.out.println("Introduzca el ancho del tablero: ");
		int ancho = sc.nextInt();*/

		Laberinto l = new Laberinto(ancho, largo);
		l.generaTablero();
		l.generaLaberinto();
		System.out.println(l.printLaberinto());

	}
}
