package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 * The Line objects for drawing the Lines in the {@link DrawingArea}.
 * @author Trevor, Jason
 *
 */

public class Line extends Shape implements Serializable{

	/**
	 * Line constructor for drawing a Line.
	 * @param startX - The x coordinate of the beginning corner of the shape.
	 * @param startY - The y coordinate of the beginning corner of the shape.
	 * @param endX - The x coordinate for the end of the line.
	 * @param endY - The y coordinate for the end of the line.
	 * @param color - The current color selected for the shape using {@link JColorChooser}.
	 */
	
	public Line(int startX, int startY, int endX, int endY, Color color) {
		super(startX, startY, endX, endY, color);
	}
	
	/**
	 * Returns the start of the line.
	 *@return getStart - The {@link Shape}s start {@link Point}.
	 */
	
	public Point getStart(){
		return super.getStart();
	}
	
	/**
	 * Returns the end of the line.
	 *@return getEnd - The {@link Shape}s end {@link Point}.
	 */
	
	public Point getEnd(){
		return super.getEnd();
	}
	
	/**
	 * Abstract method to draw a Line in a specific way.
	 * @param g -  The graphics that is passed into the draw function.
	 */

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
	}
}
