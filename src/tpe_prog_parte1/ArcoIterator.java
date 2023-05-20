package tpe_prog_parte1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class ArcoIterator<T> implements Iterator<Arco<T>>{
	//revisar si se puede hacer mas simple
	private Iterator<Entry<Integer, ArrayList<Arco<T>>>> itr_vertices;
	 private Iterator<Arco<T>> arrActual;
	
	public ArcoIterator(Iterator<Entry<Integer, ArrayList<Arco<T>>>> iterator) {
		this.itr_vertices = iterator; 
		if(iterator.hasNext()) {
			this.arrActual = this.itr_vertices.next().getValue().iterator();
		}
	}
	@Override
	public boolean hasNext() {
		if(this.arrActual != null && this.arrActual.hasNext()) {
			return true;
		}
		else if(itr_vertices.hasNext()) {
			this.arrActual = this.itr_vertices.next().getValue().iterator();
			return hasNext();
		}
		return false;
	}

	@Override
	public Arco<T> next() {
		return this.arrActual.next();
	}
}