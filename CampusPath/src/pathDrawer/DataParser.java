package pathDrawer;

import java.io.*;
import java.util.*;

import objects.*;

public class DataParser {
    /**
     * A checked exception class for bad data files
     */
    @SuppressWarnings("serial")
    public static class MalformedDataException extends Exception {
        public MalformedDataException() { }

        public MalformedDataException(String message) {
            super(message);
        }

        public MalformedDataException(Throwable cause) {
            super(cause);
        }

        public MalformedDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }

  /**
   * Reads the Campus Building data set and Campus Path data set.
   * For Campus Building data set, each line consists building abbreviation, 
   * its full name and its coordinates. For Campus Path data set, 
   * for every line that consists a coordinate, there follow coordinates where
   * one can go directly to. 
   * 
   * @requires filename are valid file paths
   * @param filename of the campus building map that will be read
   * @param filename of the campus route map that will be read
   * @param Graph where the relationships of all coordinates are mapped
   * @param abbreviation map where all the abbreviations and their full names are mapped
   * @param building map where all the buildings are mapped
   * @modifies graph
   * @modifies abbre
   * @modifies buildings
   * @effects fills graph with the coordinates in the path file, abbre with the abbreviations
   * and full names of the buildings, buildings with the abbreviations of the
   *  buildings and their coordinates
   * @throws MalformedDataException if the files are not well-formed:
   *          each line contains wrong number of tokens.
   */
  public static void parseData(String buildings_file, String routes_file, 
		  Graph<Coord,Double> paths, Map<String, String> abbre, 
		  Map<String, Coord> buildings) throws MalformedDataException {
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
    BufferedReader building_reader = null;
    BufferedReader route_reader = null;
    try {
        building_reader = new BufferedReader(new FileReader(buildings_file));
        route_reader = new BufferedReader(new FileReader(routes_file));
        
        String inputLine;
        while ((inputLine = building_reader.readLine()) != null) {

            // Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }

            // Parse the data, stripping out quotation marks and throwing
            // an exception for malformed lines.
            String[] tokens = inputLine.split("\t");
            if (tokens.length != 4) {
                throwMalformedData(inputLine, tokens.length);
            }

            String abbrevation = tokens[0];
            String fullname = tokens[1];
            abbre.put(abbrevation, fullname);
            double x = Double.parseDouble(tokens[2]);
            double y = Double.parseDouble(tokens[3]);
            buildings.put(abbrevation, new Coord(x, y));
        }
        Node<Coord> parent = new Node<Coord> (new Coord());
        while((inputLine = route_reader.readLine()) != null) {
        	// Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }
            
            // Parse the data.
            if (!inputLine.contains(": ")) {
            	String[] tokens = inputLine.split(",");
            	if (tokens.length != 2) {
                    throwMalformedData(inputLine, tokens.length);
                }
            	Double x = Double.parseDouble(tokens[0]);
            	Double y = Double.parseDouble(tokens[1]);
            	Coord start = new Coord(x, y);
            	parent = new Node<Coord> (start);
            	paths.addNode(parent);
            	
            } else {
            	String[] tokens = inputLine.split(": ");
            	if (tokens.length != 2) {
                    throwMalformedData(inputLine, tokens.length);
                }
            	String[] coords = tokens[0].split(",");
            	if (coords.length != 2) {
                    throwMalformedData(inputLine, tokens.length);
                }
            	Double x = Double.parseDouble(coords[0]);
            	Double y = Double.parseDouble(coords[1]);
            	Double distance = Double.parseDouble(tokens[1]);
            	Node<Coord> end = new Node<Coord>(new Coord(x, y));
            	paths.addChildren(parent, new Children<Coord, Double>(distance, end));
            }
            
        }
        

    } catch (IOException e) {
        System.err.println(e.toString());
        e.printStackTrace(System.err);
    } finally {
        if (building_reader != null && route_reader != null) {
            try {
                building_reader.close();
                route_reader.close();
            } catch (IOException e) {
                System.err.println(e.toString());
                e.printStackTrace(System.err);
            }
        }
    }
  }

private static void throwMalformedData(String inputLine, int length) throws MalformedDataException {
	throw new MalformedDataException("The line contains " + length + " tab(s): " + inputLine);
}
}