package com._2491nomythic.robot.subsystems;

import com._2491nomythic.robot.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private CANTalon motorTop, motorBottom;
    private double motorTopSpeed, motorBottomSpeed;
    
    public static Shooter instance;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static Shooter getInstance() {
    	if(instance == null){
    		instance = new Shooter();
    	}
    	return instance;
    }
    
    private Shooter() {
    	motorTop = new CANTalon(Constants.shooterTopChannel);
    	motorBottom = new CANTalon(Constants.shooterBottomChannel);
    }
    
    public void setShooter(double speedTop, double speedBottom) {
    	setShooterTop(speedTop);
    	setShooterBottom(speedBottom);
    }
    
    public void setShooterTop(double speed) {
    	motorTopSpeed = speed;
    	motorTop.set(speed);
    }
    
    public void setShooterBottom(double speed) {
    	motorBottomSpeed = speed;
    	motorBottom.set(speed);
    }
    
    public double getShooterTop() {
    	return motorTopSpeed;
    }
    
    public double getShooterBottom() {
    	return motorBottomSpeed;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

