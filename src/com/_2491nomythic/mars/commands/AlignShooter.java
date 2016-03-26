package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrain;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.Variables;

/**
 * Aligns the robot using vision so that we can shoot into the goal
 */
public class AlignShooter extends CommandBase {
	private double movementInDegrees;
	private boolean turnLeft;
	private RotateDrivetrain rotateDrivetrain;
	
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
		movementInDegrees = -1.0 * ((grip.getCenterX()[0] - 400)) * Constants.degreesPerPixel;
		movementInDegrees += Variables.visionHorizontalCompensation * Constants.degreesPerPixel;
		turnLeft = (movementInDegrees > 0) ? true : false;
		rotateDrivetrain = new RotateDrivetrain(movementInDegrees, 0.5, turnLeft);
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
