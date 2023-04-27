//import estructuras.*;
import java.util.Random;

public class Laberinto{
	// Atributos
	private Casilla[][] tablero;
	private int ancho;
	private int largo;

	private static Random generaCoord = new Random();

	// Constructor
	public Laberinto(int ancho, int largo){
		tablero = new Casilla[largo][ancho];
		this.ancho = ancho;
		this.largo = largo;
	}

	// Método que generará tablero
	public void generaTablero(){
		// Genera las 4 casillas de esquina
		tablero[0][0] = new Casilla(false, false, true, true, 2, 0, 0);
		tablero[0][ancho-1] = new Casilla(true, false, true, false, 2, 0, ancho-1);
		tablero[largo-1][0] = new Casilla(false, true, false, true, 2, largo-1, 0);
		tablero[largo-1][ancho-1] = new Casilla(true, true, false, false, 2, largo-1, ancho-1);

		// i = filas = largo, j = columnas = ancho
		for(int i = 0; i < largo; i++){
			for(int j = 0; j < ancho; j++){
				// SALTA CASILLAS DE ESQUINA
				if( (i==0 && j==0) || (i==0 && j==ancho-1) || (i==largo-1 && j==0) ||(i==largo-1 && j==ancho-1)) continue;
				if(i == 0){ // Caso especial de la primera fila
						tablero[0][j] = new Casilla(true, false, true, true, 3, i, j);
				} else if(j == 0) { // Caso especial de la primera columna
						tablero[i][0] = new Casilla(false, true, true, true, 3, i, j);
				} else if(j == ancho-1) { // Caso especial de la última columna
						tablero[i][j] = new Casilla(true, true, true, false, 3, i, j);
				} else if(i == largo-1) { // Caso especial de la última fila
					tablero[i][j] = new Casilla(true, true, false, true, 3, i, j);
				} else {
					tablero[i][j] = new Casilla(true, true, true, true, 4, i, j);
				}
			}
		}
	}
	
	// Método que genera laberinto
	public void generaLaberinto(){
		Pila p = new Pila();
		// Elegir una casilla aleatoriamente
		int i = generaCoord.nextInt(largo);
		int j = generaCoord.nextInt(ancho);
		Casilla aux = tablero[i][j];
		// System.out.println("Coordenadas actuales: " + aux.getIndexI() + ", " + aux.getIndexJ());

		// Marcarla como visitada
		aux.explorada();

		// Agregarla a la pila
		p.push(aux);
		while(!p.esVacia()){
			if(aux.vecinos() > 0){
				// System.out.println("Coordenadas actuales: " + aux.getIndexI() + ", " + aux.getIndexJ());
				int indexVecino = encontrarIndice(aux.getVecinosDisponibles());
				// Corregir el número de vecinos por visitar de la casilla actual
				aux.vecinosPorVisitar(aux.vecinos()-1);
				if(indexVecino == 0 && !tablero[i][j-1].esExplorada() ) { // El vecino izquierdo está sin visitar
					// Indicar en el array de la casilla actual que el vecino izquierdo ya está visitado
					aux.setVecinos(0, false);
					aux.setVisitados(0, false);
					// Volver a la casilla actual el vecino izquierdo
					aux = tablero[i][j-1];
					aux.setBordeDerecho(false);
				} else if(indexVecino == 1 && !tablero[i-1][j].esExplorada() ){ // El vecino superior está sin visitar
					// Indicar en la array de la casilla actual que el vecino superior ya está visitado
					aux.setVecinos(1, false);
					aux.setVisitados(1, false);
					// Volver a la casilla actual el vecino superior
					aux = tablero[i-1][j];
					aux.setBordeInferior(false);
				} else if(indexVecino == 2 && !tablero[i+1][j].esExplorada()){ // El vecino inferior está sin visitar
					// Indicar en la array de la casilla actual que el vecino inferior ya está visitado
					aux.setVecinos(2, false);
					aux.setVisitados(2, false);
					// Volver a la casilla actual el vecino inferior
					aux = tablero[i+1][j];
					aux.setBordeInferior(false);
				} else if(indexVecino == 3 && !tablero[i][j+1].esExplorada()){ // El vecino derecho está sin visitar
					// Indicar en la array de la casilla actual que el vecino derecho ya está visitado
					aux.setVecinos(3, false);
					aux.setVisitados(3, false);
					// Volver a la casilla actual el vecino derecho
					aux = tablero[i][j+1];
					aux.setBordeDerecho(false);
				}
				i = aux.getIndexI();
				j = aux.getIndexJ();
				// Indicar que la casilla actual está explorada
				aux.explorada();
				p.push(aux);
			} else {
				p.pop();
				try {
					aux = (Casilla) p.top();
				}catch(NullPointerException  a) {break;}
				i = aux.getIndexI();
				j = aux.getIndexJ();
			}
		}
	}

