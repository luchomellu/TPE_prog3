package tpe_prog_parte2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class checkBucle {
	private Grafo<?> grafo;
	private List<Integer> visitado;

	public checkBucle(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitado = new ArrayList<>();
	}
	
	public boolean dfsForest() {
		visitado.removeAll(visitado);
		List<Integer> dfs = new ArrayList<>();
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int ver = vertices.next();
			if(!visitado.contains(ver)) {
				if(isCyclicUtil(ver,-1)) {
					return true;
				}
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
		
		return false;
		
	}
	
	public boolean isCyclicUtil(int v,int parent){
		// Mark the current node as visited
		this.visitado.add(v);
		Integer i;
		
		// Recur for all the vertices
		// adjacent to this vertex
		Iterator<Integer> it = grafo.obtenerAdyacentes(v);
		while (it.hasNext()) {
			
			i = it.next();
		
			// If an adjacent is not
			// visited, then recur for that
			// adjacent
			if (!this.visitado.contains(i)) {
			   if (isCyclicUtil(i,v)) {
				   return true;
			   }
			}
		
			// If an adjacent is visited
			// and not parent of current
			// vertex, then there is a cycle.
			else if (i != parent) {
				 return true;
			}
		}
		
		return false;
	}
	
}
