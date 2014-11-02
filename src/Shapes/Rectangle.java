package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * The Rectangle objects for drawing the rectangles in the {@link DrawingArea}.
 * @author Trevor, Jason
 *
 */

public class Rectangle extends Shape implements Serializable{
	
	/**
	 * Rectangle constructor for drawing a Rectangle.
	 * @param x - The x coordinate of the upper left corner of the shape.
	 * @param y - The y coordinate of the upper left corner of the shape.
	 * @param color - The current color selected for the shape using {@link JColorChooser}.
	 * @param width - The width of the shape.
	 * @param height - The height of the shape.
	 */

	public Rectangle(int x, int y, Color color, int width, int height) {
		super(x, y, color, width,height);
	}
	
	/**
	 * Abstract method to draw a Rectangle in a specific way.
	 * @param g -  The graphics that is passed into the draw function.
	 */

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		Rectangle2D.Double rect = new Rectangle2D.Double(
				upperLeft.getX(), upperLeft.getY(),
				dimensions.getX(), dimensions.getY());
		g2.fill(rect);
	}
}
