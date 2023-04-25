package estructuras;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Cola</p>
 * <p>Esta clase implementa una Cola genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta cola.
 */
public class Cola<T> extends LinealAdapter implements Encolable<T> {

    private class IteradorCola implements Iterator<T> {
        private Nodo siguiente;

        private IteradorCola() {
			siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
			return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override
        public T next() {
			T aux = (T) siguiente.elemento;
			siguiente = siguiente.siguiente;
			return aux;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }

    /**
     * Constructor por omisión de la clase.
     */
    public Cola() {
        //Aqui no hay que hacer nada,
        //ya que los valores por default nos sirven al crear un objeto.
    }

    /**
     * Constructor que recibe un arreglo de elementos de tipo <code>T</code>.
     * Crea una cola con los elementos del arreglo.
     * @param elementos El arreglo que se recibe como parámetro.
     */
    public Cola(T[] elementos) {
		for(int i = 0; i < elementos.length; i++){
			queue(elementos[i]);
		}
    }


    /**
     * Constructor que recibe una colección de tipo {@link Coleccionable}
     * de elementos de tipo <code>T</code> y los agrega a la nueva cola.
     * @param elementos La colección de elementos a agregar.
     */
    public Cola(Coleccionable<T> elementos) {
		Iterator it = elementos.iterator();
		while(it.hasNext()){
			queue((T) it.next());
		}
    }

    /**
     * Agrega un elemento en el rabo de la Cola.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    public void queue(T elemento) throws IllegalArgumentException{
		super.agregarAlFinal(elemento);
    }

    /**
     * Elimina el elemento del inicio de la Cola y lo regresa.
     * @throws NoSuchElementException si la cola es vacía
     */
    public T dequeue() throws NoSuchElementException{
		return (T) super.eliminarInicio();
    }

    /**
     * Nos permite ver el elemento en el inicio de la Cola
     *
     * @return el elemento en un extremo de la estructura.
     */
    public T peek() {
		Nodo aux = ver();
		return (T) aux.elemento;
    }

    @Override
    public String toString() {
		String str = "Cola: ";
		Iterator it = new IteradorCola();
		while(it.hasNext()){
			str += it.next() + " ";
		}
		return str;
    }

}
