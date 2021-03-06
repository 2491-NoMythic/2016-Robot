package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

/**
 * Resets the encoders on the drive motors
 */
public class ResetDriveEncoders extends CommandBase {
	
	/**
	 * Resets the encoders on the drive motors
	 */
	public ResetDriveEncoders() {
		requires(drivetrain);
		setRunWhenDisabled(true);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.resetLeftEncoder();
		drivetrain.resetRightEncoder();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
