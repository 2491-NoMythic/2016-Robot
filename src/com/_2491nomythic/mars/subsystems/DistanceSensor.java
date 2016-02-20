package com._2491nomythic.mars.subsystems;

public class DistanceSensor {
	
	private static DistanceSensor instance;
	
	public static DistanceSensor getInstance() {
		if (instance == null) {
			instance = new DistanceSensor();
		}
		return instance;
	}
	
	/**
	 * The distance sensor used to find and report the distance of objects in front of the robot.
	 */
	private DistanceSensor() {
		
	}
	
	public int GetDistanceFeet() {

		// TODO: Write function that reads RS232 (serial) distance value from sensor
		
    	String str1 = "R5000";
    	String str2 = str1.substring(1);
    	int distance_val = 0;
    	
    	distance_val = Integer.parseInt(str2); // this gives us a value in millimeters
    	
    	distance_val = distance_val / 305; // rough conversion from mm to feet
    	
    	return distance_val;
	}
	
}
