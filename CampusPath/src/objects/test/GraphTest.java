package objects.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import objects.*;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Graph class.
 * <p>
 */

public final class GraphTest {
	
	private static final Node<String> bilbo = new Node<String>("Bilbo");
	private static final Node<String> gandalf = new Node<String>("Gandalf");
	private static final Node<String> arwin = new Node<String>("Arwin");
	private static final Node<String> samwise = new Node<String>("Samwise");
	private static final Children<String,String> samwiseC = new Children<String,String>("The Two Towers", samwise);
	private static final Children<String,String> gandalfC = new Children<String,String>("The Hobbit", gandalf);
	private static final Children<String,String> arwinC = new Children<String,String>("The Two Towers", arwin);
	private static final Children<String,String> bilboCT = new Children<String,String>("The Two Towers", bilbo);
	private static final Children<String,String> bilboCH = new Children<String,String>("The Hobbit", bilbo);
	
	
	
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	Constructor
	  ///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCtor() {
		Graph<String, String> g = new Graph<String,String>();
		assertEquals(g.size(), 0);
	}
	
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	addNode Test
	  ///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionAddNodeInputNull() {
		// testing IllegalArgumentException throwing when input is null
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(null);
	}
	
	@Test // testing add node into an empty graph
	public void testAddToEmpty() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(arwin);
		assertTrue(graph.getNodes().contains(arwin));
	}
	
	@Test // testing add node into a nonempty graph
	public void testAddToNonEmpty() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(bilbo);
		assertTrue(graph.getNodes().contains(bilbo));
		graph.addNode(gandalf);
		assertTrue(graph.getNodes().contains(gandalf));
	}
	
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	Size Test
	  ///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testSize() {
		Graph<String,String> graph = new Graph<String,String>();
		assertEquals(graph.size(), 0);
		graph.addNode(bilbo);
		assertEquals(graph.size(), 1);
		graph.addNode(gandalf);
		assertEquals(graph.size(), 2);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	AddChildren Test
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionAddChildrenToNull() {
		// testing IllegalArgumentException throwing when parent node input is null
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(null, bilboCT);
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionAddNullToParent() {
		// testing IllegalArgumentException throwing when child node input is null
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(bilbo, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionAddNullToNull() {
		// testing IllegalArgumentException throwing when both inputs are null
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(null, null);
	}
	
	@Test
	public void testAddExistingFirstChildToExistingParent() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(bilbo);
		graph.addNode(gandalfC.getChildNode());
		graph.addChildren(bilbo, gandalfC);
		assertTrue(graph.getChildren(bilbo).contains(gandalfC));
		graph.addNode(arwin);
		graph.addNode(bilboCT.getChildNode());
		graph.addChildren(arwin, bilboCT);
		assertTrue(graph.getChildren(arwin).contains(bilboCT));
	}
	
	@Test
	public void testAddNonExistingFirstChildToExistingParent() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(bilbo);
		graph.addChildren(bilbo, gandalfC);
		assertTrue(graph.getNodes().contains(gandalfC.getChildNode()));
		assertTrue(graph.getChildren(bilbo).contains(gandalfC));
		graph.addNode(arwin);
		graph.addChildren(arwin, bilboCT);
		assertTrue(graph.getNodes().contains(bilboCT.getChildNode()));
		assertTrue(graph.getChildren(arwin).contains(bilboCT));
	}
	
	@Test 
	public void testAddNonExistingFirstChildToNonExistingParent() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(bilbo, gandalfC);
		assertTrue(graph.getNodes().contains(bilbo));
		assertTrue(graph.getNodes().contains(gandalfC.getChildNode()));
		assertTrue(graph.getChildren(bilbo).contains(gandalfC));
		graph.addChildren(arwin, bilboCT);
		assertTrue(graph.getNodes().contains(arwin));
		assertTrue(graph.getNodes().contains(bilboCT.getChildNode()));
		assertTrue(graph.getChildren(arwin).contains(bilboCT));
	}
	
	@Test
	public void testAddNonExistingMultiChildrenToNonExistingParent() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(bilbo, arwinC);
		graph.addChildren(bilbo, gandalfC);
		graph.addChildren(bilbo, samwiseC);
		assertTrue(graph.getNodes().contains(bilbo));
		assertTrue(graph.getNodes().contains(arwinC.getChildNode()));
		assertTrue(graph.getNodes().contains(gandalfC.getChildNode()));
		assertTrue(graph.getNodes().contains(samwiseC.getChildNode()));
		assertTrue(graph.getChildren(bilbo).contains(arwinC));
		assertTrue(graph.getChildren(bilbo).contains(gandalfC));
		assertTrue(graph.getChildren(bilbo).contains(samwiseC));
	}
	
	@Test // testing add same children node to the same parents with different label
	public void testAddSameChildDiffLabelToSameParent() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(gandalf, bilboCH);
		graph.addChildren(gandalf, bilboCT);
		assertTrue(graph.getNodes().contains(gandalf));
		assertTrue(graph.getNodes().contains(bilboCH.getChildNode()));
		assertTrue(graph.getNodes().contains(bilboCT.getChildNode()));
		assertTrue(graph.getChildren(gandalf).contains(bilboCH));
		assertTrue(graph.getChildren(gandalf).contains(bilboCT));
	}
	
	@Test // testing adding n1 to n2 while n1 is n2's parent
	public void testParentChildCrossOver() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(arwin, samwiseC);
		graph.addChildren(samwise, arwinC);
		assertTrue(graph.getNodes().contains(arwin));
		assertTrue(graph.getNodes().contains(samwise));
		assertTrue(graph.getChildren(arwin).contains(samwiseC));
		assertTrue(graph.getChildren(samwise).contains(arwinC));
	}
	
	@Test // testing adding n1 to n1
	public void testAddNodeAsItsOwnChild() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(arwin, arwinC);
		assertTrue(graph.getNodes().contains(arwin));
		assertTrue(graph.getChildren(arwin).contains(arwinC));
	}
	
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	getNodes test
	  ///////////////////////////////////////////////////////////////////////////////////////
	
	@Test // testing getting an empty list of nodes from an empty graph
	public void testEmptyGraph() {
		Graph<String,String> graph = new Graph<String,String>();
		assertEquals(graph.getNodes(), new ArrayList<Node<String>> ());
	}
	
	@Test // testing getting a list of node from a simple graph with only one nodes
	public void testSimpleGraph() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(bilbo);
		assertTrue(graph.getNodes().contains(bilbo));
	}
	
	@Test // testing getting a list of nodes from a graph with multiple nodes
	public void testMultiGraph() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(bilbo);
		graph.addNode(arwin);
		graph.addNode(gandalf);
		assertTrue(graph.getNodes().contains(bilbo));
		assertTrue(graph.getNodes().contains(arwin));
		assertTrue(graph.getNodes().contains(gandalf));
	}
	
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	getChildren test
	  ///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionInputNull() {
		// testing IllegalArgumentException throwing when input is null
		Graph<String,String> graph = new Graph<String,String>();
		graph.getChildren(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionNonExistingParent() {
		// testing IllegalArgumentException throwing when target parent is not existed
		Graph<String,String> graph = new Graph<String,String>();
		graph.getChildren(arwin);
	}
	
	@Test // testing getting an empty list when the target parent has no children
	public void testNoChildren() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addNode(bilbo);
		assertEquals(graph.getChildren(bilbo).size(), 0);
		graph.addNode(arwin);
		assertEquals(graph.getChildren(arwin).size(), 0);
		graph.addNode(gandalf);
		assertEquals(graph.getChildren(gandalf).size(), 0);
	}
	
	@Test // testing getting a list of different children when the target parent has no duplicated children
	public void testNoDuplicateChildren() {
		Graph<String,String> graph = new Graph<String,String>();
		graph.addChildren(bilbo, samwiseC);
		assertTrue(graph.getChildren(bilbo).contains(samwiseC));
		graph.addChildren(bilbo, arwinC);
		assertTrue(graph.getChildren(bilbo).contains(arwinC));
		graph.addChildren(bilbo, gandalfC);
		assertTrue(graph.getChildren(bilbo).contains(gandalfC));
	}
	
	@Test // testing getting a list of children when the target parent has duplicated children
	public void testSameChildrenDiffLable() {
		Graph<String,String> graph = new Graph<String,String>();		
		graph.addChildren(gandalf, bilboCH);
		assertTrue(graph.getChildren(gandalf).contains(bilboCH));
		graph.addChildren(gandalf, arwinC);
		assertTrue(graph.getChildren(gandalf).contains(arwinC));
		graph.addChildren(gandalf, bilboCT);
		assertTrue(graph.getChildren(gandalf).contains(bilboCT));
		graph.addChildren(gandalf, samwiseC);
		assertTrue(graph.getChildren(gandalf).contains(samwiseC));
	}
}