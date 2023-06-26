package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ServicioDFS {
	
	private Grafo<?> grafo;
	private List<Integer> visitado;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitado = new ArrayList<>();
	}
	
	public List<Integer> dfsForest() {
		visitado.removeAll(visitado);
		List<Integer> dfs = new ArrayList<>();
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int ver = vertices.next();
			if(!visitado.contains(ver)) {
				Stack<Integer> pila = new Stack<>();
				//Agrego el vertice que viene como parametro a la pila
				pila.push(ver);
				while(!pila.isEmpty()) {
					//Saco el vertice del tope
					int ver_actual = pila.pop();
					//Lo agrego al ArrayList
					dfs.add(ver_actual);
					this.visitado.add(ver_actual);
					//Me traigo todos los adyacentes
					Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(ver_actual);
					while(adyacentes.hasNext()) {
						int adj_actual = adyacentes.next();
						//Si no formaba parte de la solucion, la pila o los visitados se agrega a la pila
						if(!dfs.contains(adj_actual) && !pila.contains(adj_actual) && !this.visitado.contains(adj_actual)){
							pila.push(adj_actual);
						}
					}
				}
			}
		}
		
		return dfs;
		
	}
}
