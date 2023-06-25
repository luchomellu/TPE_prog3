package tpe_prog_parte2;

import java.util.Iterator;

public class AdjIterator<T> implements Iterator<Integer>{
	
	private Iterator<Arco<T>>itr;
	
	public AdjIterator(Iterator<Arco<T>>itr) {
		this.itr = itr;
	}
	@Override
	public boolean hasNext() {
		return itr.hasNext();
	}

	@Override
	public Integer next() {
		Arco<T> aux = this.itr.next();
		return Integer.valueOf(aux.getVerticeDestino());
	}
}