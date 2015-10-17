package objects;

/**
 * @author Qiao Yan
 * <b>Children</b> represents an immutable childrenNode of arbitrary parentNode 
 * with label representing the edge. 
 * Declare two new type variables and call two type parameters
 * @param <C>, a new type parameter representing the content of childrenNode's type
 * @param <L>, a new type parameter representing the label's type
 */

/*create a children node with a label*/
public class Children<C, L> {
	
	/** Holds the label of the edge associated with the childNode */
	private final L label;
	/** Holds the child node */
	private final Node<C> childNode;
	private static boolean CHECK_CONSTANT = false;
	
//	Representation Invariant for every Children c: 
//	childNode != null && label != null
//	
//	Abstract Function: 
//	Children, c, represents a childrenNode with label of the edge between this 
//	and some parentNode.
	
	/**
	 * @param input label of the edge
	 * @param input a child Node
	 * @effects construct a new Children
	 * @throws IllegalArgumentException if either input is null.
	 */
	public Children(L inputL, Node<C> inputK) throws IllegalArgumentException {
		if (inputL == null || inputK == null) {
			throw new IllegalArgumentException("Input is null.");
		}
		childNode = inputK;
		label = inputL;
		checkRep();
	}
	
	/**
	 * @param obj The object to be compared for equality.
	 * @return true iff 'obj' is an instance of a Children and 'this' and 'obj' 
	 * 			represent the same Children.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Children) {
		      Children<?,?> c = (Children<?,?>) obj;
		      return this.label.equals(c.getLabel()) && this.childNode.equals(c.childNode);		      
		} else {
		      return false;
		}
	}
	
	/** Standard hashCode function.
	 * @return an int that all objects equal to this will also
        return.
	 */
	@Override
	public int hashCode() {
		return label.hashCode() * 3 + childNode.hashCode() * 5;
	}
	
	/**
	 * @return the label of this edge
	 */
	public L getLabel() {
		return label;
	}
	
	/**
	 * @return the Child Node
	 */
	public Node<C> getChildNode() {
		return childNode;
	}
	
	/**
	 * @return A String representation of the expression represented by this Children
	 * in the form node(edgeLabel)
	 */
	@Override
	public String toString() {
		String result = childNode.getContent() + "("+label + ")";
		return result;
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	private void checkRep() {
		if (CHECK_CONSTANT) {
		assert (label != null && childNode != null);
		}
	}
}