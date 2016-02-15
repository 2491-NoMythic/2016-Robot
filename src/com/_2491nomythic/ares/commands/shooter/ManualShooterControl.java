package com._2491nomythic.ares.commands.shooter;

import com._2491nomythic.ares.commands.CommandBase;
import com._2491nomythic.ares.settings.Constants;

/**
 *
 */
public class ManualShooterControl extends CommandBase {

    public ManualShooterControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.set(Constants.shooterSpeed);
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
    	shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
