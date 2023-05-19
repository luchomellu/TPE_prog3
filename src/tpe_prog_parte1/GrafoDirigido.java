package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T>{
	
	int size_arcos;
	
	private HashMap<Integer,ArrayList<Arco<T>>> grafo;
	
	public GrafoDirigido() {
		this.grafo =  new HashMap<Integer,ArrayList<Arco<T>>>();
		this.size_arcos = 0;
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		//check si existe---listo
		if(!this.contieneVertice(verticeId)) {
			grafo.put(verticeId,new ArrayList<>());
		}
		else {
			System.out.println("Vertice " + verticeId + " ya existe");
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		//borrar arcos entrantes al vertice
		ArrayList<Arco<T>> arcos_eliminar = new ArrayList<>();
		Iterator<Arco<T>> arcos = this.obtenerArcos();
		while(arcos.hasNext()) {
			Arco<T> arco = arcos.next();
			if(arco.getVerticeDestino() == verticeId) {
				//arcos.remove();
				//this.borrarArco(arco.getVerticeOrigen(), arco.getVerticeDestino());
				arcos_eliminar.add(arco);
			}
		}
		
		for (Arco<T> arco : arcos_eliminar) {
			if (arco.getVerticeDestino() == verticeId) {
				this.borrarArco(arco.getVerticeOrigen(), arco.getVerticeDestino());
		    }
		}
		 
		grafo.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		//checkear si existe vertices y arco---listo
		if(this.contieneVertice(verticeId1) && !this.existeArco(verticeId1, verticeId2)) {
			Arco<T> arco = new Arco<T>(verticeId1,verticeId2,etiqueta);
			this.grafo.get(verticeId1).add(arco);	
			this.size_arcos++;
		}
		else {
			System.out.println("Vertice " + verticeId1 + " no existe");
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		//check vertice 1--listo
		if(this.contieneVertice(verticeId1)) {
			Iterator<Arco<T>> arcos = this.obtenerArcos(verticeId1);
			while(arcos.hasNext()) {
				Arco<T> arco = arcos.next();
				if (arco.getVerticeDestino() == verticeId2) {
	                arcos.remove();
	                this.size_arcos--;
	            }
			}
		}
		else {
			System.out.println("Vertice " + verticeId1 + " no existe");
		}	
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.grafo.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		//check vert1---listo
		if(!this.contieneVertice(verticeId1)) {
			ArrayList<Arco<T>> adyacentes = this.grafo.get(verticeId1);
			for (Arco<T> arco : adyacentes) {
				if(arco.getVerticeDestino() == (verticeId2)) {
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}
		
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> adyacentes = this.grafo.get(verticeId1);
		for (Arco<T> arco : adyacentes) {
			if(arco.getVerticeDestino() == (verticeId2)) {
				return arco;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.grafo.size();
	}

	@Override
	public int cantidadArcos() {
		return this.size_arcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return grafo.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		return new AdjIterator<T>(obtenerArcos(verticeId));
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		return new ArcoIterator<T>(grafo.entrySet().iterator());
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return this.grafo.get(verticeId).iterator();
	}
}
