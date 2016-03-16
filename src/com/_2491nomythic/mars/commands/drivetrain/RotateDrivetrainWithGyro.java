package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

/**
 * Rotates the robot by a specified angle in a specified direction
 */
public class RotateDrivetrainWithGyro extends CommandBase {
	double distance, angleInDegrees, power;
	boolean turnLeft;

	/**
	 * Rotates the robot by a specified angle in a specified direction
	 * @param angleInDegrees The angle for the robot to turn
	 * @param power The power fed to all drive motors, ranging from 0 to 1
	 * @param turnLeft Whether or not the robot rotates to the left (false rotates to the right)
	 */
	public RotateDrivetrainWithGyro(double angleInDegrees, double power, boolean turnLeft) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		this.angleInDegrees = angleInDegrees;
		this.power = power;
		this.turnLeft = turnLeft;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(turnLeft) {
			drivetrain.drive(-1.0 * power, power);
		}
		else {
			drivetrain.drive(power, -1.0 * power);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(turnLeft) {
			return (drivetrain.getCurrentGyroDegrees() < -angleInDegrees);
		}
		else {
			return (drivetrain.getCurrentGyroDegrees() >  angleInDegrees);
		}
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
