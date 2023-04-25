package estructuras;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Pila</p>
 * <p>Esta clase implementa una Pila genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta pila.
 */
public class Pila<T> extends LinealAdapter implements Apilable<T> {

    /**
     * Constructor por omisión de la clase;
     */
    public Pila() {
    }

    /**
     * Constructor que recibe un arreglo de elementos de tipo <code>T</code>.
     * Crea una pila donde el primer elemento del arreglo es el que queda al
     * fondo de la pila, el último elemento del arreglo queda en el tope
     * de la pila.
     * @param elementos
     */
    public Pila(T[] elementos) {
		for(int i = 0; i < elementos.length; i++){
			push(elementos[i]);
		}
    }

    /**
     * Constructor que recibe una colección de tipo {@link Coleccionable}
     * de elementos de tipo <code>T</code>.
     * Crea una pila donde el primer elemento de la colección es el que queda al
     * fondo de la pila, el último elemento de la colección queda en el tope
     * de la pila.
     * @param elementos La colección de elementos a agregar.
     */
    public Pila(Coleccionable<T> elementos) {
		Iterator it = elementos.iterator();
		while(it.hasNext()){
			push((T) it.next());
		}
	}

    /**
     * Constructor de la clase, que recibe una pila y regresa una copia
     * exacta de ésta.
     *
     * @param pila La pila que se va a copiar.
     */
    /* Se habló en clase de eliminar este método
	 * public Pila(Pila <T> pila){
        //aqui va tu codigo

    }
	*/

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    public void push(T elemento) throws IllegalArgumentException {
		super.agregarAlInicio(elemento);
    }

    /**
     * Elimina el elemento del tope de la pila y lo regresa.
     * @throws NoSuchElementException si la pila es vacía.
     */
    public T pop() throws NoSuchElementException{
		return (T) eliminarInicio();
    }

    /**
     * Nos permite ver el elemento en el tope de la pila
     *
     * @return el elemento en un extremo de la estructura.
     */
    public T top() {
		Nodo aux = ver();
		return (T) aux.elemento;
    }

     @Override
    public String toString() {
		String str = "Pila: ";
		Iterator it = super.iterator();
		while(it.hasNext()){
			str += it.next() + " ";
		}
		return str;
    }

}
