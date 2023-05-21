package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ServicioBFS {
	
	private Grafo<?> grafo;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest() {
		List<Integer> visitado = new ArrayList<>();
		Queue<Integer> pila = new LinkedList<>();
		//Obtengo los vertices
		Iterator<Integer> vertices = grafo.obtenerVertices();
		System.out.println("me traigo todos los vertices");
		while(vertices.hasNext()) {
			//Tomo el primero
			int ver = vertices.next();
			System.out.println("me agarro este vertice --> " + ver);
			if(!visitado.contains(ver)) {
				//Lo agrego a los vertices visitados
				visitado.add(ver);
				pila.add(ver);
				System.out.println("meto "+ver+" a la pila y los visitados");
				System.out.println("visitados -> " + visitado);
				System.out.println("pila -> " + pila.toString());
				while(!pila.isEmpty()) {
					//Me traigo todos los adyacentes
					int ver_aux = pila.remove();
					System.out.println("saco " + ver_aux + " de la pila: " + pila);
					Iterator<Integer> adjs = grafo.obtenerAdyacentes(ver_aux);
					while(adjs.hasNext()) {
						int adj = adjs.next();
						System.out.println("agarro "+ adj +" adyacente de " + ver_aux);
						if(!visitado.contains(adj)) {
							visitado.add(adj);
							pila.add(adj);
							System.out.println("como no lo visite, lo meto en la pila y en visitados");
							System.out.println(visitado);
							System.out.println(pila);
						}else {
							System.out.println(adj + " ya fue visitado -> " + visitado);
						}
					}
				}
			}
		}
		return visitado;
	}
}
