package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ServicioBFS {
	
	private Grafo<?> grafo;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest() {
		List<Integer> visitado = new ArrayList<>();
		Stack<Integer> pila = new Stack<>();
		//Obtengo los vertices
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			//Tomo el primero
			int ver = vertices.next();
			if(!visitado.contains(ver)) {
				//Lo agrego a los vertices visitados
				visitado.add(ver);
				pila.push(ver);
				while(!pila.isEmpty()) {
					//Me traigo todos los adyacentes
					int ver_aux = pila.pop();
					Iterator<Integer> adjs = grafo.obtenerAdyacentes(ver_aux);
					
					while(adjs.hasNext()) {
						int adj = adjs.next();
						if(!visitado.contains(adj)) {
							visitado.add(adj);
							pila.push(adj);
						}
					}
				}
			}
		}
		return visitado;
	}
}
