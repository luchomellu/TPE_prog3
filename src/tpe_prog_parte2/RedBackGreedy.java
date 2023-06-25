package tpe_prog_parte2;

import java.util.ArrayList;
import java.util.Iterator;

public class RedBackGreedy {
	
	private Grafo<Integer> grafo;
	
	public RedBackGreedy(Grafo<Integer> grafo) {
		this.grafo = grafo;
		Iterator<Arco<Integer>> itr = this.grafo.obtenerArcos();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		Iterator<Integer> itr_v = this.grafo.obtenerVertices();
		while(itr_v.hasNext()) {
			System.out.println(itr_v.next());
		}
	}
	
	
}
