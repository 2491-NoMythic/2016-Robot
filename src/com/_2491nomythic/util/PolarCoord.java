package com._2491nomythic.util;

public class PolarCoord {
	private double r, theta;
	
	/**
	 * A polar coordinate.
	 * @param r The r value of the coordinate.
	 * @param theta The theta value of the coordinate.
	 */
	public PolarCoord(double r, double theta) {
		this.r = r;
		this.theta = theta;
	}
	
	/**
	 * Get the r value
	 * 
	 * @return the r value
	 */
	public double getR() {
		return r;
	}
	
	/**
	 * Set the r value
	 * 
	 * @param newR
	 *            new r value
	 */
	public void setR(double newR) {
		r = newR;
	}
	
	/**
	 * Get the theta value
	 * 
	 * @return the theta value
	 */
	public double getTheta() {
		return theta;
	}
	
	/**
	 * Set the theta value using radians
	 * 
	 * @param newTheta new theta value in radians
	 */
	public void setTheta(double newTheta) {
		theta = newTheta;
	}
	
	/**
	 * Set the theta value using degrees
	 * 
	 * @param newTheta new theta value in degrees
	 */
	public void setThetaDeg(double newTheta) {
		theta = newTheta / 180 * Math.PI;
	}
	
	/**
	 * Get a cartesian version of the coordinate
	 * 
	 * @return Cartesian version of the coordinate
	 */
	public CartesianCoord getCartesian() {
		double x = Math.cos(theta) * r;
		double y = Math.sin(theta) * r;
		return new CartesianCoord(x, y);
	}
}
