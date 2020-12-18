
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** 
 * A simple Graph class file
 * this is a main class file, responsible for plot.
 * this class will be used in separate class files to plot the graphs.
 * it is advisable not to change code in this class file.
 */

public class WeatherGraph extends Frame {

	// declare variables for titles
	private String xTitle = "";
	private String yTitle = "";
	private String graphTitle = "";

	// declare how many data sets to be plotted
	private int numDatasets; 

	// declare some variables for maximum and minimum values
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;

	// set some variables for the axes
	private int xAxisLength, yAxisLength, xOrigin, yOrigin;
	private int xBorder, yBorder;
	private double xSpread, ySpread;

	// set up a collection of points
	ArrayList<Point> points;
	
	// list of colours to use whilst plotting
	private Color[] colorList = new Color[] {
			Color.black,
			Color.red,
			Color.blue,
			Color.yellow,			
			Color.green,
			Color.white,
			Color.gray,
			Color.cyan,
			Color.magenta,
			Color.darkGray
	};

	// simple constructor - leaves all titles blank
	public WeatherGraph() {
		numDatasets = 1;
		initialiseGraph();
	}

	// constructor that assigns titles
	public WeatherGraph(String x, String y, String g) {
		numDatasets = 1;
		xTitle = x;
		yTitle = y;
		graphTitle = g;
		initialiseGraph();
	}

	// constructor that assigns titles and number of datasets
	public WeatherGraph(String x, String y, String g, int n) {
		numDatasets = n;
		xTitle = x;
		yTitle = y;
		graphTitle = g;
		initialiseGraph();
	}

