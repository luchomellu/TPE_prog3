package tpe_prog_parte2;

import java.util.Comparator;

public class ComparadorEtiquetas implements Comparator<Arco<Integer>>{

	@Override
	public int compare(Arco<Integer> o1, Arco<Integer> o2) {
		return o1.getEtiqueta().compareTo(o2.getEtiqueta());
	}

}
