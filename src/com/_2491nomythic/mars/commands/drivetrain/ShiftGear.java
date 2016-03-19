package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;


/**
 * @deprecated Shifts the drivetrain to high gear, giving it more speed but less torque
 */
public class ShiftGear extends CommandBase {
	
	/**
	 * @deprecated Shifts the drivetrain to high gear, giving it more speed but less torque
	 */
	public ShiftGear() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.shiftToHighGear();
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
		drivetrain.shiftToLowGear();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drivetrain.shiftToLowGear();
	}
}
