package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An autonomous that drives over the ramparts, starting from directly in front of them on the starting line
 */
public class DriveOverRamparts extends Command {
	DriveStraightToPosition drive13Feet;
	
	/**
	 * An autonomous that drives over the ramparts, starting from directly in front of them on the starting line
	 */
	public DriveOverRamparts() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		drive13Feet = new DriveStraightToPosition(13, 0.7);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drive13Feet.start();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (!drive13Feet.isRunning());
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drive13Feet.cancel();
	}
}
