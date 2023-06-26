package tpe_prog_parte2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedBackGreedy {
	
	private Grafo<Integer> grafo;
	private ArrayList<Arco<Integer>> solucion_parcial;
	private ArrayList<Arco<Integer>> solucion;
	private ArrayList<Arco<Integer>> conjunto;
	private int contador;
	
	public RedBackGreedy(Grafo<Integer> grafo) {
		this.grafo = grafo;
		this.solucion_parcial = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.conjunto = new ArrayList<>();
		this.contador = 0;
	}
	
	public void buscarSolucionBacktracking() {
		//ArrayList<Arco<Integer>> conjunto = new ArrayList<>();
		
		Iterator<Arco<Integer>> itr = this.grafo.obtenerArcos();
		while(itr.hasNext()) {
			this.conjunto.add(itr.next());
		}
		
		//ArrayList<Arco<Integer>> solucion = new ArrayList<>();
		//ArrayList<Arco<Integer>> solucion_parcial = new ArrayList<>();
		buscarBacktracking();
		System.out.println("Backtracking");
		System.out.println(this.solucion);
		System.out.println(obtenerDistancia(this.solucion)+"kms");
		System.out.println("Iteraciones: " + this.contador);
	}
	
	private void buscarBacktracking() {
		//System.out.println(this.contador);
		//System.out.println(this.solucion_parcial + " <-- solucion parcial");
		//System.out.println(this.solucion + " <-- solucion");
		if(this.conjunto.isEmpty()) {
			this.contador++;
			if(esConexo(this.solucion_parcial)) {
				//System.out.println("es conexo");
				if(solucion.isEmpty()) {
					this.solucion = new ArrayList<Arco<Integer>>(this.solucion_parcial);
				}
				else if((obtenerDistancia(this.solucion_parcial) < obtenerDistancia(this.solucion)) && !this.solucion.isEmpty()) {
					//System.out.println(this.solucion_parcial + " <-- nueva mejor solucion");
					this.solucion = new ArrayList<Arco<Integer>>(this.solucion_parcial);
				}
				
			}
		}
		else {
			Arco<Integer> aux = this.conjunto.get(0);
			//sin el arco aux en solucion
			this.conjunto.remove(this.conjunto.indexOf(aux));
			buscarBacktracking();
			this.conjunto.add(0,aux);
			
			//con el arco aux en solucion
			this.solucion_parcial.add(aux);
			this.conjunto.remove(this.conjunto.indexOf(aux));
			buscarBacktracking();
			this.solucion_parcial.remove(this.solucion_parcial.size()-1);
			this.conjunto.add(0,aux);
		}
		
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
		GrafoNoDirigido grafo_aux = new GrafoNoDirigido();
		for(Arco<Integer> a : solucion_parcial) {
			grafo_aux.agregarVertice(a.getVerticeOrigen());
			grafo_aux.agregarVertice(a.getVerticeDestino());
			grafo_aux.agregarArco(a.getVerticeOrigen(), a.getVerticeDestino(), a.getEtiqueta());
		}
		CheckGrafoConexo lol = new CheckGrafoConexo(grafo_aux);
		List<Integer> resultado = lol.check();
		Iterator<Integer> itr = this.grafo.obtenerVertices();
		while(itr.hasNext()) {
			if(!resultado.contains(itr.next())) {
				return false;
			}
		}
		return true;
	}
	
}
