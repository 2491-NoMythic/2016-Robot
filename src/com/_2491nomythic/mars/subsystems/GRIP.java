package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.UpdateDriverstation;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The values sent to us by the GRIP vision system running on the Raspberry Pi
 */
public class GRIP extends Subsystem {
	double[] area, centerX, centerY, defaultValue;
	NetworkTable table;
	
	private static GRIP instance;
	
	public static GRIP getInstance() {
		if (instance == null) {
			instance = new GRIP();
		}
		return instance;
	}
	
	/**
	 * The values sent to us by the GRIP vision system running on the Raspberry Pi
	 */
	private GRIP() {
		defaultValue = new double[1];
		defaultValue[0] = 1;
	}
	
	/**
	 * @return The NetworkTable sent to us by the Raspberry Pi
	 * @deprecated
	 */
	public NetworkTable getTable() {
		return NetworkTableInstance.getDefault().getTable("/GRIP/myContoursReport");
	}
	
	/**
	 * @return An array of the horizontal locations of the center of the identified targets
	 * @deprecated
	 */
	public double[] getCenterX() {
		return defaultValue;//getTable().getNumberArray("centerX", defaultValue);
	}
	
	/**
	 * @return An array of the vertical locations of the center of the identified targets
	 * @deprecated
	 */
	public double[] getCenterY() {
		return defaultValue;//getTable().getNumberArray("centerY", defaultValue);
	}
	
	/**
	 * @return An array of the areas of the identified targets
	 * @deprecated
	 */
	public double[] getArea() {
		return defaultValue;//getTable().getNumberArray("area", defaultValue);
	}
	
	/**
	 * @return An array of the heights of the identified targets
	 * @deprecated
	 */
	public double[] getHeight() {
		return defaultValue;//getTable().getNumberArray("height", defaultValue);
	}
	
	/**
	 * @return An array of the widths of the identified targets
	 * @deprecated
	 */
	public double[] getWidth() {
		return defaultValue;//getTable().getNumberArray("width", defaultValue);
	}
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new UpdateDriverstation());
	}
}

