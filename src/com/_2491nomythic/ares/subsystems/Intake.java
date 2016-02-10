package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    private CANTalon motorArmature, motorIntake;
    private Encoder armatureEncoder;
    
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
    	motorArmature = new CANTalon(Constants.intakeArmatureChannel);
    	motorIntake = new CANTalon(Constants.intakeIntakeChannel);
    	
    	armatureEncoder = new Encoder(Constants.intakeArmatureEncoderChannel1, Constants.intakeArmatureEncoderChannel2);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

