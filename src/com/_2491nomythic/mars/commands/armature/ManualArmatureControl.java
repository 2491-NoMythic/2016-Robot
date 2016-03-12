package com._2491nomythic.mars.commands.armature;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.ControllerMap;

/**
 * Moves the armature in a specified direction
 */
public class ManualArmatureControl extends CommandBase {
	boolean moveDown;
	
	/**
	 * Moves the armature in a specified direction
	 * @param moveDown Whether the armature moves down (false moves the armature up)
	 */
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
    			armature.set(-1.0 * Constants.armatureSpeed); //Zach wanted a slow button instead of a fast button
    		}
    		else {
    			armature.set(-1.0 * Constants.armatureSpeedFast);
    		}
    	}
    	else {
    		if (oi.getButton(ControllerMap.manualArmatureController, ControllerMap.manualArmatureFastButton)) {
    			armature.set(Constants.armatureSpeed);//Zach wanted a slow button instead of a fast button
    		}
    		else {
    			armature.set(Constants.armatureSpeedFast);
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(armature.getLimitSwitch()) {
//    		armature.set(0);
//    	}
    	//System.out.println(armature.getEncoderPosition());
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
