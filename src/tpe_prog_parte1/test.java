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
		grafo.agregarArco(1, 2, null);
		grafo.agregarArco(2, 3, null);
		grafo.agregarArco(1, 5, null);
		grafo.agregarArco(5, 6, null);
		System.out.println(grafo.contieneVertice(1));
		System.out.println(grafo.existeArco(1, 2));
		System.out.println(grafo.existeArco(2, 1));
		System.out.println(grafo.cantidadVertices());
		System.out.println(grafo.cantidadArcos());
		System.out.println();
		/*
		Iterator<Integer> asd = grafo.obtenerAdyacentes(1);
		while(asd.hasNext()) {
			System.out.println(asd.next());
		}
		*/
		Iterator<Arco<T>> asd = grafo.obtenerArcos();
		while(asd.hasNext()) {
			System.out.println(asd.next());
		}
		
		ServicioDFS dfs = new ServicioDFS(grafo);
		System.out.println(dfs.dfsForest());
		
		ServicioBFS bfs = new ServicioBFS(grafo);
		System.out.println(bfs.bfsForest());
		
		grafo.borrarVertice(5);
		
		System.out.println(dfs.dfsForest());
		
		System.out.println(bfs.bfsForest());
		
		Iterator<Arco<T>> asd2 = grafo.obtenerArcos();
		while(asd2.hasNext()) {
			System.out.println(asd2.next());
		}
	}
}
