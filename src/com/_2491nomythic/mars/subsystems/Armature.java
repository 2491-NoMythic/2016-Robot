package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.armature.KeepArmatureStill;
//import com._2491nomythic.mars.commands.armature.KeepArmatureStill;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Armature extends Subsystem {
    private CANTalon motor;
    private DigitalInput limitSwitch;
    private KeepArmatureStill keepArmatureStill;
    
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
    	
    	motor.configEncoderCodesPerRev(360);
    	motor.setEncPosition(0);
    	
    	limitSwitch = new DigitalInput(Constants.armatureLimitSwitchChannel);
    	
    	keepArmatureStill = new KeepArmatureStill();
    }
    
    public void set(double speed) {
    	if(keepArmatureStill.isRunning()) {
    		keepArmatureStill.cancel();
    	}
    	motor.set(speed);
    }
    
    public void armatureSet(double speed) {
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
    	keepArmatureStill.start();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new KeepArmatureStill());
    }
    public boolean armLimitSwitch(){
    	return limitSwitch.get();
    }
}

