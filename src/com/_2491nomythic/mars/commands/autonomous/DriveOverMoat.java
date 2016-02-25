package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;

import com._2491nomythic.mars.commands.drivetrain.DriveToPosition;


/**
 *
 */
public class DriveOverMoat extends CommandBase {
	DriveToPosition drive7Feet;
	DriveToPosition drive8Feet;

    public DriveOverMoat() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive7Feet = new DriveToPosition(7,0.5);
    	drive8Feet = new DriveToPosition(8,1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive7Feet.start();
    	if(!drive7Feet.isRunning()){
    		drivetrain.shiftToHighGear();
    		drive8Feet.start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!drive8Feet.isRunning());
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.shiftToLowGear();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive7Feet.cancel();
    	drive8Feet.cancel();
    	end();
    }
}
