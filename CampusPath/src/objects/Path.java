package objects;

import java.util.*;

import objects.*;

public class Path<T> {
	
	private List<Children<T, Double>> edges;
	private double total;
	
	public Path () {
		 edges = new ArrayList<Children<T, Double>> ();
		 total = 0.0;
	}
	
	public Path(Path<T> p) {
		edges = new ArrayList<Children<T, Double>> (p.getPath());
		total = p.getTotal();
	}
	
	public void addEdge(Children<T, Double> c) {
		edges.add(c);
		total = total + c.getLabel();
	}
	
	public double getTotal() {
		return total;
	}
	
	public ArrayList<Children<T, Double>> getPath() {
		return new ArrayList<Children<T, Double>>(edges);
	}
	
	public Children<T, Double> getDest() {
		return edges.get(edges.size()-1);
	}
	
	public String toString() {
		String result = "";
		for (Children<T, Double> c: edges) {
			result = result + c.toString();
		}
		return result;
	}
}