package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

/**
 * Moves the robot in a specified direction for a specified distance
 */
public class DriveToPosition extends CommandBase {
	double distance;
	double initialEncoderFeetPos, currentEncoderFeetPos;
	double speed;

	/**
	 * Moves the robot in a specified direction for a specified distance
	 * 
	 * @param feet
	 *            The distance for the robot to move (in feet)
	 * @param power
	 *            The power fed to all drive motors, ranging from -1 to 1, where
	 *            negative values run the motors backwards
	 */
	public DriveToPosition(double feet, double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		distance = feet;
		speed = Math.abs(power) * (feet > 0 ? 1 : -1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initialEncoderFeetPos = drivetrain.getLeftEncoderDistance();
		drivetrain.drive(speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		currentEncoderFeetPos = drivetrain.getLeftEncoderDistance();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (distance > 0) {
			if (currentEncoderFeetPos > initialEncoderFeetPos + distance) {
				drivetrain.stop();
				return true;
			}
		}
		else {
			if (currentEncoderFeetPos < initialEncoderFeetPos + distance) {
				drivetrain.stop();
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
