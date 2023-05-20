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
		this.lim = lim;
		this.visitados = new ArrayList<>();
	}
	
	public List<List<Integer>> caminos(){
		List<List<Integer>> resultado = new ArrayList<>();
		ArrayList<Integer> parcial = new ArrayList<>();
		resultado = buscarCaminos(this.origen, parcial, resultado);
		return resultado;
	}
	
	private List<List<Integer>> buscarCaminos(int ver, ArrayList<Integer> camino_p, List<List<Integer>> resultado){
		this.lim--;
		if (lim == 0) {
			this.lim++;
			return resultado;
		}
		camino_p.add(ver);
		System.out.println(ver + "<-- paso por aca");
		//Si es el destino
		if (ver == this.destino) {
			//Agrego el camino a la solucion
			System.out.println("Encontre el destino");
			resultado.add(new ArrayList<>(camino_p));
			System.out.println(resultado+ "<-- camino completo");
		} else {
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(ver);
			while(adyacentes.hasNext()) {
				int ver_aux = adyacentes.next();
				if (!visitados.contains(this.grafo.obtenerArco(ver, ver_aux))){
					visitados.add(this.grafo.obtenerArco(ver, ver_aux));
					buscarCaminos(ver_aux, camino_p, resultado);
					lim++;
					visitados.remove(this.grafo.obtenerArco(ver, ver_aux));
				}
			}
		}
		camino_p.remove(camino_p.size()-1);
		System.out.println(resultado+ "<-- camino cuando vuelvo");
		return resultado;
	}
}
