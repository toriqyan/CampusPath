package objects;

import java.util.*;


/**
 * @author Qiao Yan
 * <b>Graph</b> represents a mutable directed labeled multigraph of nodes and edges.
 * Declare two new type variables and call two type parameters
 * @param <C>, a new type parameter representing the content of Node's type
 * @param <L>, a new type parameter representing the label's type
 */
public class Graph<C, L> {
	
	/**	Holds all the nodes and their children */
	private Map<Node<C>, HashSet<Children<C,L>>> edges;
	private static boolean CHECK_CONSTANT = false;
	
	// Abstract Function: 
	// Graph, g, represents a directed labeled multigraph of nodes and edges. Edges 
	// map has all the nodes as key and their childrenNode in a ArrayList as value. 
	// 
	// Representation Invariant for every Graph p:
	// edges != null
	
	/**
	 * @effects construct a new empty Graph
	 */
	public Graph() {
		edges = new HashMap<Node<C>, HashSet<Children<C,L>>> ();
		checkRep();
	}
	
	/**
	 * @return the number of nodes in the graph
	 */
	public int size() {
		return edges.keySet().size();
	}
	
	/**
	 * @param input a Node
	 * @modifies this
	 * @effects add a Node into the graph if it is not in the graph yet
	 * @throws IllegalArgumentException if input is null
	 */
	public void addNode(Node<C> input) throws IllegalArgumentException {
		if (input == null) {
			throw new IllegalArgumentException("input is null");
		}
		if (!edges.containsKey(input)) {
			edges.put(input, new HashSet<Children<C,L>> ());
		}
		checkRep();
	}
	
	/**
	 * @param input a parent Node
	 * @param input a Children
	 * @modifies this
	 * @effects add a child to the specific parent node if the same edge doesn't already exist. 
	 * If the parent node or the child node is not in the graph yet, add it in the graph
	 * @throws: IllegalArgumentException if parent or child is null
	 */
	public void addChildren(Node<C> parent, Children<C, L> child) throws IllegalArgumentException {
		if (parent == null || child == null) {
			throw new IllegalArgumentException("invalid input");
		}
		if (!edges.containsKey(parent)) {
			this.addNode(parent);
		}
		if (!edges.get(parent).contains(child)) {
			edges.get(parent).add(child);
		}
		if (!edges.containsKey(child.getChildNode())) {
			this.addNode(child.getChildNode());
		}
		checkRep();
	}
	
	/**
	 * @return A List of nodes contained in the graph
	 */
	public ArrayList<Node<C>> getNodes() {
		ArrayList<Node<C>> result = new ArrayList<Node<C>> ();
		for(Node<C> n: edges.keySet()) {
			result.add(n);
		}
		return result;
	}
	
	/**
	 * @param a parent node
	 * @return  A String representation of a list of children of a given parent in the form: 
	 * " firstNode(someEdge) secondNode(edgeA) secondNode(edgeB) secondEdge(edgeC) thirdNode(anotherEdge)" 
	 * in alphabetic order by node name and secondarily by edge label if there're children. 
	 * If not, return an empty String
	 * @throws: IllegalArgumentException if parent is null or parent doesn't exist in the graph
	 */
	public ArrayList<Children<C,L>> getChildren(Node<C> parent) throws IllegalArgumentException {
		if (parent == null || !edges.containsKey(parent)) {
			throw new IllegalArgumentException ("invalid input: parent doesn't exist or is null.");
		}
		ArrayList<Children<C,L>> result = new ArrayList<Children<C,L>> ();
		Iterator<Children<C,L>> itr = edges.get(parent).iterator();
		while(itr.hasNext()) {
			result.add(itr.next());
		}
		return result;
	}
	
	//check if rep inv still holds
	private void checkRep() {
		if (CHECK_CONSTANT) {
		assert(edges != null);
		}
	}
	
}