package com._2491nomythic.ares.commands.drivetrain;

import com._2491nomythic.ares.commands.CommandBase;

/**
 *
 */
public class ShiftGear extends CommandBase {

    public ShiftGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (drivetrain.getLeftSolenoidValue() == true) {
    		drivetrain.shiftToLowGear();
    	}
    	else {
    		drivetrain.shiftToHighGear();
    	}
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
