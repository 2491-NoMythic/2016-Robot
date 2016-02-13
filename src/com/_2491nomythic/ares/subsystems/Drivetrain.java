package com._2491nomythic.ares.subsystems;

import com._2491nomythic.ares.commands.drivetrain.Drive;
import com._2491nomythic.ares.settings.Constants;
import com._2491nomythic.ares.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    private CANTalon left1, left2, left3, right1, right2, right3;
    private Encoder encoderLeft, encoderRight;
    private Solenoid solenoidLeft, solenoidRight;
    private double currentLeftSpeed, currentRightSpeed;
    
	private static Drivetrain instance;
	
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
	
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
		
		solenoidLeft = new Solenoid(Constants.driveSolenoidLeftChannel);
		solenoidRight = new Solenoid(Constants.driveSolenoidRightChannel);
		solenoidLeft.set(false);
		solenoidRight.set(false);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void drive(double leftSpeed, double rightSpeed) {
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}
	
	public void driveLeft(double speed) {
		left1.set(-1.0 * speed);
		left2.set(-1.0 * speed);
		left3.set(speed);
		currentLeftSpeed = speed;
	}
	
	public void driveRight(double speed) {
		right1.set(speed);
		right2.set(speed);
		right3.set(-1.0 * speed);
		currentRightSpeed = speed;
	}
	
	public void shiftToHighGear() {
		solenoidLeft.set(true);
		solenoidRight.set(true);
	}
	
	public void shiftToLowGear() {
		solenoidLeft.set(false);
		solenoidRight.set(false);
	}
	
	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	public Encoder getRightEncoder() {
		return encoderRight;
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

