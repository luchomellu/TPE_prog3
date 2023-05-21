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
		
		/*List<Integer> forest = new ArrayList<>();
		//Creo el iterador de vertices del grafo
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int ver = vertices.next();
			//Si no esta visitado
			if(!visitado.contains(ver)) {
				//Creo una lista resultado
				List<Integer> resultado = dfs(ver);
				forest.addAll(resultado);
				visitado.addAll(resultado);
			}
		}
		return forest;
	}

	private List<Integer> dfs(int vertice) {
		
		/*List<Integer> aux = new ArrayList<>();
		Stack<Integer> pila = new Stack<>();
		//Agrego el vertice que viene como parametro a la pila
		pila.push(vertice);
		while(!pila.isEmpty()) {
			//Saco el vertice del tope
			int ver_actual = pila.pop();
			//Lo agrego al ArrayList
			aux.add(ver_actual);
			//Me traigo todos los adyacentes
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(ver_actual);
			while(adyacentes.hasNext()) {
				int adj_actual = adyacentes.next();
				//Si no formaba parte de la solucion, la pila o los visitados se agrega a la pila
				if(!aux.contains(adj_actual) && !pila.contains(adj_actual) && !this.visitado.contains(adj_actual)){
					pila.push(adj_actual);
				}
			}
		}
		return aux;*/
	}
