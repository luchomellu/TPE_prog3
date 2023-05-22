package tpe_prog_parte1;

import java.util.ArrayList;
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
		
		ServicioDFS dfs = new ServicioDFS(grafo);
		System.out.println("dfs");
		System.out.println(dfs.dfsForest());
		
		ServicioBFS bfs = new ServicioBFS(grafo);
		System.out.println("bfs");
		System.out.println(bfs.bfsForest());
		
		ServicioCaminos scam = new ServicioCaminos(grafo,1,5,0);
		System.out.println("\nCaminos de vertice 1 a 5 con limite 0");
		System.out.println(scam.caminos());
		
		ServicioCaminos scam2 = new ServicioCaminos(grafo,1,5,1);
		System.out.println("\nCaminos de vertice 1 a 5 con limite 1");
		System.out.println(scam2.caminos());
		
		ServicioCaminos scam3 = new ServicioCaminos(grafo,1,5,2);
		System.out.println("\nCaminos de vertice 1 a 5 con limite 2");
		System.out.println(scam3.caminos() + "\n");
		
		
		Iterator<Integer> adj = grafo.obtenerAdyacentes(1);
		ArrayList<Integer> arr_adj = new ArrayList<>();
		while(adj.hasNext()) {
			arr_adj.add(adj.next());
		}
		System.out.println("Adyacentes de 1 -> " + arr_adj);
		
		Iterator<Arco<T>> todos_arcos = grafo.obtenerArcos();
		ArrayList<Arco<T>> arr_arc = new ArrayList<>();
		while(todos_arcos.hasNext()) {
			arr_arc.add(todos_arcos.next());
		}
		System.out.println("Todos los arcos -> " + arr_arc + "\n");
		
		System.out.println("Existe el vertice 5? -> " + grafo.contieneVertice(5) + "\n");
		
		System.out.println("Borro el vertice 5\n");
		grafo.borrarVertice(5);
		
		System.out.println("Existe el vertice 5? -> " + grafo.contieneVertice(5) + "\n");
		
		System.out.println("dfs");
		System.out.println(dfs.dfsForest());
		System.out.println("bfs");
		System.out.println(bfs.bfsForest());
		
		System.out.println("\nElimino arco de 7 -> 78");
		grafo.borrarArco(7, 78);
		
		Iterator<Arco<T>> todos_arcos2 = grafo.obtenerArcos();
		ArrayList<Arco<T>> arr_arc2 = new ArrayList<>();
		while(todos_arcos2.hasNext()) {
			arr_arc2.add(todos_arcos2.next());
		}
		System.out.println("Todos los arcos -> " + arr_arc2);
		
		
		
		
	}
}
