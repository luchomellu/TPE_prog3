package tpe_prog_parte2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RedBackGreedy {
	
	private Grafo<Integer> grafo;
	
	private ArrayList<Arco<Integer>> solucion_parcial;
	private ArrayList<Arco<Integer>> solucion;
	private ArrayList<Arco<Integer>> conjunto;
	
	private int contador;
	private int cant_vertices;
	private int mayorDistancia;
	
	public RedBackGreedy(Grafo<Integer> grafo) {
		this.grafo = grafo;
		this.solucion_parcial = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.conjunto = new ArrayList<>();
		this.mayorDistancia = 999999;
		this.contador = 0;
		
		Iterator<Arco<Integer>> itr = this.grafo.obtenerArcos();
		while(itr.hasNext()) {
			this.conjunto.add(itr.next());
		}
		
		this.cant_vertices = this.grafo.cantidadVertices();
	}
	
	public void buscarSolucionBacktracking() {
		
		this.solucion_parcial = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.contador = 0;
		Timer timer = new Timer();
		timer.start();

		buscarBacktracking();
		
		System.out.println("");
		System.out.println("Completado en: " + timer.stop() + "ms");
		System.out.println("Backtracking");
		System.out.println(this.solucion);
		System.out.println(obtenerDistancia(this.solucion)+"kms");
		System.out.println("Llamados Recursivos: " + this.contador);
	}
	
	private void buscarBacktracking() {
		if(this.conjunto.isEmpty()) {
			this.contador++;
			
			if(esConexo(this.solucion_parcial)) {
				if((obtenerDistancia(this.solucion_parcial) <= this.mayorDistancia)) {
					this.mayorDistancia = obtenerDistancia(this.solucion_parcial);
					this.solucion.clear();
					this.solucion.addAll(this.solucion_parcial);
				}
			}
		}
		else {
			Arco<Integer> aux = this.conjunto.get(0);
			//sin el arco aux en solucion
			this.conjunto.remove(this.conjunto.indexOf(aux));
			
			//poda
			if(obtenerDistancia(this.solucion_parcial) <= this.mayorDistancia && this.solucion_parcial.size() <= this.cant_vertices-1) {
				buscarBacktracking();
			}
			this.conjunto.add(0,aux);
			
			//con el arco aux en solucion
			this.solucion_parcial.add(aux);
			this.conjunto.remove(this.conjunto.indexOf(aux));
			//poda
			if(obtenerDistancia(this.solucion_parcial) <= this.mayorDistancia && this.solucion_parcial.size() <= this.cant_vertices-1) {
				buscarBacktracking();
			}

			//buscarBacktracking();
			this.solucion_parcial.remove(this.solucion_parcial.size()-1);
			this.conjunto.add(0,aux);
		}
		
	}
	
	public void buscarGreedy() {
		this.solucion = new ArrayList<>();
		this.contador = 0;
		
		Timer timer = new Timer();
		timer.start();
		
		ArrayList<Arco<Integer>> arcosOrdenados = new ArrayList<>(this.conjunto);
		Collections.sort(arcosOrdenados,new ComparadorEtiquetas());
		
		while(!arcosOrdenados.isEmpty() && !esConexo(solucion)) {
			this.contador++;
			Arco<Integer> aux = arcosOrdenados.get(0);
			arcosOrdenados.remove(0);
			if(sePuedeAgregar(aux)) {
				this.solucion.add(aux);
			}
		}
		
		if(esConexo(solucion)) {
			this.mayorDistancia = obtenerDistancia(this.solucion);
			System.out.println("");
			System.out.println("Completado en: " + timer.stop() + "ms");
			System.out.println("Greedy");
			System.out.println(this.solucion);
			System.out.println(obtenerDistancia(this.solucion)+"kms");
			System.out.println("Iteraciones: " + this.contador);
		}
		else {
			System.out.println("No se encontro solucion");
		}
	}

	private boolean sePuedeAgregar(Arco<Integer> aux) {
		
		GrafoNoDirigido<Integer> grafo_aux = new GrafoNoDirigido<Integer>();
		
		for(Arco<Integer> a : this.solucion) {
			grafo_aux.agregarVertice(a.getVerticeOrigen());
			grafo_aux.agregarVertice(a.getVerticeDestino());
			grafo_aux.agregarArco(a.getVerticeOrigen(), a.getVerticeDestino(), a.getEtiqueta());
		}
		
		if(grafo_aux.existeArco(aux.getVerticeOrigen(), aux.getVerticeDestino())) {
			return false;
		}
		
		grafo_aux.agregarVertice(aux.getVerticeOrigen());
		grafo_aux.agregarVertice(aux.getVerticeDestino());
		grafo_aux.agregarArco(aux.getVerticeOrigen(), aux.getVerticeDestino(), aux.getEtiqueta());
		
		checkBucle a = new checkBucle(grafo_aux);
		return(!a.check());
	}

	private int obtenerDistancia(ArrayList<Arco<Integer>> solucion_parcial) {
		int suma = 0;
		for(Arco<Integer> a : solucion_parcial) {
			suma += a.getEtiqueta();
		}
		return suma;
	}

	
	private boolean esConexo(ArrayList<Arco<Integer>> solucion_parcial) {
		
		if(solucion_parcial.isEmpty()) {
			return false;
		}
		
		GrafoNoDirigido<Integer> grafo_aux = new GrafoNoDirigido<Integer>();
		
		for(Arco<Integer> a : solucion_parcial) {
			grafo_aux.agregarVertice(a.getVerticeOrigen());
			grafo_aux.agregarVertice(a.getVerticeDestino());
			grafo_aux.agregarArco(a.getVerticeOrigen(), a.getVerticeDestino(), a.getEtiqueta());
		}
		
		CheckGrafoConexo claseCheckeo = new CheckGrafoConexo(grafo_aux);
		List<Integer> resultado = claseCheckeo.check();
		
		Iterator<Integer> itr = this.grafo.obtenerVertices();
		
		while(itr.hasNext()) {
			if(!resultado.contains(itr.next())) {
				return false;
			}
		}
		
		return true;
	}
	
}
