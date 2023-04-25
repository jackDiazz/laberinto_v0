import estructuras.*;
public class Test{

	// Dios por favor no me abandones

		public static void main(String[] args){

		int n1 = 1;
		int n2 = 2;
		int n3 = 3;
		int n4 = 4;
		int n5 = 5;
		int n6 = 6;
		int n7 = 7;
		int n8 = 8;
		int n9 = 9;
		int n10 = 10;

		Integer N1 = n1;
		Integer N2 = n2;
		Integer N3 = n3;
		Integer N4 = n4;
		Integer N5 = n5;
		Integer N6 = n6;
		Integer N7 = n7;
		Integer N8 = n8;
		Integer N9 = n9;
		Integer N10 = n10;

		Integer array1[] = {N1, N3, N9, N2, N7};

		Pila<Integer> pila1 = new Pila<Integer>();
		Pila<Integer> pila2 = new Pila<Integer>(array1);

		Cola<Integer> cola1 = new Cola<Integer>();
		Cola<Integer> cola2 = new Cola<Integer>(array1);

		System.out.println("Prueba pila");
		System.out.println("Agregar 1 a pila 1");
		pila1.push(N1);
		System.out.println(pila1);
		System.out.println("Agregar 4 a pila 1");
		pila1.push(N4);
		System.out.println(pila1);
		System.out.println("Agregar 8 a pila 1");
		pila1.push(N8);
		System.out.println(pila1);
		System.out.println("Agregar 5 a pila 1");
		pila1.push(N5);
		System.out.println(pila1);
		System.out.println("Llamar top() en pila 1");
		System.out.println(pila1.top());
		System.out.println("Llamar pop() en pila 1");
		System.out.println(pila1.pop());
		System.out.println(pila1);
		System.out.println("Llamar pop() en pila 1");
		System.out.println(pila1.pop());
		System.out.println(pila1);

		System.out.println("Llamar Pila(T[] elementos) con [1, 3, 9, 2, 7], se genera pila 2");
		System.out.println(pila2);
		System.out.println("Llamar top() en pila 2");
		System.out.println(pila2.top());
		System.out.println("Llamar pop() en pila 2");
		System.out.println(pila2.pop());
		System.out.println(pila2);
		System.out.println("Agregar 10 a pila 2");
		pila2.push(N10);
		System.out.println(pila2);

		System.out.println("Probar constructor de colección, se pasa cola 2 sin alterar como argumento, se genera pila 3");
		Pila<Integer> pila3 = new Pila<Integer>(cola2);
		System.out.println(pila3);
		System.out.println("Agregar 7 a pila 3");
		pila3.push(N7);
		System.out.println(pila3);
		System.out.println("Probar constructor de colección, generar pila pasando como argumento a pila 2");
		Pila<Integer> pila4 = new Pila<Integer>(pila2);
		System.out.println(pila4);


		System.out.println(" ");
		System.out.println("Prueba cola");
		System.out.println("Agregar 6 a cola 1");
		cola1.queue(N6);
		System.out.println(cola1);
		System.out.println("Agregar 7 a cola 1");
		cola1.queue(N7);
		System.out.println(cola1);
		System.out.println("Agregar 2 a cola 1");
		cola1.queue(N2);
		System.out.println(cola1);
		System.out.println("Agregar 9 a cola 1");
		cola1.queue(N9);
		System.out.println(cola1);
		System.out.println("Agregar 5 a cola 1");
		cola1.queue(N5);
		System.out.println(cola1);
		System.out.println("Llamar dequeue() en cola 1");
		System.out.println(cola1.dequeue());
		System.out.println(cola1);
		System.out.println("Llamar peek() en cola 1");
		System.out.println(cola1.peek());
		
		System.out.println("Llamar Cola(T[] elementos) con [1, 3, 9, 2, 7], se genera cola 2");
		System.out.println(cola2);
		System.out.println("Llamar dequeue() en cola 2");
		System.out.println(cola2.dequeue());
		System.out.println(cola2);
		System.out.println("Llamar dequeue() en cola 2");
		System.out.println(cola2.dequeue());
		System.out.println(cola2);
		System.out.println("Agregar 8 a cola 2");
		cola2.queue(N8);
		System.out.println(cola2);
		System.out.println("Llamar peek() en cola 2");
		System.out.println(cola2.peek());
		System.out.println("Probar constructor de colección, se pasa pila 2 como argumento, se genera cola 3");
		Cola<Integer> cola3 = new Cola<Integer>(pila2);
		System.out.println(cola3);
		System.out.println("Llamar peek() en cola 3");
		System.out.println(cola3.peek());
		System.out.println("Llamar dequeue() en cola 3");
		System.out.println(cola3.dequeue());
		System.out.println(cola3);
		System.out.println("Probar constructor de colección, generar cola pasando como argumento a cola 2");
		Cola<Integer> cola4 = new Cola<Integer>(cola2);
		System.out.println(cola4);
		System.out.println("Llamar peek() en cola 4");
		System.out.println(cola4.peek());
	}
}
