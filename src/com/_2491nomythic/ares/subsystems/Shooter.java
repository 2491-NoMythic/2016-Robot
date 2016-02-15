package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private CANTalon motorLeft, motorRight;
    private Solenoid solenoidLeft, solenoidRight;
    private double currentLeftSpeed, currentRightSpeed;
    
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
    	
    	motorLeft.configEncoderCodesPerRev(1); //TODO check empirically if the encoder 4x edge reading impacts this.
    	motorRight.configEncoderCodesPerRev(1);//I want one unit per rotation, not 4 units per rotation.
    	
    	solenoidLeft = new Solenoid(Constants.shooterSolenoidLeftChannel);
    	solenoidRight = new Solenoid(Constants.shooterSolenoidRightChannel);
    	solenoidLeft.set(false);
    	solenoidRight.set(false);
    	
    	
    }
    
    public void set(double speedLeft, double speedRight) {
    	setLeft(speedLeft);
    	setRight(speedRight);
    }
    
    public void set(double speed) {
    	setLeft(speed);
    	setRight(speed);
    }
    
    public void setLeft(double speed) {
    	currentLeftSpeed = speed;
    	motorLeft.set(speed);
    }
    
    public void setRight(double speed) {
    	currentRightSpeed = speed;
    	motorRight.set(-1.0 * speed);
    }
    
    public void raise() {
		solenoidLeft.set(true);
		solenoidRight.set(true);
	}
	
	public void lower() {
		solenoidLeft.set(false);
		solenoidRight.set(false);
	}
    
    public double getLeftEncoderPosition() {
    	return motorLeft.getEncPosition();
    }
    
    public double getLeftEncoderVelocity() {
    	return motorLeft.getEncVelocity();
    }
    
    public double getRightEncoderPosition() {
    	return motorRight.getEncPosition();
    }
    
    public double getRightEncoderVelocity() {
    	return motorRight.getEncVelocity();
    }
    
    public Solenoid getLeftSolenoid() {
		return solenoidLeft;
	}
	
	public Solenoid getRightSolenoid() {
		return solenoidRight;
	}
	
	public boolean getLeftSolenoidValue() {
		return solenoidLeft.get();
	}
	
	public boolean getRightSolenoidValue() {
		return solenoidRight.get();
	}
    
    public double getLeftSpeed() {
    	return currentLeftSpeed;
    }
    
    public double getRightSpeed() {
    	return currentRightSpeed;
    }
    
    public void stop() {
    	set(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

