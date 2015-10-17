package pathDrawer;

import objects.*;

import java.util.*;

public class PathFinder {
	/**
	 * find the least-cost path between the provided depart node and 
	 * destination node in the provided weighted graph
	 * @param graph, where the path is being mapped
	 * @param depart, the starting node
	 * @param dest, the destination node
	 * @return a list of Children. The first element of the list is 
	 * the start node with empty label. The other single element of the list
	 * is node with nonempty label representing the edge it forms with its previous 
	 * node in path; return an empty list if there's no path.
	 */
	public static ArrayList<Children<Coord,Double>> findPath(Node<Coord> depart, Node<Coord> dest, Graph<Coord,Double> graph) {
		
		PriorityQueue<Path<Coord>> active  = new PriorityQueue<Path<Coord>> (graph.size(), new Comparator<Path<Coord>> () {
			@Override
			public int compare(Path<Coord> o1, Path<Coord> o2) {
				if (o1.getTotal() > o2.getTotal()) {
					return 1;
				} else if (o1.getTotal() < o2.getTotal()) {
					return -1;
				}
				return 0;
			}
		});
		List<Node<Coord>> known = new ArrayList<Node<Coord>> ();
		Path<Coord> start = new Path<Coord>();
		
		// Add a path from start to itself to active
		start.addEdge(new Children<Coord, Double>(0.0, depart));
		active.add(start);
		
		while (!active.isEmpty()) {
			Path<Coord> minPath = active.remove();
			Node<Coord> minDest = minPath.getDest().getChildNode();
			if (minDest.equals(dest)) {
				return minPath.getPath();
			}
			if (known.contains(minDest)) {
					continue;
			}
			for (Children<Coord, Double> cd: graph.getChildren(minDest)) {
				if (!known.contains(cd)) {
					Path<Coord> newPath = new Path<Coord>(minPath);
					newPath.addEdge(cd);
					active.add(newPath);
				}
				
			}
			known.add(minDest);
		
		}
		return new ArrayList<Children<Coord,Double>> ();
	}
	
}