package objects.test;

import static org.junit.Assert.*;

import org.junit.Test;

import objects.Children;
import objects.Node;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Children class.
 * <p>
 */

public final class ChildrenTest {
	private static final Children<String,String> empty = new Children<String,String>("", new Node<String>(""));
	private static final Children<String,String> arwin = new Children<String,String>("The return of the king", new Node<String>("Arwin"));
	private static final Children<String,String> bilbo = new Children<String,String>("The Hobbit", new Node<String>("Bilbo"));
	private static final Children<String,String> gandalf = new Children<String,String>("The Hobbit", new Node<String>("Gandalf"));
	
	//private static final Children<String,String>[] childrenSet = new Children<String,String>[] {empty, arwin, bilbo, gandalf};
	
	// convenience for building a Children with label of the edge and content of the child node
	private Children<String,String> children(String label, String node) {
		return new Children<String,String>(label, new Node<String>(node));
	}
	
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	Constructor
	  ///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionConstructorInputNull() {
		// testing IllegalArgumentException throwing when input is null
		new Children<String,String>(null, new Node<String>(""));
		new Children<String,String>("", null);
		new Children<String,String>(null, null);
	}

	  @Test
	  public void testCtor() {
		  new Children<String,String>("", new Node<String>(""));
		  new Children<String,String>("The return of the king", new Node<String>("Arwin"));
		  new Children<String,String>("The Hobbit", new Node<String>("Bilbo"));
		  new Children<String,String>("The Hobbit", new Node<String>("Gandalf"));

	  }
	  
	  ///////////////////////////////////////////////////////////////////////////////////////
	  ////	Equals Test
	  ///////////////////////////////////////////////////////////////////////////////////////
	  
	  @Test // testing if equals is reflexive
	  public void testEqualsReflexive() {
			  assertEquals(empty, empty);
			  assertEquals(bilbo, bilbo);
			  assertEquals(arwin, arwin);
			  assertEquals(gandalf, gandalf);
	  }
	  
	  @Test
	  public void testEquals() {
		  assertEquals(children("The return of the king", "Arwin"), children("The return of the king", "Arwin"));
		  assertEquals(children("The Hobbit", "Bilbo"), children("The Hobbit", "Bilbo"));
		  assertEquals(children("", ""), children("", ""));
		  assertEquals(children("The Hobbit", "Gandalf"), children("The Hobbit", "Gandalf"));
	  }
	  
	  @Test
	  public void testNotEqual() {
		  assertFalse(empty.equals(arwin));
		  assertFalse(arwin.equals(bilbo));
		  assertFalse(bilbo.equals(children("The Two Towers", "Bilbo")));
		  assertFalse(children("The Hobbit", "Gandalf").equals(children("The Hobbit", "Bilbo")));
	  }
	  
	///////////////////////////////////////////////////////////////////////////////////////
	////	HashCode Test
	///////////////////////////////////////////////////////////////////////////////////////
	  
	  @Test // testing Hash Codes of the same object are equal
	  public void testHashCodeSameObject() {
		  Children<String,String> c = new Children<String,String>("The Two Towers", new Node<String>("Boromir"));
		  for (int i = 0; i < 25; i++) {
			  assertEquals(c.hashCode(), c.hashCode()); //Verify Non random HashCode
		  }
	  }
	  
	  @Test // testing Hash Codes of equal objects are equal
	  public void testHashCodeEqualDiffObjects() {
		  Children<String,String> Arwin2 = new Children<String,String>("The return of the king", new Node<String>("Arwin"));
		  assertEquals(arwin.hashCode(), Arwin2.hashCode());
	  }
	  
		///////////////////////////////////////////////////////////////////////////////////////
		////	getLabel Test
		///////////////////////////////////////////////////////////////////////////////////////
	  @Test // comparing getLabel() method output with correct String value
	  public void testStringVSGetLabel() {
		  assertEquals("Oh Yeah", children("Oh Yeah", "Dunno").getLabel());
		  assertEquals("oops", children("oops", "").getLabel());
		  assertEquals("", children("", " ").getLabel());
		  assertEquals(" ", children(" ", "The label is single space").getLabel());
	  }
	  
	  @Test // testing consistency of the method
	  public void testGetLabelSameObj() {
			  assertEquals(empty.getLabel(), empty.getLabel());
			  assertEquals(arwin.getLabel(), arwin.getLabel());
			  assertEquals(bilbo.getLabel(), bilbo.getLabel());
			  assertEquals(gandalf.getLabel(), gandalf.getLabel());
	  }
	  
	  @Test // comparing equal children's getLabel() output
	  public void testGetContentDiffObj() {
		  assertEquals(arwin.getLabel(), children("The return of the king", "Arwin").getLabel());
		  assertEquals(empty.getLabel(), children("", "").getLabel());
		  assertEquals(bilbo.getLabel(), children("The Hobbit", "Bilbo").getLabel());
		  assertEquals(gandalf.getLabel(), children("The Hobbit", "Gandalf").getLabel());
	  }
	  
	///////////////////////////////////////////////////////////////////////////////////////
	////	getChildNode Test
	///////////////////////////////////////////////////////////////////////////////////////
	  
	  @Test // testing consistency of the method
	  public void testGetChildNodeSameObj() {
			  assertEquals(empty.getChildNode(), empty.getChildNode());
			  assertEquals(arwin.getChildNode(), arwin.getChildNode());
			  assertEquals(bilbo.getChildNode(), bilbo.getChildNode());
			  assertEquals(gandalf.getChildNode(), gandalf.getChildNode());
	  }
	  
	  @Test // comparing equal children's getChildNode() output
	  public void testGetChildNodeDiffObj() {
		  assertEquals(arwin.getChildNode(), children("The return of the king", "Arwin").getChildNode());
		  assertEquals(empty.getChildNode(), children("", "").getChildNode());
		  assertEquals(bilbo.getChildNode(), children("The Two Towers", "Bilbo").getChildNode());
		  assertEquals(gandalf.getChildNode(), children("The Two Towers", "Gandalf").getChildNode());
	  }
	  
	///////////////////////////////////////////////////////////////////////////////////////
	////	toString Test
	///////////////////////////////////////////////////////////////////////////////////////
	  
	  public void testToString() {
		  assertEquals("()", empty.toString());
		  assertEquals("Arwin(The return of the king)", arwin.toString());
		  assertEquals("Bilbo(The Hobbit)", bilbo.toString());
		  assertEquals("Gandalf(The Hoobit)", gandalf.toString());
	  }	 
}