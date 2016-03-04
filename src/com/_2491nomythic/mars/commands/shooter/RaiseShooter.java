package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;

/**
 * Raises the shooter using the solenoid
 */
public class RaiseShooter extends CommandBase {

	/**
	 * Raises the shooter using the solenoid
	 */
    public RaiseShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.raise();
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
