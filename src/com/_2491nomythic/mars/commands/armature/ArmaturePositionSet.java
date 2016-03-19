package com._2491nomythic.mars.commands.armature;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

/**
 * Moves the armature to a specified encoder position
 */
public class ArmaturePositionSet extends CommandBase {
	double position;
	
	/**
	 * Moves the armature to a specified encoder position
	 */
	public ArmaturePositionSet(double position) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.position = position;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if ((position - armature.getEncoderPosition()) > Constants.acceptableArmatureDifference) {
			// motor position is greater then where it needs to be
			armature.set(-1.0 * Constants.armatureSpeed);
		}
		else if ((armature.getEncoderPosition() - position) > Constants.acceptableArmatureDifference) {
			// motor position is less then where it needs to be
			armature.set(Constants.armatureSpeed);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Math.abs(armature.getEncoderPosition() - position) < Constants.acceptableArmatureDifference);
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
