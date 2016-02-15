package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    private CANTalon motor;
    private DigitalInput limitSwitch;
    
    public static Intake instance;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static Intake getInstance() {
    	if(instance == null){
    		instance = new Intake();
    	}
    	return instance;
    }
    
    private Intake() {
    	motor = new CANTalon(Constants.intakeChannel);
    	
    	limitSwitch = new DigitalInput(Constants.intakeLimitSwitchChannel);
    }
    
    public void set(double speed) {
    	motor.set(-1.0 * speed);
    }
    
    public CANTalon get() {
    	return motor;
    }
    
    public boolean getLimitSwitchValue(){
    	return limitSwitch.get();
    }
    
    public void stop() {
    	set(0);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

