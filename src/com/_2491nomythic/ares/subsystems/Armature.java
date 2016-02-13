package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Armature extends Subsystem {
    private CANTalon motor;
    
    public static Armature instance;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static Armature getInstance() {
    	if(instance == null){
    		instance = new Armature();
    	}
    	return instance;
    }
    
    private Armature() {
    	motor = new CANTalon(Constants.armatureChannel);
    }
    
    public void set(double speed) {
    	motor.set(speed);
    }
    
    public double getEncoderPosition() {
    	return motor.getEncPosition();
    }
    
    public double getEncoderVelocity() {
    	return motor.getEncVelocity();
    }
    
    public CANTalon get() {
    	return motor;
    }
    
    public void stop() {
    	set(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

