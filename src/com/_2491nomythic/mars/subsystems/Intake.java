package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.settings.Constants;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The system that loads boulders into the robot
 */
public class Intake extends Subsystem {
	private CANTalon motor;
	private DigitalInput limitSwitch1, limitSwitch2;
	
	public static Intake instance;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static Intake getInstance() {
		if (instance == null) {
			instance = new Intake();
		}
		return instance;
	}
	
	/**
	 * The system that loads boulders into the robot
	 */
	private Intake() {
		motor = new CANTalon(Constants.intakeChannel);
		
		limitSwitch1 = new DigitalInput(Constants.intakeLimitSwitch1Channel);
		limitSwitch2 = new DigitalInput(Constants.intakeLimitSwitch2Channel);
	}
	
	/**
	 * Sets the intake motor to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the intake motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public void set(double speed) {
		motor.set(-1.0 * speed);
	}
	
	/**
	 * @return The intake motor
	 */
	public CANTalon get() {
		return motor;
	}
	
	/**
	 * @return Whether the first of two limit switches is pressed
	 */
	public boolean getLimitSwitch1Value() {
		return limitSwitch1.get();
	}
	
	/**
	 * @return Whether the second of two limit switches is pressed
	 */
	public boolean getLimitSwitch2Value() {
		return limitSwitch2.get();
	}
	
	/**
	 * Stops the intake motor
	 */
	public void stop() {
		set(0);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}

