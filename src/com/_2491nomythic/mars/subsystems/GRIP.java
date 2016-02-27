package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.UpdateDriverstation;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
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
	
	private GRIP() {
    	defaultValue = new double[0];
	}
	
	public NetworkTable getTable() {
		return NetworkTable.getTable("GRIP/myCountoursReport");
	}
	
	public double[] getCenterX() {
		return getTable().getNumberArray("centerX", defaultValue);
	}
	
	public double[] getCenterY() {
		return getTable().getNumberArray("centerY", defaultValue);
	}
	
	public double[] getArea() {
		return getTable().getNumberArray("area", defaultValue);
	}
	
	public double[] getHeight() {
		return getTable().getNumberArray("height", defaultValue);
	}
	
	public double[] getWidth() {
		return getTable().getNumberArray("width", defaultValue);
	}
	
	public double distanceFromTarget(double pixelWidth) {
		double targetWidth = 1.6667; //ft
		double FOVPixel = 800;
		double targetPixelWidth = pixelWidth;
		return (targetWidth*FOVPixel)/(targetPixelWidth * 2 * Math.tan(Constants.cameraHorizontalViewAngle * (Math.PI / 180)));
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new UpdateDriverstation());
    }
}

