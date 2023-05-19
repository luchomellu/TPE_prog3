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
		
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int ver = vertices.next();
			if(!visitado.contains(ver)) {
				visitado.add(ver);
				pila.push(ver);
				Iterator<Integer> aux = grafo.obtenerAdyacentes(ver);
				while(!pila.isEmpty()) {
					pila.pop();
					while(aux.hasNext()) {
						int ver_aux = aux.next();
						if(!pila.contains(ver_aux)) {
							pila.push(ver_aux);
						}
						if(!visitado.contains(ver_aux)) {
							visitado.add(ver_aux);
						}
					}
				}
			}
		}
		return visitado;
	}
}
