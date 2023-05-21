package tpe_prog_parte1;

import java.util.Iterator;

public class test {
	public static <T> void main(String[] args) {
		GrafoDirigido<T> grafo = new GrafoDirigido<T>();
		grafo.agregarVertice(1);
		grafo.agregarVertice(2);
		grafo.agregarVertice(3);
		grafo.agregarVertice(4);
		grafo.agregarVertice(5);
		grafo.agregarVertice(6);
		grafo.agregarVertice(7);
		grafo.agregarVertice(9);
		grafo.agregarVertice(10);
		grafo.agregarVertice(30);
		grafo.agregarVertice(78);
		grafo.agregarArco(1, 2, null);
		grafo.agregarArco(1, 1, null);
		grafo.agregarArco(1, 10, null);
		grafo.agregarArco(10, 5, null);
		grafo.agregarArco(3, 1, null);
		grafo.agregarArco(2, 3, null);
		grafo.agregarArco(2, 4, null);
		grafo.agregarArco(4, 6, null);
		grafo.agregarArco(4, 5, null);
		grafo.agregarArco(4, 7, null);
		grafo.agregarArco(3, 5, null);
		grafo.agregarArco(5, 9, null);
		grafo.agregarArco(3, 30, null);
		grafo.agregarArco(7, 78, null);
		grafo.agregarArco(78, 5, null);
		
		/*
		Iterator<Integer> asd = grafo.obtenerAdyacentes(1);
		while(asd.hasNext()) {
			System.out.println(asd.next());
		}
		
		Iterator<Arco<T>> asd = grafo.obtenerArcos();
		while(asd.hasNext()) {
			System.out.println(asd.next());
		} */
		
		ServicioDFS dfs = new ServicioDFS(grafo);
		System.out.println("dfs");
		System.out.println(dfs.dfsForest());
		ServicioBFS bfs = new ServicioBFS(grafo);
		System.out.println("bfs");
		System.out.println(bfs.bfsForest());
		
		/*
		grafo.borrarVertice(5);
		
		System.out.println(dfs.dfsForest());
		
		System.out.println(bfs.bfsForest());
		
		Iterator<Arco<T>> asd2 = grafo.obtenerArcos();
		while(asd2.hasNext()) {
			System.out.println(asd2.next());
		}
		*/
		/*
		ServicioCaminos scam = new ServicioCaminos(grafo,1,5,0);
		System.out.println(scam.caminos());
		*/
	}
}
