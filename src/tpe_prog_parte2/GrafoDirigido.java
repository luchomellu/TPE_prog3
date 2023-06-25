package tpe_prog_parte2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T>{
	
	private int size_arcos;
	
	private HashMap<Integer,ArrayList<Arco<T>>> grafo;
	
	public GrafoDirigido() {
		this.grafo =  new HashMap<Integer,ArrayList<Arco<T>>>();
		this.size_arcos = 0;
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		/*
		 *  Complejidad: O(1) porque es la complejidad de contieneVertice que es O(1) 
		 *  con la complejidad del put del hashmap que es O(1)
		 *  */
		//checkeo si existe el vertice
		if(!this.contieneVertice(verticeId)) {
			//si no existe lo meto en la estructura
			grafo.put(verticeId,new ArrayList<>());
		}
		else {
			System.out.println("Vertice " + verticeId + " ya existe");
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		/**
		* Complejidad: O(n*m) donde N es el total de vertices , M es el total de arcos y el producto 
		* de estos dos es porque en el peor de los casos recorremos todos los vertices y todos los arcos para 
		* encontrar un arco con destino al vertice a borrar.
		*/
		Iterator<Integer> vertices = this.obtenerVertices();
		while(vertices.hasNext()) {
			int ver = vertices.next();
			ArrayList<Arco<T>> arcos = this.grafo.get(ver);
			if(!arcos.isEmpty()) {
				for(int i = 0 ; i < arcos.size() ; i++) {
					Arco<T> aux = arcos.get(i);
					if(aux.getVerticeDestino() == verticeId) {
						arcos.remove(aux);
						i = arcos.size();
					}
				}
			}
		}
		grafo.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		/**
		* Complejidad: O(n) Donde se accede de manera constante a la memoria una vez por cada
		* busqueda de vertice, la complejidad de existeArco y el acceso a memoria para incorporar el nuevo arco
		*/
		//checkeo si existe el vertice y no existe el arco
		if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2) && !this.existeArco(verticeId1, verticeId2)) {
			//creo el arco
			Arco<T> arco = new Arco<T>(verticeId1,verticeId2,etiqueta);
			//agarro el vertice origen y le agrego el arco a su arraylist de arcos
			this.grafo.get(verticeId1).add(arco);	
			this.size_arcos++;
		}
		else {
			System.out.println("Vertice " + verticeId1 + " no existe");
		}
	}

	@Override
	/**
	* Complejidad: O(n) Donde n constituye la iteracion de los arcos del vertice y el valor constante de
	* contieneVertice
	* .
	*/
	public void borrarArco(int verticeId1, int verticeId2) {
		//checkeo que exista el vertice 1
		if(this.contieneVertice(verticeId1)) {
			//me traigo un iterador para iterar sobre los arcos
			Iterator<Arco<T>> arcos = this.obtenerArcos(verticeId1);
			while(arcos.hasNext()) {
				Arco<T> arco = arcos.next();
				//si es el arco que yo busco
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
		/**
		* Complejidad: O(1) 1 porque el containsKey accede directamente a la direccion de memoria
		*/
		return this.grafo.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		/**
		* Complejidad: O(n) donde es la constante de checkear si existe el vertice 
		* y la totalidad de arcos del verticeId1 que se recorre
		*/
		//check vert1---listo
		if(!this.contieneVertice(verticeId1)) {
			Iterator<Arco<T>> arcos = this.obtenerArcos(verticeId1);
			while(arcos.hasNext()) {
				if(arcos.next().getVerticeDestino() == (verticeId2)) {
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
		/**
		* Complejidad: O(n) Donde n es la cantidad de arcos que tiene el vertice origen
		* .
		*/
		Iterator<Arco<T>> arcos = this.obtenerArcos(verticeId1);
		while(arcos.hasNext()) {
			Arco<T> arco = arcos.next();
			if(arco.getVerticeDestino() == verticeId2) {
				return arco;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		/**
		* Complejidad: O(1) Es una constante de 1
		* .
		*/
		return this.grafo.size();
	}

	@Override
	public int cantidadArcos() {
		/**
		* Complejidad: O(1) Es una constante de 1 por el acceso a memoria
		* .
		*/
		return this.size_arcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		/**
		* Complejidad: O(1) Es una constante de 1 para la creacion del iterador
		* .
		*/
		return grafo.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		/**
		* Complejidad: O(1) Es una constante de 1 para la creacion de cada uno de los iteradores
		* .
		*/
		return new AdjIterator<T>(obtenerArcos(verticeId));
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		/**
		* Complejidad: O(1) Es una constante de 1 para la creacion de cada uno de los iteradores
		* .
		*/
		return new ArcoIterator<T>(grafo.entrySet().iterator());
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		/**
		* Complejidad: O(1) Es una constante de 1 para la creacion del iterador
		* .
		*/
		return this.grafo.get(verticeId).iterator();
	}
}
