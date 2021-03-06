package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.armature.KeepArmatureStill;
import com._2491nomythic.mars.settings.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The rotating protrusion on the end of the intake system
 */
public class Armature extends Subsystem {
	private TalonSRX motor;
	private DigitalInput limitSwitch;
	private KeepArmatureStill keepArmatureStill;
	
	public static Armature instance;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static Armature getInstance() {
		if (instance == null) {
			instance = new Armature();
		}
		return instance;
	}
	
	/**
	 * The rotating protrusion on the end of the intake system
	 */
	private Armature() {
		motor = new TalonSRX(Constants.armatureChannel);
		// motor.setFeedbackDevice();
		motor.setSelectedSensorPosition(0, 0, 0);
		
		limitSwitch = new DigitalInput(Constants.armatureLimitSwitchChannel);
		
		keepArmatureStill = new KeepArmatureStill();
	}
	
	/**
	 * Sets the armature motor to a constant speed and stops the KeepArmatureStill command
	 * 
	 * @param speed
	 *            The power fed to the motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public void set(double speed) {
		if(keepArmatureStill.isRunning()) {
			keepArmatureStill.cancel();
		}
		motor.set(ControlMode.PercentOutput, speed);
	}
	
	/**
	 * Sets the armature motor to a constant speed
	 * 
	 * @param speed
	 *            The power fed to the motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public void armatureSet(double speed) {
		motor.set(ControlMode.PercentOutput, speed);
	}
	
	/**
	 * @return The current position of the armature encoder
	 */
	public double getEncoderPosition() {
		return motor.getSelectedSensorPosition(0);
	}
	
	/**
	 * @return The current velocity of the armature encoder
	 */
	public double getEncoderVelocity() {
		return motor.getSelectedSensorVelocity(0);
	}
	
	/**
	 * @return The armature motor
	 */
	public TalonSRX get() {
		return motor;
	}
	
	/**
	 * Stops the armature motor and then starts the KeepArmatureStill command
	 */
	public void stop() {
		armatureSet(0);
		keepArmatureStill.start();
	}
	
	/**
	 * @return Returns whether the armature limit switch is pressed
	 */
	public boolean getLimitSwitch() {
		return limitSwitch.get();
	}
	
	public void resetEncoder() {
		motor.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new KeepArmatureStill());
	}
}

