package module1;

import com.sun.imageio.plugins.common.ImageUtil;
import de.fhpotsdam.unfolding.providers.Microsoft;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PImage;
import processing.opengl.PGraphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/** HelloWorld
  * An application with two maps side-by-side zoomed in on different locations.
  * Author: UC San Diego Coursera Intermediate Programming team
  * @author Your name here
  * Date: July 17, 2015
  * */
public class HelloWorld extends PApplet
{
	/** Your goal: add code to display second map, zoom in, and customize the background.
	 * Feel free to copy and use this code, adding to it, modifying it, etc.  
	 * Don't forget the import lines above. */

	// You can ignore this.  It's to keep eclipse from reporting a warning
	private static final long serialVersionUID = 1L;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// IF YOU ARE WORKING OFFLINE: Change the value of this variable to true
	private static final boolean offline = false;
	
	/** The map we use to display their home town: La Jolla, CA */
	UnfoldingMap left_map;
	
	/** The map you will use to display my home town: Kharkiv, UA */
	UnfoldingMap right_map;

	public void setup() {
		size(850, 600, P2D);  // Set up the Applet window to be 800x600
		                      // The OPENGL argument indicates to use the 
		                      // Processing library's 2D drawing
		                      // You'll learn more about processing in Module 3

		// This sets the background color for the Applet.  
		// Play around with these numbers and see what happens!
        PImage image;
        image = loadImage("ua_flag.jpg");
        this.background(image);

		
		// Select a map provider
		AbstractMapProvider left_map_provider = new Google.GoogleTerrainProvider();
        AbstractMapProvider right_map_provider = new Microsoft.HybridProvider();
		// Set a zoom level
		int zoomLevel = 10;
		
		if (offline) {
			// If you are working offline, you need to use this provider 
			// to work with the maps that are local on your computer.  
			left_map_provider = new MBTilesMapProvider(mbTilesString);
			// 3 is the maximum zoom level for working offline
			zoomLevel = 3;
		}
		
		// Create a new UnfoldingMap to be displayed in this window.  
		// The 2nd-5th arguments give the map's x, y, width and height
		// When you create your map we want you to play around with these 
		// arguments to get your second map in the right place.
		// The 6th argument specifies the map provider.  
		// There are several providers built-in.
		// Note if you are working offline you must use the MBTilesMapProvider
		left_map = new UnfoldingMap(this, 50, 50, 350, 500, left_map_provider);
        right_map = new UnfoldingMap(this, 450, 50, 350, 500, right_map_provider);

		// The next lines zoom in and center the maps at
	    // 32.9 (latitude) and -117.2 (longitude) and
	    left_map.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
        right_map.zoomAndPanTo(zoomLevel, new Location(49.9935f, 36.2303f));

		
		// This line makes the map interactive
		MapUtils.createDefaultEventDispatcher(this, left_map);
        MapUtils.createDefaultEventDispatcher(this, right_map);

	}

	/** Draw the Applet window.  */
	public void draw() {
		// Next lines draw maps.
		left_map.draw();
        right_map.draw();
	}

	
}
