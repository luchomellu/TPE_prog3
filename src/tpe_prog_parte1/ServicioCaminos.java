package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
	private Grafo<?> grafo;
	int origen,destino,lim;
	private ArrayList<Arco<?>> visitados;
	
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		if(lim == 0) {
			this.lim = -1;
		}else {
			this.lim = lim;
		}
		this.visitados = new ArrayList<>();
	}
	
	public List<List<Integer>> caminos(){
		List<List<Integer>> resultado = new ArrayList<>();
		ArrayList<Integer> parcial = new ArrayList<>();
		resultado = buscarCaminos(this.origen, parcial, resultado);
		return resultado;
	}

	private List<List<Integer>> buscarCaminos(int ver, ArrayList<Integer> camino_p, List<List<Integer>> resultado){
		//agrego vertice a camino parcial
		camino_p.add(ver);
		//System.out.println("estoy parado en " + ver);
		//System.out.println("camino parcial: " + camino_p);
		//si es destino
		if (ver == this.destino) {
			//System.out.println("es destino, lo agrego a resultado");
			//agrego el camino parcial a la solucion
			resultado.add(new ArrayList<>(camino_p));
		} 
		//si el limite es 0
		if (lim == 0) {
			//sumo en 1 al limite, saco el vertice del camino parcial y retorno
			//System.out.println("limite es igual a 0");
			this.lim++;
			//System.out.println("sumo limite a " + this.lim + " y retorno");
			camino_p.remove(camino_p.size()-1);
			return resultado;
		}
		//como el limite no es 0 me traigo los adyacentes
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(ver);
		//si hay adyacentes
		while(adyacentes.hasNext()) {
			//agarro un adyacente
			int ver_aux = adyacentes.next();
			//si no lo visite por el arco
			if (!visitados.contains(this.grafo.obtenerArco(ver, ver_aux))){
				//agrego el arco a los arcos visitados
				visitados.add(this.grafo.obtenerArco(ver, ver_aux));
				//resto el limite y "salto" al adyacente para buscar caminos
				this.lim--;
				//System.out.println("salto a " + ver_aux + " por eso resto a limite, queda en " + this.lim);
				buscarCaminos(ver_aux, camino_p, resultado);
				//System.out.println("vuelvo a " + ver );
				//saco el arco a adyacente de visitados
				visitados.remove(this.grafo.obtenerArco(ver, ver_aux));
			}
		}
		//una vez recorrido todos los adyacentes, elimino el vertice del camino parcial, sumo al limite y vuelvo
		camino_p.remove(camino_p.size()-1);
		this.lim++;
		//System.out.println("me voy de " + ver + " y sumo a lim dejandolo en " + this.lim);
		return resultado;
	}

}
