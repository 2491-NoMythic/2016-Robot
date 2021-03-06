package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

/**
 * Drives the robot straight using the gyro to a specified position at a specified power
 */
public class DriveStraightToPosition extends CommandBase {
	double speed, distance, initialEncoderFeetPos, currentEncoderFeetPos;
	
	/**
	 * Drives the robot straight using the gyro to a specified position at a specified power
	 * 
	 * @param feet
	 *            The distance for the robot to move (in feet)
	 * @param speed
	 *            The power fed to all drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public DriveStraightToPosition(double feet, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		this.speed = speed;
		distance = feet;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.resetGyro();
		initialEncoderFeetPos = drivetrain.getLeftEncoderDistance();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (speed > 0) {
			drivetrain.drive(Math.min(speed, speed - Math.min(0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())), Math.min(speed, speed + Math.max(-0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())));
		}
		else {
			drivetrain.drive(Math.max(speed, speed - Math.max(0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())), Math.max(speed, speed + Math.min(-0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())));
		}
		currentEncoderFeetPos = drivetrain.getLeftEncoderDistance();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (distance > 0) {
			if (currentEncoderFeetPos > initialEncoderFeetPos + distance) {
				return true;
			}
		}
		else {
			if (currentEncoderFeetPos < initialEncoderFeetPos + distance) {
				return true;
			}
		}
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		drivetrain.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
