package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.drivetrain.Drive;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.subsystems.Drivetrain;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The system used to move the robot
 */
public class Drivetrain extends Subsystem {
	private Gyro gyro;
	private TalonSRX left1, left2, left3, right1, right2, right3;
	private Encoder encoderLeft, encoderRight;
	private Solenoid solenoid;
	private double currentLeftSpeed, currentRightSpeed;
	
	private static Drivetrain instance;
	
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
	
	/**
	 * The system used to move the robot
	 */
	private Drivetrain() {
		left1 = new TalonSRX(Constants.driveTalonLeft1Channel);
		left2 = new TalonSRX(Constants.driveTalonLeft2Channel);
		left3 = new TalonSRX(Constants.driveTalonLeft3Channel);
		right1 = new TalonSRX(Constants.driveTalonRight1Channel);
		right2 = new TalonSRX(Constants.driveTalonRight2Channel);
		right3 = new TalonSRX(Constants.driveTalonRight3Channel);
		
		encoderLeft = new Encoder(Constants.driveEncoderLeftChannel1, Constants.driveEncoderLeftChannel2, false, CounterBase.EncodingType.k1X);
		encoderRight = new Encoder(Constants.driveEncoderRightChannel1, Constants.driveEncoderRightChannel2, false, CounterBase.EncodingType.k1X);
		encoderLeft.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderRight.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderLeft.reset();
		encoderRight.reset();
		
		// left1.configEncoderCodesPerRev(360);
		// right1.configEncoderCodesPerRev(360);
		
		solenoid = new Solenoid(Constants.driveSolenoidChannel);
		solenoid.set(false);
		
		gyro = new AnalogGyro(Constants.gyroChannel);
		gyro.calibrate();
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	/**
	 * Sets the drive motors to independent specific speeds
	 * 
	 * @param leftSpeed
	 *            The power fed to the left drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 * @param rightSpeed
	 *            The power fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void drive(double leftSpeed, double rightSpeed) {
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}
	
	/**
	 * Sets the drive motors to a unified specific speed
	 * 
	 * @param speed
	 *            The power fed to all drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void drive(double speed) {
		driveLeft(speed);
		driveRight(speed);
	}
	
	/**
	 * Sets the left drive motors to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the left drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void driveLeft(double speed) {
		left1.set(ControlMode.PercentOutput, -1.0 * speed);
		left2.set(ControlMode.PercentOutput, speed);
		left3.set(ControlMode.PercentOutput, speed);
		currentLeftSpeed = speed;
	}
	
	/**
	 * Sets the right drive motors to a specific speed
	 * 
	 * @param speed
	 *            The power fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public void driveRight(double speed) {
		right1.set(ControlMode.PercentOutput, speed);
		right2.set(ControlMode.PercentOutput, -1.0 * speed);
		right3.set(ControlMode.PercentOutput, -1.0 * speed);
		currentRightSpeed = speed;
	}
	
	/**
	 * Shifts the drivetrain to high gear, giving it more speed but less torque
	 */
	public void shiftToHighGear() {
		solenoid.set(true);
	}
	
	/**
	 * Shifts the drivetrain to low gear, giving it more torque but less speed
	 */
	public void shiftToLowGear() {
		solenoid.set(false);
	}
	
	/**
	 * Resets the left drive encoder value to 0
	 */
	public void resetLeftEncoder() {
		encoderLeft.reset();
	}
	
	/**
	 * Resets the right drive encoder value to 0
	 */
	public void resetRightEncoder() {
		encoderRight.reset();
	}
	
	/**
	 * @return The left drive encoder
	 */
	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	/**
	 * @return the right drive encoder
	 */
	public Encoder getRightEncoder() {
		return encoderRight;
	}
	
	/**
	 * @return The value of the left drive encoder
	 */
	public double getLeftEncoderDistance() {
		return encoderLeft.getDistance();
	}
	
	/**
	 * @return The value of the right drive encoder
	 */
	public double getRightEncoderDistance() {
		return encoderRight.getDistance();
	}
	
	/**
	 * @return The speed of the left motor in feet per second
	 */
	public double getLeftEncoderRate() {
		return encoderLeft.getRate();
	}
	
	/**
	 * @return The speed of the right motor in feet per second
	 */
	public double getRightEncoderRate() {
		return encoderRight.getRate();
	}
	
	/**
	 * @return The solenoid that shifts the drive gears
	 */
	public Solenoid getSolenoid() {
		return solenoid;
	}
	
	/**
	 * @return Whether the drive solenoid is extended
	 */
	public boolean getSolenoidValue() {
		return solenoid.get();
	}
	
	/**
	 * @return The first of three drive motors on the left side of the robot
	 */
	public TalonSRX getLeft1Motor() {
		return left1;
	}
	
	/**
	 * @return The second of three drive motors on the left side of the robot
	 */
	public TalonSRX getLeft2Motor() {
		return left2;
	}
	
	/**
	 * @return The third of three drive motors on the left side of the robot
	 */
	public TalonSRX getLeft3Motor() {
		return left3;
	}
	
	/**
	 * @return The first of three drive motors on the right side of the robot
	 */
	public TalonSRX getRight1Motor() {
		return right1;
	}
	
	/**
	 * @return The second of three drive motors on the right side of the robot
	 */
	public TalonSRX getRight2Motor() {
		return right2;
	}
	
	/**
	 * @return The third of three drive motors on the right side of the robot
	 */
	public TalonSRX getRight3Motor() {
		return right3;
	}
	
	/**
	 * @return The current power being fed to the left drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public double getCurrentLeftSpeed() {
		return currentLeftSpeed;
	}
	
	/**
	 * @return The current power being fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public double getCurrentRightSpeed() {
		return currentRightSpeed;
	}
	
	/**
	 * @return The angle of the gyroscope from last reset or calibration
	 */
	public double getCurrentGyroDegrees() {
		return gyro.getAngle();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public void calibrateGyro() {
		gyro.calibrate();
	}
	
	/**
	 * Stops the drive motors
	 */
	public void stop() {
		drive(0);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Drive());
	}
}

