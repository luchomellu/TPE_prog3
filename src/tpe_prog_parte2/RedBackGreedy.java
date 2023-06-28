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
		Iterator<Arco<Integer>> itr = this.grafo.obtenerArcos();
		while(itr.hasNext()) {
			this.conjunto.add(itr.next());
		}
	}
	
	public void buscarSolucionBacktracking() {
		//ArrayList<Arco<Integer>> conjunto = new ArrayList<>();
		this.solucion_parcial = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.contador = 0;
		
		//ArrayList<Arco<Integer>> solucion = new ArrayList<>();
		//ArrayList<Arco<Integer>> solucion_parcial = new ArrayList<>();
		buscarBacktracking();
		System.out.println("");
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
			
			if(solucion.isEmpty()) {
				buscarBacktracking();
			}
			if((obtenerDistancia(this.solucion_parcial) < obtenerDistancia(this.solucion)) && !this.solucion.isEmpty()) {
				buscarBacktracking();
			}
			
			//buscarBacktracking();
			this.solucion_parcial.remove(this.solucion_parcial.size()-1);
			this.conjunto.add(0,aux);
		}
		
	}
	
	public void buscarGreedyKruskal() {
		this.solucion = new ArrayList<>();
		this.contador = 0;
		
		ArrayList<Arco<Integer>> arcosOrdenados = new ArrayList<>(this.conjunto);
		sort(arcosOrdenados , 0 , arcosOrdenados.size() - 1);
		
		while(!arcosOrdenados.isEmpty() && !esConexo(solucion)) {
			this.contador++;
			Arco<Integer> aux = arcosOrdenados.get(0);
			arcosOrdenados.remove(0);
			if(sePuedeAgregar(aux)) {
				this.solucion.add(aux);
			}
		}
		
		if(esConexo(solucion)) {
			System.out.println("");
			System.out.println("Greedy - Kruskal'ish");
			System.out.println(this.solucion);
			System.out.println(obtenerDistancia(this.solucion)+"kms");
			System.out.println("Iteraciones: " + this.contador);
		}
		else {
			System.out.println("no funco algo");
		}
	}

	private boolean sePuedeAgregar(Arco<Integer> aux) {
		
		GrafoNoDirigido<Integer> grafo_aux = new GrafoNoDirigido();
		
		//this.solucion.add(aux);
		
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
		return(!a.dfsForest());
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
		
		GrafoNoDirigido<Integer> grafo_aux = new GrafoNoDirigido();
		
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
	
	void merge(ArrayList<Arco<Integer>> arr, int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        // Create temp arrays
        //int L[] = new int[n1];
        //int R[] = new int[n2];
        ArrayList<Arco<Integer>> L = new ArrayList<>();
        ArrayList<Arco<Integer>> R = new ArrayList<>();
 
        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            //L[i] = arr[l + i];
        	L.add(arr.get(l + i));
        }
        for (int j = 0; j < n2; ++j) {
        	 //R[j] = arr[m + 1 + j];
        	 R.add(arr.get(m + 1 + j));
        } 
 
        // Merge the temp arrays
 
        // Initial indices of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L.get(i).getEtiqueta() <= R.get(j).getEtiqueta()) {
                //arr[k] = L[i];
            	arr.set(k, L.get(i));
                i++;
            }
            else {
                //arr[k] = R[j];
            	arr.set(k, R.get(j));
                j++;
            }
            k++;
        }
        // Copy remaining elements of L[] if any
        while (i < n1) {
            //arr[k] = L[i];
        	arr.set(k, L.get(i));
            i++;
            k++;
        }
        // Copy remaining elements of R[] if any
        while (j < n2) {
            //arr[k] = R[j];
        	arr.set(k, R.get(j));
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    void sort(ArrayList<Arco<Integer>> arr, int l, int r){
  
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
	
	
}
