package com._2491nomythic.mars.commands.intake;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

/**
 * Runs intake forwards
 */
public class ManualTakeIn extends CommandBase {
	
	/**
	 * Runs intake forwards
	 */
	public ManualTakeIn() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(intake);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		intake.set(Constants.intakeSpeed);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		intake.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
