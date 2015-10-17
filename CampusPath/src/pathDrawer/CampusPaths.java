package pathDrawer;

import objects.*;
import pathDrawer.DataParser.MalformedDataException;

import java.util.*;

public class CampusPaths {
	
	// this program take the names of two buildings and generate 
	// directions for the shortest walking route between them
	public static void main(String[] args) throws MalformedDataException {
		Scanner in = new Scanner(System.in);
		Map<String, String> abbre = new HashMap<String, String> ();
		Graph<Coord,Double> paths = new Graph<Coord,Double> ();
		Map<String, Coord> buildings = new HashMap<String, Coord> ();
		setup(abbre, paths, buildings);
		printMenu();
		System.out.println();
		String option = getOption(in);
		while (!option.equals("q")) {
			if (option.equals("m")) {
				printMenu();
			} else if (option.equals("b")) {
				printBuildings(abbre);
			} else if (option.equals("r")) {
				String start = getStart(in);
				String end = getEnd(in);
				if (buildings.containsKey(start) && buildings.containsKey(end)) {
					findPath(abbre.get(start), abbre.get(end), buildings.get(start), buildings.get(end), paths);
				} else {
					if (!buildings.containsKey(start)) {
						printUnknownBuilding(start);
					} 
					if (!buildings.containsKey(end)) {
						printUnknownBuilding(end);
					}
				}
				
			} else {
				printUnknwonOption();
			}
			System.out.println();
			option = getOption(in);
		}
		
	}
	
	// fill abbreviation list, path graph and building list
	private static void setup(Map<String, String> abbre, 
			Graph<Coord,Double> paths, Map<String, Coord> buildings) throws MalformedDataException {
		DataParser.parseData("src/data/campus_buildings.dat",
				"src/data/campus_paths.dat", paths, abbre, buildings);
	}
	
	// print out menu
	private static void printMenu() {
		System.out.println("Menu:");
		System.out.println("	r to find a route");
		System.out.println("	b to see a list of all buildings");
		System.out.println("	q to quit");
	}
	
	// get command from the user
	private static String getOption(Scanner in) {
		System.out.print("Enter an option ('m' to see the menu): ");
		String option = in.nextLine();
			while (option.startsWith("#") || option.equals("")) {
				System.out.println(option);
				option = in.nextLine();
            }
		return option;
	}
	
	// print out a list of buildings
	private static void printBuildings(Map<String, String> abbre) {
		System.out.println("Buildings: ");
		List<String> abbre_sorted = new ArrayList<String> (abbre.keySet());
		Collections.sort(abbre_sorted);
		for (String abbrevation: abbre_sorted) {
			System.out.println("\t" + abbrevation + ": " +abbre.get(abbrevation));
		}
	}
	
	// print when the user types in unknown options
	private static void printUnknwonOption() {
		System.out.println("Unknown option");
	}
	
	// get start building from the user
	private static String getStart(Scanner in) {
		System.out.print("Abbreviated name of starting building: ");
		String start = in.nextLine();
			while (start.startsWith("#") || start.equals("")) {
				System.out.println(start);
				start = in.nextLine();
            }
		
		return start;
	}
	
	// get destination building from the user
	private static String getEnd(Scanner in) {
		System.out.print("Abbreviated name of ending building: ");
		String end = in.nextLine();
		while (end.startsWith("#") || end.equals("")) {
			System.out.println(end);
			end = in.nextLine();
        }
		return end;
	}
	
	// find the path from the given starting building to the given destination building
	private static void findPath(String start, String end, Coord depart, Coord dest, Graph<Coord,Double> paths) {
		ArrayList<Children<Coord,Double>> result 
		= PathFinder.findPath(new Node<Coord>(depart), new Node<Coord>(dest), paths);
		printPath(start, end, result);
	}
	
	// print out the path
	private static void printPath(String start, String end, ArrayList<Children<Coord,Double>> result) {
		System.out.println("Path from "+ start +" to " + end + ":");
		double totalD = 0.0;
		for (int i = 1; i < result.size(); i++) {
			totalD = totalD + result.get(i).getLabel();
			String direction = findDirect(result.get(i-1).getChildNode().getContent(), result.get(i).getChildNode().getContent());
			System.out.println("	Walk " + String.format("%.0f", result.get(i).getLabel())
					+ " feet "  + direction+ " to (" + String.format("%.0f", result.get(i).getChildNode().getContent().getX()) 
					+ ", " + String.format("%.0f", result.get(i).getChildNode().getContent().getY()) + ")");
		}
		System.out.println("Total distance: " + String.format("%.0f", totalD) + " feet");
	}
	
	// print the output when the user inputs an unknown building 
	private static void printUnknownBuilding(String building) {
		System.out.println("Unknown building: "+building);
	}
	
	// find direction between two coordinations
	private static String findDirect(Coord c1, Coord c2) {
		double x1 = c1.getX();
		double x2 = c2.getX();
		double y1 = c1.getY();
		double y2 = c2.getY();
		double angleDiff = Math.atan2(y2-y1, x2-x1);
		if (angleDiff < Math.PI / 8.0 && angleDiff >= -Math.PI / 8.0) {
			return "E";
		} else if (angleDiff < 3.0*Math.PI / 8.0 && angleDiff >= Math.PI / 8.0) {
			return "SE";
		} else if (angleDiff < 5.0*Math.PI / 8.0 && angleDiff >= 3.0*Math.PI / 8.0) {
			return "S";
		} else if (angleDiff < 7.0*Math.PI / 8.0 && angleDiff >= 5.0*Math.PI / 8.0) {
			return "SW";
		} else if (angleDiff < -7.0*Math.PI / 8.0 || angleDiff >= 7.0*Math.PI / 8.0) {
			return "W";
		} else if (angleDiff < -5.0*Math.PI / 8.0 && angleDiff >= -7.0*Math.PI / 8.0) {
			return "NW";
		} else if (angleDiff < -3.0*Math.PI / 8.0 && angleDiff >= -5.0*Math.PI / 8.0) {
			return "N";
		} else {
			return "NE";
		}
	}
}