package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private CANTalon motorLeft, motorRight;
    private double motorLeftSpeed, motorRightSpeed;
    
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
    	motorLeft = new CANTalon(Constants.shooterTalonLeftChannel);
    	motorRight = new CANTalon(Constants.shooterTalonRightChannel);
    }
    
    public void setShooter(double speedLeft, double speedRight) {
    	setShooterLeft(speedLeft);
    	setShooterRight(speedRight);
    }
    
    public void setShooterLeft(double speed) {
    	motorLeftSpeed = speed;
    	motorLeft.set(speed);
    }
    
    public void setShooterRight(double speed) {
    	motorRightSpeed = speed;
    	motorRight.set(speed);
    }
    
    public double getShooterTop() {
    	return motorLeftSpeed;
    }
    
    public double getShooterBottom() {
    	return motorRightSpeed;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

