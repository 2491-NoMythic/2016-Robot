package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;


/**
 * Locks the shooter in place to avoid recoil while shooting
 */
public class LockPiston extends CommandBase {

	/**
	 * Locks the shooter in place to avoid recoil while shooting
	 */
	public LockPiston() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.lock();
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
