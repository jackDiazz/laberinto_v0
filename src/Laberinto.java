import estructuras.*;

public class Laberinto{
	// Atributos
	private Casilla[][] tablero;
	private int ancho;
	private int largo;

	// Constructor
	public Laberinto(int ancho, int largo){
		tablero = new Casilla[ancho][largo];
		this.ancho = ancho;
		this.largo = largo;
	}

	// Método que generará tablero
	public void generaTablero(){
		// i = filas = largo, j = columnas = ancho
		for(int i = 0; i < largo; i++){
			for(int j = 0; j < ancho; j++){
				// Caso especial de la primera fila
				if(i == 0){
					// Caso especial de la primera casilla de la primera fila
					if(j == 0){
						tablero[i][j] = new Casilla(false, false, true, true, 2, i, j);
					} else if(j == ancho-1) { // Caso especial de la última casilla de la primera fila
						tablero[i][j] = new Casilla(true, false, true, false, 2, i, j);
					} else {
						tablero[i][j] = new Casilla(true, false, true, true, 3, i, j);
					}
				} else if(j == 0) { // Caso especial de la primera columna
					// Caso especial de la última casilla de la primera columna
					if(i == largo-1){
						tablero[i][j] = new Casilla(false, true, false, true, 2, i, j);
					} else {
						tablero[i][j] = new Casilla(false, true, true, true, 3, i, j);
					}
				} else if(j == ancho-1) { // Caso especial de la última columna
					// Caso especial de la última casilla de la última columna
					if(i == largo-1){
						tablero[i][j] = new Casilla(true, true, false, false, 2, i, j);
					} else {
						tablero[i][j] = new Casilla(true, true, true, false, 3, i, j);
					}
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
		int i = (int) (Math.random()*largo);
		int j = (int) (Math.random()*ancho);
		Casilla aux = tablero[i][j];
		// Marcarla como visitada
		aux.explorada();
		// Agregarla a la pila
		p.push(aux);
		while(p.esVacia() == false){
			if(aux.vecinos() > 0){
				boolean[] vecinos = aux.getVecinosDisponibles();
				int indexVecino = encontrarIndice(vecinos);
				// Corregir el número de vecinos por visitar de la casilla actual
				aux.vecinosPorVisitar(aux.vecinos()-1);
				if(indexVecino == 0){ // El vecino izquierdo está sin visitar
					// Indicar en la array de la casilla actual que el vecino izquierdo ya está visitado
					aux.setVecinos(indexVecino, false);
					// Volver a la casilla actual el vecino izquierdo
					aux = tablero[i][j-1];
					aux.setBordeDerecho(false); 
					j--;
				} else if(indexVecino == 1){ // El vecino superior está sin visitar
					// Indicar en la array de la casilla actual que el vecino superior ya está visitado
					aux.setVecinos(indexVecino, false);
					// Volver a la casilla actual el vecino superior
					aux = tablero[i-1][j];
					aux.setBordeInferior(false);
					i--;
				} else if(indexVecino == 2){ // El vecino inferior está sin visitar
					// Indicar en la array de la casilla actual que el vecino inferior ya está visitado
					aux.setVecinos(indexVecino, false);
					aux.setBordeInferior(false);
					// Volver a la casilla actual el vecino inferior
					aux = tablero[i+1][j];
					i++;
				} else if(indexVecino == 3){ // El vecino derecho está sin visitar
					// Indicar en la array de la casilla actual que el vecino derecho ya está visitado
					aux.setVecinos(indexVecino, false);
					aux.setBordeDerecho(false);
					// Volver a la casilla actual el vecino derecho
					aux = tablero[i][j+1];
					j++;
				}
				// Indicar que la casilla actual está explorada
				aux.explorada();
				p.push(aux);
			} else {
				p.pop();
				if(p.esVacia() == false){
					aux = (Casilla) p.top();
					i = aux.getIndexI();
					j = aux.getIndexJ();
				}
			}
		}
	}

	// Método
	private static int encontrarIndice(boolean[] vecinos){
		int z = (int) (Math.random()*4);
		int index = -1;
		if(z < 2){
			for(int l = 0; l < 4; l++){
				if(vecinos[l] == true){
					index = l;
					break;
				}
			}
		} else if(z > 1){
			for(int m = 3; m < 0; m--){
				if(vecinos[m] == true){
					index = m;
					break;
				}
			}
		}
		return index;
	}

	// Método que genera solución
	public void generaSolución(){
		Cola c = new Cola();
	}

	// Para imprimir laberinto sin respuesta
	public String printLaberinto(){
		String str = "";
		// Añadir lineas de arriba 
		for(int l = 0; l < ancho; l++){
			str += "_._";
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
