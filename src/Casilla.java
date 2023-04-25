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
	// Array de vecinos por vecinos sin vecinos sin visitar 
	boolean[] vecinosDisponibles = new boolean[4]; 
	// Otros atributos
	private boolean explorada = false;
	private boolean camino = false;
	private int vecinosSinVisitar;

	// Constructor
	public Casilla(boolean vecIz, boolean vecSup, boolean vecInf, boolean vecDer, int vsv, int i, int j){
		this.vecinoIzquierdo = vecIz;
		this.vecinoSuperior = vecSup;
		this.vecinoInferior = vecInf;
		this.vecinoDerecho = vecDer;
		this.vecinosSinVisitar = vsv;
		this.indexI = i;
		this.indexJ = j;
		if(vecIz == true){
			vecinosDisponibles[0] = true;
		} else {
			vecinosDisponibles[0] = false;
		}
		if(vecSup == true){
			vecinosDisponibles[1] = true;
		} else {
			vecinosDisponibles[1] = false;
		}
		if(vecInf == true){
			vecinosDisponibles[2] = true;
		} else {
			vecinosDisponibles[2] = false;
		}
		if(vecDer == true){
			vecinosDisponibles[3] = true;
		} else {
			vecinosDisponibles[3] = false;
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
	public void vecinosPorVisitar(int v){
		this.vecinosSinVisitar = v;
	}
	public void setBordeInferior(boolean borInf){
		bordeInferior = borInf;
	}
	public void setBordeDerecho(boolean borDer){
		bordeDerecho = borDer;
	}

}
