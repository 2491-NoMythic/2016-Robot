package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The system that shoots boulders into the high goal.
 */
public class Shooter extends Subsystem {
    private CANTalon motorLeft, motorRight;
    private Solenoid solenoid;
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
    
    /**
     * The system that shoots boulders into the high goal.
     */
    private Shooter() {
    	motorLeft = new CANTalon(Constants.shooterTalonLeftChannel);
    	motorRight = new CANTalon(Constants.shooterTalonRightChannel);
    	
    	motorLeft.configEncoderCodesPerRev(1); //TODO check empirically if the encoder 4x edge reading impacts this.
    	motorRight.configEncoderCodesPerRev(1);//I want one unit per rotation, not 4 units per rotation.
    	motorLeft.setEncPosition(0);
    	solenoid = new Solenoid(Constants.shooterSolenoidChannel);
    	solenoid.set(false); 
    }
    
    /**
	 * Sets the shooter motors to independent specific speeds.
	 * @param speedLeft The power fed to the left shooter motor, ranging from -1 to 1, where negative values run the motor backwards.
	 * @param speedRight The power fed to the right shooter motor, ranging from -1 to 1, where negative values run the motor backwards.
	 */
    public void set(double speedLeft, double speedRight) {
    	setLeft(speedLeft);
    	setRight(speedRight);
    }
    
    /**
	 * Sets the shooter motors to a unified specific speed.
	 * @param speed The power fed to both shooter motors, ranging from -1 to 1, where negative values run the motors backwards.
	 */
    public void set(double speed) {
    	setLeft(speed);
    	setRight(speed);
    }
    
    /**
	 * Sets the left shooter motor to a specific speed.
	 * @param speed The power fed to the left shooter motor, ranging from -1 to 1, where negative values run the motor backwards.
	 */
    public void setLeft(double speed) {
    	currentLeftSpeed = speed;
    	motorLeft.set(speed);
    }
    
    /**
	 * Sets the right shooter motor to a specific speed.
	 * @param speed The power fed to the right shooter motor, ranging from -1 to 1, where negative values run the motor backwards.
	 */
    public void setRight(double speed) {
    	currentRightSpeed = speed;
    	motorRight.set(-1.0 * speed);
    }
    
    /**
     * Raises the shooter so that we can shoot.
     */
    public void raise() {
		solenoid.set(true);
	}
	
    /**
     * Lowers the shooter so that we can fit under the low bar.
     */
	public void lower() {
		solenoid.set(false);
	}
    
	/**
	 * @return The current value of the left shooter encoder.
	 */
    public double getLeftEncoderPosition() {
    	return motorLeft.getEncPosition();
    }
    
    /**
     * @return The current velocity of the left shooter encoder.
     */
    public double getLeftEncoderVelocity() {
    	return motorLeft.getEncVelocity();
    }
    
    /**
     * @return The current value of the right shooter encoder.
     */
    public double getRightEncoderPosition() {
    	return motorRight.getEncPosition();
    }
    
    /**
     * @return The current velocity of the right shooter velocity.
     */
    public double getRightEncoderVelocity() {
    	return motorRight.getEncVelocity();
    }
    
    /**
     * @return The solenoid that holds the shooter down.
     */
    public Solenoid getSolenoid() {
		return solenoid;
	}
	
    /**
     * @return Whether the solenoid holding the shooter down is extended.
     */
	public boolean getSolenoidValue() {
		return solenoid.get();
	}
	
	/**
	 * @return The left shooter motor.
	 */
    public CANTalon getLeftMotor() {
    	return motorLeft;
    }
    
    /**
     * @return the right shooter motor.
     */
    public CANTalon getRightMotor() {
    	return motorRight;
    }
    
	/**
	 * @return The current power being fed to the left shooter motor, ranging from -1 to 1, where negative values run the motor backwards.
	 */
    public double getLeftSpeed() {
    	return currentLeftSpeed;
    }
    
    /**
	 * @return The current power being fed to the right shooter motor, ranging from -1 to 1, where negative values run the motor backwards.
     */
    public double getRightSpeed() {
    	return currentRightSpeed;
    }
    
    /**
     * Stops the shooter motors.
     */
    public void stop() {
    	set(0);
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

