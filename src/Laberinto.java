import estructuras.*;
import java.util.Random;
import java.util.NoSuchElementException;

public class Laberinto{
	// Atributos
	private Casilla[][] tablero;
	private int ancho, largo;
	private  String generado, resuelto;

	private static Random generaCoord = new Random();

	// Constructor
	public Laberinto(int ancho, int largo){
		tablero = new Casilla[largo][ancho];
		this.ancho = ancho;
		this.largo = largo;
		generado = null;
		resuelto = null;
	}

	// Getters
	public int getAncho(){
		return ancho;
	}

	public int getLargo(){
		return largo;
	}

	public String getGenerado(){
		return generado;
	}

	public Casilla[][] getTablero(){
		return tablero;
	}

	// Método auxiliar para laberinto en string.
	public void setGenerado(String str){
		generado = str;
	}

	private void setResuelto(String str){
		resuelto = str;
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
		Cola c = new Cola();
		// Elegir una casilla aleatoriamente
		int i = generaCoord.nextInt(largo);
		int j = generaCoord.nextInt(ancho);
		Casilla aux = tablero[i][j];

		// Marcarla como visitada
		aux.explorada();

		// Agregarla a la pila
		c.queue(aux);
		while(!c.esVacia()){
			if(aux.vecinos() > 0){
				// System.out.println("Coordenadas actuales: " + aux.getIndexI() + ", " + aux.getIndexJ());
				int indexVecino = encontrarIndice(aux.getVecinosDisponibles());
				// Corregir el número de vecinos por visitar de la casilla actual
				aux.vecinosPorVisitar(aux.vecinos()-1);
				if(indexVecino == 0 && !tablero[i][j-1].esExplorada() && aux.getVecinoIzquierdo() ) { // El vecino izquierdo está sin visitar
					// Indicar en el array de la casilla actual que el vecino izquierdo ya está visitado
					aux.setVecinos(0, false);
					aux.setVisitados(0, false);
					// Volver a la casilla actual el vecino izquierdo
					aux.setBordeIzquierdo(false);
					aux = tablero[i][j-1];
					aux.setBordeDerecho(false);
				} else if(indexVecino == 1 && !tablero[i-1][j].esExplorada() && aux.getVecinoSuperior() ){ // El vecino superior está sin visitar
					// Indicar en la array de la casilla actual que el vecino superior ya está visitado
					aux.setVecinos(1, false);
					aux.setVisitados(1, false);
					// Volver a la casilla actual el vecino superior
					aux.setBordeSuperior(false);
					aux = tablero[i-1][j];
					aux.setBordeInferior(false);
				} else if(indexVecino == 2 && !tablero[i+1][j].esExplorada() && aux.getVecinoInferior()){ // El vecino inferior está sin visitar
					// Indicar en la array de la casilla actual que el vecino inferior ya está visitado
					aux.setVecinos(2, false);
					aux.setVisitados(2, false);
					// Volver a la casilla actual el vecino inferior
					aux.setBordeInferior(false);
					aux = tablero[i+1][j];
					aux.setBordeSuperior(false);
				} else if(indexVecino == 3 && !tablero[i][j+1].esExplorada() && aux.getVecinoDerecho()){ // El vecino derecho está sin visitar
					// Indicar en la array de la casilla actual que el vecino derecho ya está visitado
					aux.setVecinos(3, false);
					aux.setVisitados(3, false);
					// Volver a la casilla actual el vecino derecho
					aux.setBordeDerecho(false);
					aux = tablero[i][j+1];
					aux.setBordeIzquierdo(false);
				}
				i = aux.getIndexI();
				j = aux.getIndexJ();
				// Indicar que la casilla actual está explorada
				aux.explorada();
				c.queue(aux);
			} else {
				c.dequeue();
				try {
					aux = (Casilla) c.peek();
				}catch(NullPointerException  | NoSuchElementException a) {break;}
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
	public void generaSolucion(){
		Pila casillasPróximas = new Pila();
		Pila casillasExploradas = new Pila();
		Casilla casillaActual = tablero[0][0];
		Pila i = new Pila();
		Pila j = new Pila();
		int iActual = 0;
		int jActual = 0;
		int iFinal = largo-1;
		int jFinal = ancho-1;

		casillasPróximas.push(casillaActual);
		casillasExploradas.push(casillaActual);
		i.push(iActual);
		j.push(jActual);

		while((iActual != iFinal && jActual != jFinal)){
			try{
			casillaActual = (Casilla) casillasPróximas.pop();
			iActual = (int) i.pop();
			jActual = (int) j.pop();
			casillaActual.camino(true);
			// Abajo
			if(casillaActual.getBordeInferior() == false){
				if(casillasExploradas.contiene(tablero[iActual+1][jActual]) == false){
					casillasPróximas.push(tablero[iActual+1][jActual]);
					casillasExploradas.push(tablero[iActual+1][jActual]);
					i.push(iActual+1);
					j.push(jActual);
				}
			}
			// Arriba
			if(casillaActual.getBordeSuperior() == false){
				if(casillasExploradas.contiene(tablero[iActual-1][jActual]) == false){
					casillasPróximas.push(tablero[iActual-1][jActual]);
					casillasExploradas.push(tablero[iActual-1][jActual]);
					i.push(iActual-1);
					j.push(jActual);
				}
			}
			// Derecha
			if(casillaActual.getBordeDerecho() == false){
				if(casillasExploradas.contiene(tablero[iActual][jActual+1]) == false){
					casillasPróximas.push(tablero[iActual][jActual+1]) ;
					casillasExploradas.push(tablero[iActual][jActual+1]) ;
					i.push(iActual);
					j.push(jActual+1);
				}
			}
			// Izquierda
			if(casillaActual.getBordeIzquierdo() == false){
				if(casillasExploradas.contiene(tablero[iActual][jActual-1])  == false){
					casillasPróximas.push(tablero[iActual][jActual-1]) ;
					casillasExploradas.push(tablero[iActual][jActual-1]) ;
					i.push(iActual);
					j.push(jActual-1);
				}
			}
		} catch(NoSuchElementException e){break;}
		}
	}

	// Para imprimir laberinto sin respuesta
	public String printLaberinto(){
		if(generado == null) setGenerado( laberintoGrafico() );
		return generado;
	}

	// Para imprimir laberinto con respuesta
	public String printRespuesta(){
		if(resuelto == null) setResuelto( laberintoRespuesta() );
		return resuelto;
	}

	public String laberintoRespuesta(){
		String lab = "";
		// Añadir lineas de arriba
		for(int l = 0; l < ancho+1; l++){
			lab += "__";
		}
		lab += "\n";

		// Recorrer tablero
		for(int i = 0; i < largo; i++){
			lab += "|";
			for(int j = 0; j < ancho; j++){
				if(i == 0){ // Caso especial de la primera fila
					if(j == ancho-1) continue;
					if(tablero[0][j].esCamino()){
						lab += "x";
					} else {
						if(tablero[0][j].getBordeInferior() && tablero[1][j].getBordeSuperior()) lab += "_";
						else lab += " ";
					}
					if(tablero[0][j].getBordeDerecho() && tablero[0][j+1].getBordeIzquierdo()) lab += "|";
					else lab += " ";
					if( j == ancho - 2){
						if(tablero[0][ancho-2].getBordeDerecho() && tablero[0][ancho-1].getBordeIzquierdo()) lab += "|";
						else lab += " ";
					}
				} else if(i == largo-1) { // Caso especial de la última fila
					if(tablero[i][j].esCamino()){lab += "x";} else {lab += "_";}
					if(j == ancho-1) continue;
					if(tablero[largo-1][j].getBordeDerecho() && tablero[largo-1][j+1].getBordeIzquierdo()) lab += "|";
					else lab += " ";
				} else if(j == 0) { // Caso especial de la primera columna
					if(i == 0 && j == 0){
						if(tablero[0][0].esCamino()){lab += "x";} else {
							if(tablero[0][0].getBordeInferior() && tablero[1][0].getBordeSuperior()) lab += "_";
							else lab += " ";
						}
						if(tablero[0][0].getBordeDerecho() && tablero[0][1].getBordeIzquierdo()) lab += "|";
						else lab += " ";
					} else if(i == largo-1 && j == 0){
						if(tablero[largo-1][0].esCamino()){lab += "x";} else {lab += "_";}
					} else{
						if(tablero[i][0].esCamino()){lab += "x";} else {
							if(tablero[i][0].getBordeInferior() && tablero[i+1][1].getBordeSuperior()) lab += "_";
							else lab += " ";
						}
						if(tablero[i][0].getBordeDerecho() && tablero[i][1].getBordeIzquierdo()) lab += "|";
						else lab += " ";
					}
				} else if(j == ancho-1) { // Caso especial de la última columna
					if(tablero[i][ancho-1].esCamino()){lab += "x";} else {
						if(tablero[i][ancho-1].getBordeInferior() && tablero[i+1][ancho-1].getBordeSuperior()) lab += "_";
						else lab += " ";
					}
				} else {
					if(i == 0 || i == largo-1 || j == 0 || j == ancho-1) continue;
					if(tablero[i][j].esCamino()){lab += "x";} else {
						if(tablero[i][j].getBordeInferior() && tablero[i+1][j].getBordeSuperior()) lab += "_";
						else lab += " ";
					}
					if(tablero[i][j].getBordeDerecho() && tablero[i][j+1].getBordeIzquierdo()) lab += "|";
					else lab += " ";
				}
			}
			lab += "|\n";
		}
		return lab;
	}

	public String laberintoGrafico(){
		String lab = "";
		// Añadir lineas de arriba
		for(int l = 0; l < ancho+1; l++){
			lab += "__";
		}
		lab += "\n";

		// Recorrer tablero
		for(int i = 0; i < largo; i++){
			lab += "|";
			for(int j = 0; j < ancho; j++){
				if(i == 0){ // Caso especial de la primera fila
					if( j == ancho-1) continue;
					if(tablero[0][j].getBordeInferior() && tablero[1][j].getBordeSuperior() ) lab += "_";
					else lab += " ";
					if(tablero[0][j].getBordeDerecho() && tablero[0][j+1].getBordeIzquierdo()) lab += "|";
					else lab += " ";
					if( j == ancho - 2){
						if(tablero[0][ancho-2].getBordeDerecho() && tablero[0][ancho-1].getBordeIzquierdo()) lab += "|";
						else lab += " ";
					}
				} else if(i == largo-1) { // Caso especial de la última fila
					lab += "_";
					if(j == ancho-1) continue;
					if(tablero[largo-1][j].getBordeDerecho() && tablero[largo-1][j+1].getBordeIzquierdo() ) lab += "|";
					else lab += " ";
				} else if(j == 0) { // Caso especial de la primera columna
					if(i == 0 && j == 0){
						if(tablero[0][0].getBordeInferior() && tablero[1][0].getBordeSuperior() ) lab += "_";
						else lab += " ";
						if(tablero[0][0].getBordeDerecho() && tablero[0][1].getBordeIzquierdo() ) lab += "|";
						else lab += " ";
					} else if(i == largo-1 && j == 0){
						lab += "_";
					} else{
						if(tablero[i][0].getBordeInferior() && tablero[i+1][1].getBordeSuperior() ) lab += "_";
						else lab += " ";
						if(tablero[i][0].getBordeDerecho() && tablero[i][1].getBordeIzquierdo() ) lab += "|";
						else lab += " ";
					}
				} else if(j == ancho-1) { // Caso especial de la última columna
					if(tablero[i][ancho-1].getBordeInferior() && tablero[i+1][ancho-1].getBordeSuperior() ) lab += "_";
					else lab += " ";
				}else {
					if(i == 0 || i == largo-1 || j == 0 || j == ancho-1) continue;
					if(tablero[i][j].getBordeInferior() && tablero[i+1][j].getBordeSuperior() ) lab += "_";
					else lab += " ";
					if(tablero[i][j].getBordeDerecho() && tablero[i][j+1].getBordeIzquierdo() ) lab += "|";
					else lab += " ";
				}
			}
			lab += "|\n";
		}
		return lab;
	}
}
