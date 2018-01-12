package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.settings.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The system that shoots boulders into the high goal
 */
public class Shooter extends Subsystem {
	private TalonSRX motorLeft, motorRight;
	private Solenoid shootSolenoid, lockSolenoid;
	private Relay photonCannon;
	private double currentLeftSpeed, currentRightSpeed;
	public static Shooter instance;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}
		return instance;
	}
	
	/**
	 * The system that shoots boulders into the high goal
	 */
	private Shooter() {
		motorLeft = new TalonSRX(Constants.shooterTalonLeftChannel);
		motorRight = new TalonSRX(Constants.shooterTalonRightChannel);
		
		motorLeft.setSelectedSensorPosition(0, 0, 0);
		
		shootSolenoid = new Solenoid(Constants.shooterShootSolenoidChannel);
		lockSolenoid = new Solenoid(Constants.shooterLockSolenoidChannel);
		
		photonCannon = new Relay(Constants.shooterPhotonCannonChannel);
	}
	
	/**
	 * Sets the shooter motors to independent specific speeds
	 * 
	 * @param speedLeft
	 *            The power fed to the left shooter motor, ranging from -1 to 1, where negative values run the motor backwards
	 * @param speedRight
	 *            The power fed to the right shooter motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public void set(double speedLeft, double speedRight) {
		setLeft(speedLeft);
		setRight(speedRight);
	}
	
	/**
	 * Sets the shooter motors to a unified specific speed
	 * 
	 * @param speed
	 *            The power fed to both shooter motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void set(double speed) {
		setLeft(speed);
		setRight(speed);
	}
	
	/**
	 * Sets the left shooter motor to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the left shooter motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public void setLeft(double speed) {
		currentLeftSpeed = speed;
		motorLeft.set(ControlMode.PercentOutput, -1.0 * speed);
	}
	
	/**
	 * Sets the right shooter motor to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the right shooter motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public void setRight(double speed) {
		currentRightSpeed = speed;
		motorRight.set(ControlMode.PercentOutput, -1.0 * speed);
	}
	
	/**
	 * Raises the shooter so that we can shoot
	 */
	public void raise() {
		shootSolenoid.set(true);
	}
	
	/**
	 * Lowers the shooter so that we can fit under the low bar
	 */
	public void lower() {
		shootSolenoid.set(false);
	}
	
	/**
	 * Locks the shooter in place to avoid recoil while shooting
	 */
	public void lock() {
		lockSolenoid.set(true);
	}
	
	/**
	 * Unlocks the shooter so it can be moved by the main solenoid
	 */
	public void unlock() {
		lockSolenoid.set(false);
	}
	
	/**
	 * Turns on the photon cannon that helps the drivers aim
	 */
	public void turnOnPhotonCannon() {
		photonCannon.set(Relay.Value.kForward);
	}
	
	/**
	 * Turns off the photon cannon that helps the drivers aim
	 */
	public void turnOffPhotonCannon() {
		photonCannon.set(Relay.Value.kOff);
	}
	
	public boolean getPhotonCannonValue() {
		if (photonCannon.get() == Relay.Value.kForward) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return The current value of the left shooter encoder
	 */
	public double getLeftEncoderPosition() {
		return motorLeft.getSelectedSensorPosition(0);
	}
	
	/**
	 * @return The current velocity of the left shooter encoder
	 */
	public double getLeftEncoderVelocity() {
		return motorLeft.getSelectedSensorVelocity(0);
	}
	
	/**
	 * @return The current value of the right shooter encoder
	 */
	public double getRightEncoderPosition() {
		return motorRight.getSelectedSensorPosition(0);
	}
	
	/**
	 * @return The current velocity of the right shooter velocity
	 */
	public double getRightEncoderVelocity() {
		return motorRight.getSelectedSensorVelocity(0);
	}
	
	/**
	 * @return The solenoid that holds the shooter down
	 */
	public Solenoid getShootSolenoid() {
		return shootSolenoid;
	}
	
	/**
	 * @return The solenoid that locks the shooter in place
	 */
	public Solenoid getLockSolenoid() {
		return lockSolenoid;
	}
	
	/**
	 * @return Whether the solenoid holding the shooter down is extended
	 */
	public boolean isRaised() {
		return shootSolenoid.get();
	}
	
	/**
	 * @return Whether the solenoid locking the shooter in place is extended
	 */
	public boolean isLocked() {
		return lockSolenoid.get();
	}
	
	/**
	 * @return The left shooter motor
	 */
	public TalonSRX getLeftMotor() {
		return motorLeft;
	}
	
	/**
	 * @return the right shooter motor
	 */
	public TalonSRX getRightMotor() {
		return motorRight;
	}
	
	/**
	 * @return The current power being fed to the left shooter motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public double getLeftSpeed() {
		return currentLeftSpeed;
	}
	
	/**
	 * @return The current power being fed to the right shooter motor, ranging from -1 to 1, where negative values run the motor backwards
	 */
	public double getRightSpeed() {
		return currentRightSpeed;
	}
	
	/**
	 * Stops the shooter motors
	 */
	public void stop() {
		set(0);
	}
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}