	// Método para encontrar si aún hay vecinos por visitar
	private static int encontrarIndice(boolean[] vecinos){
		do {
			int z = generaCoord.nextInt(4);
			if (vecinos[z]) return z; // El vecinos[z] == true no es necesario, solo con el valor que devuelve vecinos[z]
		} while( vecinos[0] || vecinos[1] || vecinos[2] || vecinos[3]);
		return -1; // Solo para que el compilador no marque que se requiere un return aquí
	}

	// Método que genera solución
	public void generaSolución(){
		Cola c = new Cola();
		int i = generaCoord.nextInt(largo);
		int j = generaCoord.nextInt(ancho);
		Casilla aux = tablero[i][j];
		int copiaInicioI = i, copiaInicioJ = j;
		int k = generaCoord.nextInt(largo);
		int l = generaCoord.nextInt(ancho);
		Casilla fin = tablero[k][l];
		//System.out.println("Coordenadas actuales: " + aux.getIndexI() + ", " + aux.getIndexJ());
		//System.out.println("Coordenadas del final: " + fin.getIndexI() + ", " + fin.getIndexJ());

		// Marcarla como visitada
		aux.explorada();

		// Agregarla a la pila
		c.queue(aux);
		while(!c.esVacia()){
			if( !(aux.getIndexI() == fin.getIndexI() && aux.getIndexJ() == fin.getIndexJ() )) {
				if (aux.vecinos() > 0) {
					// System.out.println("Coordenadas actuales: " + aux.getIndexI() + ", " + aux.getIndexJ());
					int indexVecino = encontrarIndice(aux.getVecinosDisponibles());
					// Corregir el número de vecinos por visitar de la casilla actual
					aux.vecinosPorVisitar(aux.vecinos() - 1);
					if (indexVecino == 0 && !tablero[i][j - 1].esExplorada()) { // El vecino izquierdo está sin visitar
						// Indicar en el array de la casilla actual que el vecino izquierdo ya está visitado
						aux.setVecinos(0, false);
						aux.setVisitados(0, false);
						// Volver a la casilla actual el vecino izquierdo
						aux = tablero[i][j - 1];
						aux.setBordeDerecho(false);
					} else if (indexVecino == 1 && !tablero[i - 1][j].esExplorada()) { // El vecino superior está sin visitar
						// Indicar en la array de la casilla actual que el vecino superior ya está visitado
						aux.setVecinos(1, false);
						aux.setVisitados(1, false);
						// Volver a la casilla actual el vecino superior
						aux = tablero[i - 1][j];
						aux.setBordeInferior(false);
					} else if (indexVecino == 2 && !tablero[i + 1][j].esExplorada()) { // El vecino inferior está sin visitar
						// Indicar en la array de la casilla actual que el vecino inferior ya está visitado
						aux.setVecinos(2, false);
						aux.setVisitados(2, false);
						// Volver a la casilla actual el vecino inferior
						aux = tablero[i + 1][j];
						aux.setBordeInferior(false);
					} else if (indexVecino == 3 && !tablero[i][j + 1].esExplorada()) { // El vecino derecho está sin visitar
						// Indicar en la array de la casilla actual que el vecino derecho ya está visitado
						aux.setVecinos(3, false);
						aux.setVisitados(3, false);
						// Volver a la casilla actual el vecino derecho
						aux = tablero[i][j + 1];
						aux.setBordeDerecho(false);
					}
					i = aux.getIndexI();
					j = aux.getIndexJ();
					aux.setCamino();
					// Indicar que la casilla actual está explorada
					aux.explorada();
					c.queue(aux);
				} else {
					c.dequeue();
					try {
						aux = (Casilla) c.peek();
					} catch (NullPointerException a) {
						break;
					}
					i = aux.getIndexI();
					j = aux.getIndexJ();
				}
			} else break;
		}
	}

	// Para imprimir laberinto sin respuesta
	public String printLaberinto(){
		String str = "";
		// Añadir lineas de arriba 
		for(int l = 0; l < ancho+1; l++){
			str += "__";
		}
		str += "\n";
		// Recorrer tablero
		for(int i = 0; i < largo; i++){
			str += "|";
			for(int j = 0; j < ancho; j++){
				if(tablero[i][j].getBordeDerecho() && tablero[i][j].getBordeInferior()){
					str += "_|";
				} else if(tablero[i][j].getBordeDerecho()){
					str += " |";
				} else if(tablero[i][j].getBordeInferior()) {
					str += "__";
				}		
			}
			str += "\n";
		}
		return str;
	}

	// Para imprimir laberinto con respuesta
	public String printRespuesta(){
		return "";
	}
}
