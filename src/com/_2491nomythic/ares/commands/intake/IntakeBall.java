package com._2491nomythic.ares.commands.intake;

import com._2491nomythic.ares.commands.CommandBase;

/**
 *
 */
public class IntakeBall extends CommandBase {

    public IntakeBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    // button is held down and in take motor for ball will run until limit switch
    //is pushed or until button is released
    {
    	
    }
}
