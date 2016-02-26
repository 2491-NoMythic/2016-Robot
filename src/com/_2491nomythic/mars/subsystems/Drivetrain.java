package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.drivetrain.Drive;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The system used to move the robot
 */
public class Drivetrain extends Subsystem {
    private CANTalon left1, left2, left3, right1, right2, right3;
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
		left1 = new CANTalon(Constants.driveTalonLeft1Channel);
		left2 = new CANTalon(Constants.driveTalonLeft2Channel);
		left3 = new CANTalon(Constants.driveTalonLeft3Channel);
		right1 = new CANTalon(Constants.driveTalonRight1Channel);
		right2 = new CANTalon(Constants.driveTalonRight2Channel);
		right3 = new CANTalon(Constants.driveTalonRight3Channel);
		
		encoderLeft = new Encoder(Constants.driveEncoderLeftChannel1, Constants.driveEncoderLeftChannel2);
		encoderRight = new Encoder(Constants.driveEncoderRightChannel1, Constants.driveEncoderRightChannel2);
		encoderLeft.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderRight.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderLeft.reset();
		encoderRight.reset();
		
//		left1.configEncoderCodesPerRev(360);
//		right1.configEncoderCodesPerRev(360);
		
		solenoid = new Solenoid(Constants.driveSolenoidChannel);
		solenoid.set(false);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void drive(double leftSpeed, double rightSpeed) {
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}
	
	public void driveLeft(double speed) {
		left1.set(speed);
		left2.set(-1.0 * speed);
		left3.set(-1.0 * speed);
		currentLeftSpeed = speed;
	}
	
	public void driveRight(double speed) {
		right1.set(-1.0 * speed);
		right2.set(speed);
		right3.set(speed);
		currentRightSpeed = speed;
	}
	
	public void shiftToHighGear() {
		solenoid.set(true);
	}
	
	public void shiftToLowGear() {
		solenoid.set(false);
	}
	
	public void resetLeftEncoder() {
		encoderLeft.reset();
	}
	
	public void resetRightEncoder() {
		encoderRight.reset();
	}
	
//	public double getLeftEncoderPosition() {
//		return left1.getEncPosition();
//	}
//	
//	public double getLeftEncoderVelocity() {
//		return left1.getEncVelocity();
//	}
//	
//	public double getRightEncoderPosition() {
//		return right1.getEncPosition();
//	}
//	
//	public double getRightEncoderVelocity() {
//		return right1.getEncVelocity();
//	}
	
	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	public Encoder getRightEncoder() {
		return encoderRight;
	}
	
	public double getLeftEncoderDistance() {
		return encoderLeft.getDistance();
	}
	
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
	
	public Solenoid getSolenoid() {
		return solenoid;
	}
	
	public boolean getSolenoidValue() {
		return solenoid.get();
	}
	
	public CANTalon getLeft1Motor() {
		return left1;
	}
	
	public CANTalon getLeft2Motor() {
		return left2;
	}
	
	public CANTalon getLeft3Motor() {
		return left3;
	}
	
	public CANTalon getRight1Motor() {
		return right1;
	}
	
	public CANTalon getRight2Motor() {
		return right2;
	}
	
	public CANTalon getRight3Motor() {
		return right3;
	}
	
	public double getCurrentLeftSpeed() {
		return currentLeftSpeed;
	}
	
	public double getCurrentRightSpeed() {
		return currentRightSpeed;
	}
	
	public void stop() {
		drive(0, 0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
}

