package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import com._2491nomythic.mars.settings.Constants;

/**
 *
 */
public class DriveUnderLowBarDistance extends CommandBase {
	DriveStraightToPosition drive7Feet;
	ArmaturePositionSet moveArmToLowBarConfiguration;
	boolean hasRun;
    public DriveUnderLowBarDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive7Feet = new DriveStraightToPosition(7, -0.8);
    	moveArmToLowBarConfiguration = new ArmaturePositionSet(Constants.armatureLowBarDifference - armature.getEncoderPosition());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hasRun = false;
    	drive7Feet.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(drivetrain.getLeftEncoderDistance() > 1.2 && !hasRun) {
    		moveArmToLowBarConfiguration.start();
    		hasRun = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!drive7Feet.isRunning() && !moveArmToLowBarConfiguration.isRunning());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive7Feet.cancel();
    	moveArmToLowBarConfiguration.cancel();
    }
}
