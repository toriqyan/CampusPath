package objects;

/**
 * 
 * @author toriqyan
 * 
 * <b>Node</b> represents an immutable single node carrying certain information 
 * client wants to include. 
 * Declare a new type variable and call a type parameter 
 * which represents the information type
 * @param <C>, a new type parameter
 */
public class Node<C> {
	
	/** Holds the content of the node */
	private static boolean CHECK_CONSTANT = false;
	private final C content;
	
//  Representation Invariant for every Node n: 
//	content != null
//  
//  Abstract Function: 
//	Node, n, represents a single node with a String content.
	
	/**
	 * @param input The value of content
	 * @effects construct a new Node
	 * @throws IllegalArgumentException if input is null
	 */
	public Node (C input) throws IllegalArgumentException {
		if (input == null) {
			throw new IllegalArgumentException("input is null");
		}
		content = input;
		checkRep();
	}

	/**
	 * @return the String carried by the Node
	 */
	public C getContent() {
		return content;
	}
	
	/** Standard equality operation.
	 * @param obj The object to be compared for equality.
	 * @return true iff 'obj' is an instance of a Node and 'this' and 'obj' 
	 * represent the same Node. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			Node<?> n = (Node<?>) obj;
			return this.content.equals(n.getContent());
		}
		return false;
		
	}
	
	/** Standard hashCode function.
	 * @return an int that all objects equal to this will also
        return.
	 */
	@Override
	public int hashCode() {
		return content.hashCode();
	}
	
	/**
	 * check if rep inv holds
	 */
	private void checkRep() {
		if(CHECK_CONSTANT) {
			assert (content != null);
		}
	}
	
}