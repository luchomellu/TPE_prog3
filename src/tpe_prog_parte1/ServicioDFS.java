package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ServicioDFS {
	
	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> dfsForest() {
		List<Integer> forest = new ArrayList<>();
		List<Integer> visitado = new ArrayList<>();
		
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int ver = vertices.next();
			if(!visitado.contains(ver)) {
				List<Integer> resultado = dfs(ver);
				forest.addAll(resultado);
				visitado.addAll(resultado);
			}
		}
		return forest;
	}

	private List<Integer> dfs(int vertice) {
		List<Integer> aux = new ArrayList<>();
		Stack<Integer> pila = new Stack<>();
		
		pila.push(vertice);
		while(!pila.isEmpty()) {
			int ver_actual = pila.pop();
			aux.add(ver_actual);
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(ver_actual);
			while(adyacentes.hasNext()) {
				int adj_actual = adyacentes.next();
				if(!aux.contains(adj_actual) && !pila.contains(adj_actual)){
					pila.push(adj_actual);
				}
			}
		}
		return aux;
	}
}

