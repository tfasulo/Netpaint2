package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * The Oval objects for drawing the Ovals in the {@link DrawingArea}.
 * @author Trevor, Jason
 *
 */

public class Oval extends Shape implements Serializable{
	
	/**
	 * Oval constructor for drawing an Oval.
	 * @param x - The x coordinate of the upper left corner of the shape.
	 * @param y - The y coordinate of the upper left corner of the shape.
	 * @param color - The current color selected for the shape using {@link JColorChooser}.
	 * @param width - The width of the shape.
	 * @param height - The height of the shape.
	 */

	public Oval(int x, int y, Color color, int width, int height) {
		super(x, y, color, width, height);
	}

	/**
	 * Abstract method to draw an Oval in a specific way.
	 * @param g -  The graphics that is passed into the draw function.
	 */
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillOval((int)upperLeft.getX(), (int)upperLeft.getY(), (int)dimensions.getX(), (int)dimensions.getY());
		
	}
}
