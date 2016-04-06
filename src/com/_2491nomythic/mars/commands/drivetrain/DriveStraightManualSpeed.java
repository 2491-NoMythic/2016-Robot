package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.ControllerMap;

/**
 * Drives the robot straight using the gyro at a power specified by the right drive axis on the driver controller
 */
public class DriveStraightManualSpeed extends CommandBase {
	double speed;
	
	/**
	 * Drives the robot straight using the gyro at a power specified by the right drive axis on the driver controller
	 */
	public DriveStraightManualSpeed() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.resetGyro();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		speed = -1.0 * oi.getAxis(ControllerMap.driveController, ControllerMap.driveRightAxis);
		if (speed > 0) {
			drivetrain.drive(Math.min(speed, speed - Math.min(0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())), Math.min(speed, speed + Math.max(-0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())));
		}
		else {
			drivetrain.drive(Math.max(speed, speed - Math.max(0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())), Math.max(speed, speed + Math.min(-0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())));
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
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
