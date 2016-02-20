package com._2491nomythic.mars.commands.armature;
import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

public class ArmatureLowBarPosition extends CommandBase{
	
	
	public ArmatureLowBarPosition() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
	requires(armature);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		armature.runMotorToPosition(Constants.armatureLowBarPositionValue);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    return (armature.getEncoderPosition() == Constants.armatureLowBarPositionValue);
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