package com._2491nomythic.mars.commands.armature;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.ControllerMap;

/**
 *
 */
public class ManualArmatureControl extends CommandBase {
	boolean moveDown;

    public ManualArmatureControl(boolean moveDown) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.moveDown = moveDown;
    	requires(armature);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (moveDown) {
    		if (oi.getButton(ControllerMap.manualArmatureController, ControllerMap.manualArmatureFastButton)) {
    			armature.set(-1.0 * Constants.armatureSpeedFast);
    		}
    		else {
    			armature.set(-1.0 * Constants.armatureSpeed);
    		}
    	}
    	else {
    		if (oi.getButton(ControllerMap.manualArmatureController, ControllerMap.manualArmatureFastButton)) {
    			armature.set(Constants.armatureSpeedFast);
    		}
    		else {
    			armature.set(Constants.armatureSpeed);
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(armature.armLimitSwitch()) {
//    		armature.set(0);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	armature.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
