package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.commands.armature.KeepArmatureStill;
import com._2491nomythic.ares.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Armature extends Subsystem {
    private CANTalon motor;
    private DigitalInput limitSwitch;
    
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
    
    public void runMotorToPosition(double Position) {
		double CurrentEncoderValue = getEncoderPosition();
		
		while((CurrentEncoderValue >= (Position - Constants.acceptableArmatureDifference)) &&
			  (CurrentEncoderValue <= (Position + Constants.acceptableArmatureDifference))){
			if(CurrentEncoderValue == Position){
				//the motor is in the correct position
				set(0);
			}
			else if(CurrentEncoderValue > Position){
				//motor position is greater then where it needs to be
				set(-1.0 * Constants.armatureSpeed);
			}
			else{
				//motor position is less then where it needs to be
				set(Constants.armatureSpeed);
			}
			
			// Put the thread to sleep while we let the motor run
			try {
				Thread.sleep(Constants.armatureRunTimeMs);
			} catch(InterruptedException e) {
				System.out.print("Thread sleep error!");
			}
			
			CurrentEncoderValue = getEncoderPosition();
		}
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
    	setDefaultCommand(new KeepArmatureStill());
    }
    public boolean armLimitSwitch(){
    	return limitSwitch.get();
    }
}

