package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    private CANTalon motor;
    private DigitalInput limitSwitch1, limitSwitch2;
    
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
    	
    	limitSwitch1 = new DigitalInput(Constants.intakeLimitSwitch1Channel);
    	limitSwitch2 = new DigitalInput(Constants.intakeLimitSwitch2Channel);
    }
    
    public void set(double speed) {
    	motor.set(-1.0 * speed);
    }
    
    public CANTalon get() {
    	return motor;
    }
    
    public boolean getLimitSwitch1Value() {
    	return limitSwitch1.get();
    }
    
    public boolean getLimitSwitch2Value() {
    	return limitSwitch2.get();
    }
    
    public void stop() {
    	set(0);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