	// method to create the graph
	private void initialiseGraph() {

		// set the max and min data values
		xMax = Double.MIN_VALUE;
		yMax = Double.MIN_VALUE;
		xMin = Double.MAX_VALUE;
		yMin = Double.MAX_VALUE;

		// initialise the collection of points
		points = new ArrayList<Point>();

		// set up the window (i.e. the frame)
		setSize(640,480);
		super.setTitle ("Graph");
		addWindowListener (new WindowAdapter () {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	// add data to the graph
	public void add(double x, double y) {		
		points.add(new Point(x, y, 0));
		if (x > xMax) xMax = x;
		if (x < xMin) xMin = x;
		if (y > yMax) yMax = y;
		if (y < yMin) yMin = y;
	}

	// add data to specified data set in the graph
	public void add(double x, double y, int dataset) {		

		// if the specified data set is less than or equal to the max number of
		// data sets then add it to the specified data set
		if(dataset <= numDatasets-1) {		
			points.add(new Point(x, y, dataset-1));
		}
		// otherwise add it to the highest numbered data set
		else {
			points.add(new Point(x, y, numDatasets-1));
		}

		// adjust max and min values
		if (x > xMax) xMax = x;
		if (x < xMin) xMin = x;
		if (y > yMax) yMax = y;
		if (y < yMin) yMin = y;
	}

	// show the graph
	public void showGraph() {
		repaint();
		setVisible(true);
	}

	// render the graph
	public void paint (Graphics g) {

		// calculate length of axes from window size minus a border of 20
		xBorder = 40;
		yBorder = 80;
		xAxisLength = (int) getWidth() - 2 * xBorder;
		yAxisLength = (int) getHeight() - 2 * yBorder;

		// calculate value spreads from mins and maxs which have
		// been recorded as we go
		xSpread = xMax - xMin;
		ySpread = yMax - yMin;
		if (xMin > 0) xOrigin = scaleX(xMin); else xOrigin = scaleX(0);
		if (yMin > 0) yOrigin = scaleY(yMin); else yOrigin = scaleY(0);

		drawAxes(g);
		plotGraph(g);
	}

	// draw the graph axes
	private void drawAxes(Graphics g) {

		Font plain = g.getFont();
		Font small = new Font(plain.getFamily(),Font.PLAIN,10);
		Font bold = new Font(plain.getFamily(),Font.BOLD,14);

		// draw the axes
		g.drawLine(xBorder - 5, yOrigin, xAxisLength + xBorder + 5, yOrigin);
		g.drawLine(xOrigin, yBorder - 5, xOrigin, yAxisLength + yBorder + 5);

		// draw the x and y axis labels
		g.drawString(xTitle,
				getWidth() - g.getFontMetrics().stringWidth(xTitle) - xBorder / 2,
				yOrigin - 5);
		g.drawString(yTitle,
				xOrigin - g.getFontMetrics().stringWidth(yTitle) / 2,
				yBorder - 8);

		// draw the graph title
		g.setFont(bold);
		g.drawString(graphTitle,
				(getWidth() - g.getFontMetrics().stringWidth(graphTitle)) / 2,
				yBorder / 2);
		g.setFont(plain);

		// tick and label the four min / max points only
		int scaleXMin = scaleX(xMin);
		int scaleXMax = scaleX(xMax);
		int scaleYMin = scaleY(yMin);
		int scaleYMax = scaleY(yMax);
		g.drawLine(xOrigin-5,scaleYMax,xOrigin+5,scaleYMax);
		g.drawLine(xOrigin-5,scaleYMin,xOrigin+5,scaleYMin);
		g.drawLine(scaleXMax,yOrigin+5,scaleXMax,yOrigin);
		g.drawLine(scaleXMin,yOrigin+5,scaleXMin,yOrigin);

		g.setFont(small);
		g.drawString(Text.writeDouble(xMin,6,2),scaleXMin-10,yOrigin+15);
		g.drawString(Text.writeDouble(xMax,6,2),scaleXMax-10,yOrigin+15);
		g.drawString(Text.writeDouble(yMin,6,2),xOrigin-35,scaleYMin+4);
		g.drawString(Text.writeDouble(yMax,6,2),xOrigin-35,scaleYMax+4);
		g.setFont(plain);
	}

	private void plotGraph(Graphics g) {

		// sort all the data points
		Collections.sort(points);

		// crude hack to get the number of points in each data set ...
		int[] sizes = new int[numDatasets];
		for(int k = 0; k < points.size(); k++) {
			Point p = points.get(k);
			sizes[p.dataset]++;
		}
		
		// mark size
		int mark = 2;

		// iterate over all the data sets (changing colour after each one!)
		int count = 0;
		for(int j = 0; j < numDatasets; j++) {
				
			// set the colour
			g.setColor(colorList[j]);
			
			// get first point
			Point p1 = points.get(count);
			int x1 = scaleX(p1.xCoord);
			int y1 = scaleY(p1.yCoord);

			// loop through the rest of the points
			for(int i = 1; i < sizes[j]; i++) {

				// draw point
				g.drawOval(x1 - mark, y1 - mark, 2 * mark, 2 * mark);

				// get next point
				Point p2 = points.get(count+i);
				int x2 = scaleX(p2.xCoord);
				int y2 = scaleY(p2.yCoord);

				// draw point
				g.drawOval(x2 - mark, y2 - mark, 2 * mark, 2 * mark);

				// draw line between points
				g.drawLine(x1, y1, x2, y2);

				// set x1 = x2 & y1 = y2
				x1 = x2;
				y1 = y2;	

			}	
			
			// increment count because we are moving on the next data set
			count = count + sizes[j];
		}
	}

	private int scaleX(double x) {
		return (int) ((x-xMin) / xSpread*xAxisLength)  + xBorder ;
	}

	private int scaleY(double y) {
		return (int) (yAxisLength - ((y-yMin) /
				ySpread*yAxisLength)) + yBorder;
	}

	// utility class that represents a single point
	private class Point implements Comparable<Point> {

		double xCoord, yCoord;
		int dataset;

		Point(double x, double y, int set) {
			xCoord = x;
			yCoord = y;
			dataset = set;
		}

		@Override
		// add a comparison between points so we can sort ...
		public int compareTo(Point another) {

			if(this.dataset < another.dataset) {
				return -1;
			}
			else if(this.dataset > another.dataset) {
				return 1;
			}
			else {
				if(this.xCoord < another.xCoord) {
					return -1;
				}
				else if(this.xCoord > another.xCoord) {
					return 1;
				}
				else {
					return 0;
				}
			}		
		}

	}

}

// utility class for doing text formatting
class Text {

	// define the format
	private static DecimalFormat N = new DecimalFormat();
	private static final String spaces = "                    ";

	public static String writeDouble(double number, int align, int frac) {
		N.setGroupingUsed(false);
		N.setMaximumFractionDigits(frac);
		N.setMinimumFractionDigits(frac);
		String num = N.format(number);
		if (num.length() < align)
			num = spaces.substring(0,align-num.length()) + num;
		return num;
	}
}

