package objects.test;

import static org.junit.Assert.*;

import org.junit.Test;

import objects.Coord;

public final class CoordTest {
	
	private static final Coord c0 = new Coord(0.0, 0.0);
	private static final Coord c1 = new Coord(1.1, 1.1);
	private static final Coord c2 = new Coord(1.2345, 5.4321);
	private static final Coord c3 = new Coord(0.341, 54.3);
		
	///////////////////////////////////////////////////////////////////////////////////////
	////	Constructor
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
    public void testEmptyConstructor() {
		new Coord();
	}
	
	@Test
	public void testConstructorTwoDoubles() {
		new Coord(0.0, 0.0);
		new Coord(1.1, 1.1);
		new Coord(1.2345, 1.5432);
	}
	
	@Test
	public void testConstructorFromCoord() {
		new Coord(c1);
		new Coord(c2);
		new Coord(c3);
		new Coord(c0);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getX
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testGetXEqualDouble() {
		assertTrue(c0.getX() == 0.0);
		assertTrue(c1.getX() == 1.1);
		assertTrue(c2.getX() == 1.2345);
		assertTrue(c3.getX() == 0.341);
	}
	
	@Test
	public void testGetXEqualGetX() {
		assertTrue(c0.getX() == (new Coord(c0).getX()));
		assertTrue(c1.getX() == (new Coord(c1).getX()));
		assertTrue(c2.getX() == (new Coord(c2).getX()));
		assertTrue(c3.getX() == (new Coord(c3).getX()));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getY
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	@Test
	public void testGetYEqualDouble() {
		assertTrue(c0.getY() == 0.0);
		assertTrue(c1.getY() == 1.1);
		assertTrue(c2.getY() == 5.4321);
		assertTrue(c3.getY() == 54.3);
	}
	
	@Test
	public void testGetYEqualGetY() {
		assertTrue(c0.getY() == (new Coord(c0).getY()));
		assertTrue(c1.getY() == (new Coord(c1).getY()));
		assertTrue(c2.getY() == (new Coord(c2).getY()));
		assertTrue(c3.getY() == (new Coord(c3).getY()));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Equals
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test// testing if equals method is reflexive
	public void testEqualsReflexive() {
		assertEquals(c0, c0);
		assertEquals(c1, c1);
		assertEquals(c2, c2);
		assertEquals(c3, c3);
	}
	
	@Test// testing that different nodes of same content is still equal
	public void testEqualsWithDiffObjects() {
		assertEquals(c0, new Coord(c0));
		assertEquals(c1, new Coord(c1));
		assertEquals(c2, new Coord(c2));
		assertEquals(c3, new Coord(c3));
	}
	
	@Test// testing equals is false when x or y coordinate is different
	public void testNotEquals() {
		assertFalse(c0.equals(c1));
		assertFalse(c0.equals(new Coord(0.0, 1.0)));
		assertFalse(c0.equals(new Coord(1.0, 0.0)));
		assertFalse(c2.equals(c3));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	HashCode Test
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test// testing Hash Codes of the same object are equal
	public void testHashCodeSameObject() {
		assertEquals(c0.hashCode(), c0.hashCode());
		assertEquals(c1.hashCode(), c1.hashCode());
		assertEquals(c2.hashCode(), c2.hashCode());
		assertEquals(c3.hashCode(), c3.hashCode());
	}
	
	@Test// testing Hash Codes of equal objects are equal
	public void testHashCodeEqualDiffObjects() {
		assertEquals(c0.hashCode(), (new Coord(c0)).hashCode());
		assertEquals(c1.hashCode(), (new Coord(c1)).hashCode());
		assertEquals(c2.hashCode(), (new Coord(c2)).hashCode());
		assertEquals(c3.hashCode(), (new Coord(c3)).hashCode());
	}
}