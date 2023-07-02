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
	
	public boolean check() {
		Iterator<Integer> vertices = grafo.obtenerVertices();
		
		int ver = vertices.next();
		if(!visitado.contains(ver)) {
			if(hayBucle(ver,-1)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hayBucle(int v,int parent){
		this.visitado.add(v);
		Integer adj;
		
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
		while (adyacentes.hasNext()) {
			
			adj = adyacentes.next();
			if (!this.visitado.contains(adj)) {
				if (hayBucle(adj,v)) {
					return true;
			   }
			}
			else if (adj != parent) {
				return true;
			}
		}
		return false;
	}
	
}
