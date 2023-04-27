public class Casilla{
	// Atributos
	// Vecinos de la casilla
	private boolean vecinoIzquierdo;
	private boolean vecinoSuperior;
	private boolean vecinoInferior;
	private boolean vecinoDerecho;
	// Bordes de la casilla
	private boolean bordeInferior = true;
	private boolean bordeDerecho = true;
	// Indexes en la matriz
	private int indexI;
	private int indexJ;
	// Array de vecinos sin visitar
	boolean[] vecinosDisponibles = new boolean[4]; 
	boolean[] vecinosVisitados = new boolean[4];
	// Otros atributos
	private boolean explorada = false;
	private boolean camino = false;
	private int vecinosSinVisitar;

	// Constructor
	public Casilla(boolean vecIz, boolean vecSup, boolean vecInf, boolean vecDer, int vsv, int i, int j){
		explorada = false;
		camino = false;
		this.vecinoIzquierdo = vecIz;
		this.vecinoSuperior = vecSup;
		this.vecinoInferior = vecInf;
		this.vecinoDerecho = vecDer;
		this.vecinosSinVisitar = vsv;
		this.indexI = i;
		this.indexJ = j;
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
	
	// Setters
	public void explorada(){
		this.explorada = true;
	}
	public void camino(boolean cam){
		this.camino = cam;
	}
	public void setVecinos(int index, boolean valor){
		this.vecinosDisponibles[index] = valor;
	}
	public void setVisitados(int index, boolean valor){ this.vecinosVisitados[index] = valor; }
	public void vecinosPorVisitar(int v){
		this.vecinosSinVisitar = v;
	}
	public void setBordeInferior(boolean borInf){
		bordeInferior = borInf;
	}
	public void setBordeDerecho(boolean borDer){
		bordeDerecho = borDer;
	}

	public void setCamino(){ camino = true; }

}