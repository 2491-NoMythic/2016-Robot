package com._2491nomythic.util;

/**
 * A cartesian coordinate.
 */
public class CartesianCoord {
	private double x, y;
	
	/**
	 * A cartesian coordinate.
	 * @param x The x value of the coordinate.
	 * @param y The y value of the coordinate.
	 */
	public CartesianCoord(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x coordinate
	 * 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Set the x coordinate
	 * 
	 * @param newX
	 *            new x coordinate
	 */
	public void setX(double newX) {
		x = newX;
	}
	
	/**
	 * Get the y coordinate
	 * 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Set the y coordinate
	 * 
	 * @param newY
	 *            new y coordinate
	 */
	public void setY(double newY) {
		y = newY;
	}
	
	/**
	 * Get a polar version of the coordinate
	 * 
	 * @return a polar version of the coordinate
	 */
	public PolarCoord getPolar() {
		double r, theta;
		r = Math.sqrt(x * x + y * y);
		theta = Math.atan2(y, x);
		return new PolarCoord(r, theta);
	}
}
