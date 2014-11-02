package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 * The abstract base class for all the possible shapes.
 * @author Trevor, Jason
 *
 */

abstract public class Shape implements Serializable{

	protected Point upperLeft;
	protected Point dimensions;
	protected Color color;
	private Point pivot;
	protected Point start;
	protected Point end;
	
	/**
	 * Shape constructor for drawing shapes such as {@link Rectangle}s, {@link Oval}s, and {@link Image}s.
	 * @param x - The x coordinate of the upper left corner of the shape.
	 * @param y - The y coordinate of the upper left corner of the shape.
	 * @param color - The current color selected for the shape using {@link JColorChooser}.
	 * @param width - The width of the shape.
	 * @param height - The height of the shape.
	 */
	
	Shape(int x, int y, Color color, int width, int height){
		upperLeft = new Point(x,y);
		dimensions = new Point(width, height);
		this.color = color;
	}
	
	/**
	 * Shape constructor for drawing shapes such as {@link Line}s
	 * @param startX - The x coordinate of the beginning corner of the shape.
	 * @param startY - The y coordinate of the beginning corner of the shape.
	 * @param endX - The x coordinate for the end of the line.
	 * @param endY - The y coordinate for the end of the line.
	 * @param color - The current color selected for the shape using {@link JColorChooser}.
	 */
	
	Shape(int startX, int startY, int endX, int endY, Color color){
		this.start = new Point(startX, startY);
		this.end = new Point(endX, endY);
		this.color = color;
	}
	
	/**
	 * Returns the upper left point of the shape.
	 * @return upperLeft - The upper left point of the shape.
	 */
	
	public Point getUpperLeft(){
		return upperLeft;
	}
	
	/**
	 * Returns the width and height of the shape.
	 * @return dimensions - The width and height of the shape.
	 */
	
	public Point getDimensions(){
		return dimensions;
	}
	
	/**
	 * Returns the color of the drawn shape.
	 * @return color - The color of the drawn shape.
	 */
	
	public Color getColor(){
		return color;
	}
	
	/**
	 * Returns the pivot point of the drawn shape.
	 * @return pivot - The pivot point of the shape.
	 */
	
	protected Point getPivot(){
		return pivot;
	}
	
	/**
	 * Returns the start of the drawn shape.
	 * @return start - The start of the drawn shape.
	 */
	
	protected Point getStart(){
		return start;
	}
	
	/**
	 * Returns the end of the drawn shape.
	 * @return end - The end of the drawn shape.
	 */
	
	protected Point getEnd(){
		return end;
	}
	
	/**
	 * Abstact method for every shape to implement since each shape is drawn in a different way.
	 * @param g - The graphics that is passed into the draw function.
	 */
	
	public abstract void draw(Graphics g);
}
