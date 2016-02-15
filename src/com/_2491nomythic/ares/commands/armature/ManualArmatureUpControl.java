package com._2491nomythic.ares.commands.armature;

import com._2491nomythic.ares.commands.CommandBase;
import com._2491nomythic.ares.settings.Constants;

/**
 *
 */
public class ManualArmatureUpControl extends CommandBase {

    public ManualArmatureUpControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(armature);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	armature.set(-1.0 * Constants.armatureSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(armature.armLimitSwitch()) {
		armature.set(0);
    	}
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
