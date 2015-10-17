package objects;

// creates a coordination with x coordinate and y coordinate
public class Coord {
	
	/* holds x coordinate*/
	private double x;
	/* holds y coordinate*/
	private double y;
	
	//	Representation Invariant for every Coord c: 
	//  x != null && y != null
	//  
	//  Abstract Function: 
	//  Coord c represents a point with double x and y representing 
	//  x and y coordinates
	
	/**
	 * @effects construct a new Coord at origin(0.0, 0.0)
	 */
	public Coord() {
		x = 0.0;
		y = 0.0;
	}
	
	/**
	 * @param x1, the x coordinate
	 * @param y1, the y coordinate
	 * @effects construct a new Coord with provided coordinates
	 */
	public Coord(double x1, double y1) {
		x = x1;
		y = y1;
	}
	
	/**
	 * @param o1, the model Coord
	 * @effects construct a new Coord with the same x and y 
	 * coordinates as the model Coord
	 */
	public Coord(Coord o1) {
		x = o1.getX();
		y = o1.getY();
	}
	
	/**
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @param obj The object to be compared for equality.
	 * @return true iff 'obj' is an instance of a Coord and 'this' and 'obj' 
	 * 			represent the same Coord.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Coord) {
			Coord obj = (Coord) o;
			return obj.getX() == x && obj.getY() == y;
		}
		return false;
	}
	
	/** Standard hashCode function.
	 * @return an int that all objects equal to this will also
        return.
	 */
	@Override
	public int hashCode() {
		return (int)(x*7.0 + y*13.0);
	}
}