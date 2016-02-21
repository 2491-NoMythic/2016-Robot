package com._2491nomythic.mars.commands.armature;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

/**
 *
 */
public class KeepArmatureStill extends CommandBase {
	double initialArmaturePosition;

    public KeepArmatureStill() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialArmaturePosition = armature.getEncoderPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if ((initialArmaturePosition - armature.getEncoderPosition()) > Constants.acceptableArmatureDifference) {
    		armature.armatureSet(-0.3);
    	}
    	else {
    		armature.stop();
    	}
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
}
