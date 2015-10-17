package pathDrawer; 

import objects.*;
import pathDrawer.DataParser.MalformedDataException;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CampusPathsHelper {
	
	// A frame to hold components
	private JFrame frame;
	
	// All components to this view
	private JButton submit;
	private JButton reset;
	private JComboBox<String> start; 
	private JComboBox<String> end;
	private MapPanel displayMap;
	
	// A list of abbreviations and full names of all buildings
	private Map<String, String> abbre;
	
	// A graph of all the paths
	private Graph<Coord,Double> paths;
	
	// A map of abbreviations and coordinations of all buildings
	private Map<String, Coord> buildings;
	
	// The closest path between two given buildings
	private ArrayList<Children<Coord, Double>> path;
	
	/**
	 * @throws MalformedDataException
	 */
	public CampusPathsHelper() throws MalformedDataException {
		abbre = new HashMap<String, String> ();
		paths = new Graph<Coord,Double> ();
		buildings = new HashMap<String, Coord> ();
		
		setupComponent();
		
		// Add MapPanel to frame
		frame.add(displayMap, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	
	// Add custom button ActionListener
	private void buildButtonListener() {
		ButtonListener listner = new ButtonListener();
		submit.addActionListener(listner);
		reset.addActionListener(listner);
	}
	
	/**
	 * Listener class for Button
	 * Update MapPanel when user clicks submit or reset button
	 * @author Qiao Yan
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit && start.getSelectedIndex() != 0 && end.getSelectedIndex()!=0) {
				Object o1 = start.getSelectedItem();
				Object o2 = end.getSelectedItem();
				String start = "";
				String end = "";
				if (o1 instanceof String && o2 instanceof String) {
					start = (String) o1;
					end = (String) o2;
				}
				Coord startB = buildings.get(start);
				Coord endB = buildings.get(end);
				
				path = PathFinder.findPath(new Node<Coord>(startB), new Node<Coord>(endB), paths);
				displayMap.repaint();
			} else if (e.getSource() == reset){
				path = new ArrayList<Children<Coord, Double>> ();
				start.setSelectedIndex(0);
				end.setSelectedIndex(0);
				displayMap.repaint();
			}
			
		}
				
	}
	
	/**
	 * Custom class that is used to display buttons, ComboBoxes, map and pathes 
	 * @author Qiao Yan
	 */
	private class MapPanel extends JPanel {
		/**
		* Serialized ID
		*/
		private static final long serialVersionUID = 1L;
		private Image img;
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); 
			Graphics2D g2d = (Graphics2D)g;
			
			// retrieve the map
			File f = new File("src/data/campus_map.jpg"); 
			URI uri = f.toURI();
			try {
				img = ImageIO.read(uri.toURL());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Draw the map
			if (img != null){
				g2d.drawImage(img, 30, 40, 866, 593, null);
			}	
			
			// Draw the path
			if (path != null && !path.isEmpty()) {
				// set the color of g2d
				g2d.setColor(Color.RED);
				
				Coord depart = path.get(0).getChildNode().getContent();
				Coord dest = path.get(path.size() - 1).getChildNode().getContent();
				g2d.fillOval(30 + (int) depart.getX() / 5, 40 + (int) depart.getY() / 5, 10, 10);
				g2d.fillOval(30 + (int) dest.getX() / 5, 40 + (int) dest.getY() / 5, 10, 10);
				for (int i = 1; i < path.size(); i++) {
					Coord c1 = path.get(i - 1).getChildNode().getContent();
					Coord c2 = path.get(i).getChildNode().getContent();
					g2d.drawLine(30 + (int) c1.getX() / 5, 40 + (int) c1.getY() / 5, 30 
							+ (int) c2.getX() / 5, 40 + (int) c2.getY() / 5);				}
			}
		}	
	}
	
	
	// initialize ComboBox
	private void initializeComboBox() {
		submit = new JButton("Go");
		submit.setPreferredSize(new Dimension(50, 20));
		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(50, 20));

		buildButtonListener();
		
		start = new JComboBox<String> ();
		start.addItem("Start from");
		end = new JComboBox<String> ();
		end.addItem("End at");
		
		ArrayList<String> abbreSort = new ArrayList<String> (abbre.keySet());
		Collections.sort(abbreSort);
		for (String s: abbreSort) {
			start.addItem(s);
			end.addItem(s);
		}
	}
	
	// initialize MapPanel
	private void initializeMapPanel() {
		displayMap = new MapPanel();
		displayMap.setSize(1024, 768);
		displayMap.setPreferredSize(new Dimension(1024, 768));
		displayMap.setLayout(new FlowLayout());
		displayMap.add(start);
		displayMap.add(end);
		displayMap.add(submit);
		displayMap.add(reset);
	}
	
	// fill in paths, abbre, buildings and initialize ComboBox and MapPanel
	private void setupComponent() throws MalformedDataException {
		DataParser.parseData("src/data/campus_buildings.dat",
				"src/data/campus_paths.dat", paths, abbre, buildings);
		
		// initialize ComboBox
		initializeComboBox();
		
		// initialize MapPanel
		initializeMapPanel();
		
		// frame set up
		frame = new JFrame("CampusPaths");
		frame.setSize(1024,768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}