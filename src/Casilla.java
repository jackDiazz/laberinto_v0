public class Casilla{
	// Atributos
	// Vecinos de la casilla
	private boolean vecinoIzquierdo, vecinoSuperior, vecinoInferior, vecinoDerecho;

	// Bordes de la casilla
	private boolean bordeInferior = true, bordeDerecho = true, bordeSuperior = true, bordeIzquierdo = true;

	// Indexes en la matriz
	private int indexI, indexJ;

	// Array de vecinos sin visitar
	boolean[] vecinosDisponibles = new boolean[4]; 
	boolean[] vecinosVisitados = new boolean[4];
	// Otros atributos
	private boolean explorada, camino, inicio, fin;
	private int vecinosSinVisitar;

	// Constructor
	public Casilla(boolean vecIz, boolean vecSup, boolean vecInf, boolean vecDer, int vsv, int i, int j){
		explorada = false;
		camino = false;
		inicio = false;
		fin = false;
		vecinoIzquierdo = vecIz;
		vecinoSuperior = vecSup;
		vecinoInferior = vecInf;
		vecinoDerecho = vecDer;
		vecinosSinVisitar = vsv;
		indexI = i;
		indexJ = j;
		if(vecIz){
			vecinosDisponibles[0] = true;
			vecinosVisitados[0] = false;
		}
		if(vecSup){
			vecinosDisponibles[1] = true;
			vecinosVisitados[1] = false;
		}
		if(vecInf){
			vecinosDisponibles[2] = true;
			vecinosVisitados[2] = false;
		}
		if(vecDer){
			vecinosDisponibles[3] = true;
			vecinosVisitados[3] = false;
		}
	}	

	// Getters
	public boolean esExplorada(){
		return explorada;
	}
	public boolean esCamino(){
		return camino;
	}
	public int vecinos(){
		return vecinosSinVisitar;
	}
	public int getIndexI(){
		return indexI;
	}
	public int getIndexJ(){
		return indexJ;
	}
	public boolean[] getVecinosDisponibles(){
		return vecinosDisponibles;
	}
	public boolean getVecinoIzquierdo(){
		return vecinoIzquierdo;
	}
	public boolean getVecinoSuperior(){
		return vecinoSuperior;
	}
	public boolean getVecinoInferior(){
		return vecinoInferior;
	}
	public boolean getVecinoDerecho(){
		return vecinoDerecho;
	}
	public boolean getBordeInferior(){
		return bordeInferior;
	}
	public boolean getBordeDerecho(){
		return bordeDerecho;
	}

	public boolean getBordeIzquierdo(){
		return bordeIzquierdo;
	}

	public boolean getBordeSuperior(){
		return bordeSuperior;
	}
	
	// Setters
	public void explorada(){
		explorada = true;
	}
	public void camino(boolean cam){
		camino = cam;
	}
	public void setVecinos(int index, boolean valor){
		vecinosDisponibles[index] = valor;
	}
	public void setVisitados(int index, boolean valor){ vecinosVisitados[index] = valor; }
	public void vecinosPorVisitar(int v){
		vecinosSinVisitar = v;
	}
	public void setBordeInferior(boolean borInf){
		bordeInferior = borInf;
	}
	public void setBordeDerecho(boolean borDer){
		bordeDerecho = borDer;
	}

	public void setBordeSuperior(boolean borSup){
		bordeSuperior = borSup;
	}
	public void setBordeIzquierdo(boolean borIzq){
		bordeIzquierdo = borIzq;
	}

	public void setCamino(){ camino = true; }

	public void setInicio(boolean esInicio){ inicio = esInicio; }
	public void setFin(boolean esFin){ fin = esFin; }

}