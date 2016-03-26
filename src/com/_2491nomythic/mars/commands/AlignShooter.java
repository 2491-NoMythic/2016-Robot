package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrainWithGyro;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.Variables;

/**
 * Aligns the robot using vision so that we can shoot into the goal
 */
public class AlignShooter extends CommandBase {
	private double movementInDegrees;
	private boolean turnLeft;
	private RotateDrivetrainWithGyro rotateDrivetrain;
	
	/**
	 * Aligns the robot using vision so that we can shoot into the goal
	 */
	public AlignShooter() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		 requires(grip);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		movementInDegrees = (400 - grip.getCenterX()[0]) * Constants.degreesPerPixel;
		movementInDegrees += 34 * Constants.degreesPerPixel;
		turnLeft = (movementInDegrees > 0) ? true : false;
		rotateDrivetrain = new RotateDrivetrainWithGyro(Math.abs(movementInDegrees), 0.3, turnLeft);
		rotateDrivetrain.start();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (!rotateDrivetrain.isRunning());
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		rotateDrivetrain.cancel();
	}
}
