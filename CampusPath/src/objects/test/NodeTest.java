package objects.test;

import static org.junit.Assert.*;

import org.junit.Test;

import objects.Node;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Node class.
 * <p>
 */

public final class NodeTest {
	
	private static final Node<String> empty = new Node<String>("");
	private static final Node<String> oneSpace = new Node<String> (" ");
	private static final Node<String> multiSpace = new Node<String> ("    ");
	private static final Node<String> singleWord = new Node<String> ("hello!");
	private static final Node<String> twoWords = new Node<String>("Hello World!");
	private static final Node<String> multiWords = new Node<String>("Hey you, World! How are you doing?");
		
	// convenience for building a node with its content
	private static Node<String> node(String content) {
		return new Node<String>(content);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Constructor
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected=IllegalArgumentException.class)
    public void expectedExceptionAddInputNull() {
		// test IllegalArgumentException when input is null
		new Node<String>(null);
	}
	
	@Test
	public void testConstructor () {
		new Node<String>("");
		new Node<String>(" ");
		new Node<String>("hello!");
		new Node<String>("Hello World!");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getContent
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test //comparing getContent() method output with correct String value
	public void testStringVSGetContent() {
		assertEquals("", empty.getContent());
		assertEquals(" ", oneSpace.getContent());
		assertEquals("hello!", singleWord.getContent());
		assertEquals("Hey you, World! How are you doing?", multiWords.getContent());
	}
	
	@Test //testing consistency of the method getContent()
	public void testGetContentSameObj() {
			assertEquals(empty.getContent(), empty.getContent());
			assertEquals(oneSpace.getContent(), oneSpace.getContent());
			assertEquals(multiSpace.getContent(), multiSpace.getContent());
			assertEquals(singleWord.getContent(), singleWord.getContent());
			assertEquals(twoWords.getContent(), twoWords.getContent());

	}
	
	@Test //comparing equal nodes' getContent() output
	public void testGetContentDiffObj() {
		assertEquals(empty.getContent(), node("").getContent());
		assertEquals(oneSpace.getContent(), node(" ").getContent());
		assertEquals(singleWord.getContent(), node("hello!").getContent());
		assertEquals(multiWords.getContent(), node("Hey you, World! How are you doing?").getContent());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	equals
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test// testing if equals method is reflexive
	public void testEqualsReflexive() {
			assertEquals(empty, empty);
			assertEquals(oneSpace, oneSpace);
			assertEquals(multiSpace, multiSpace);
			assertEquals(singleWord, singleWord);
			assertEquals(twoWords, twoWords);

	}
	
	@Test// testing that different nodes of same content is still equal
	public void testEqualsWithDiffObjects() {
		assertEquals(oneSpace, new Node<String>(" "));
		assertEquals(singleWord, new Node<String> ("hello!"));
		assertEquals(multiWords, new Node<String> ("Hey you, World! How are you doing?"));
	}
	
	@Test// testing equals is false when content of nodes are different
	public void testNotEquals() {
		assertFalse(oneSpace.equals(empty));
		assertFalse(singleWord.equals(multiSpace));
		assertFalse(multiSpace.equals(oneSpace));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	HashCode Test
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test// testing Hash Codes of the same object are equal
	public void testHashCodeSameObject() {
		Node<String> n = new Node<String>("Non sense!");
		for (int i = 0; i < 25; i++) {
			assertEquals(n.hashCode(), n.hashCode()); //Verify Non random HashCode
		}
	}
	
	@Test// testing Hash Codes of equal objects are equal
	public void testHashCodeEqualDiffObjects() {
		Node<String> n1 = new Node<String> ("Non sense!");
		Node<String> n2 = new Node<String>("Non sense!");
		assertEquals(n1.hashCode(), n2.hashCode());
	}
	
}