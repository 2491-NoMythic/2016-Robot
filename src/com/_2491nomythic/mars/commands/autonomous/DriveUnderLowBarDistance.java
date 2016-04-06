package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.armature.ArmatureTime;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
//import com._2491nomythic.mars.settings.Constants;

/**
 * An autonomous that drives under the low bar, starting from directly in front of it on the starting line (based on encoder values)
 */
public class DriveUnderLowBarDistance extends CommandBase {
	DriveStraightToPosition drive7Feet;
	ArmaturePositionSet moveArmToLowBarConfiguration;
	ArmatureTime lowerArmature;
	boolean hasRun;
	
	/**
	 * An autonomous that drives under the low bar, starting from directly in front of it on the starting line (based on encoder values)
	 */
	public DriveUnderLowBarDistance() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		drive7Feet = new DriveStraightToPosition(7, -0.8);
		lowerArmature = new ArmatureTime(0.5, 0.8); // takes in positive power
//		moveArmToLowBarConfiguration = new ArmaturePositionSet(Constants.armatureLowBarDifference - armature.getEncoderPosition());
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		hasRun = false;
		drive7Feet.start();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (drivetrain.getLeftEncoderDistance() > 1.2 && !hasRun) {
			lowerArmature.start();
			hasRun = true;
		}
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (!drive7Feet.isRunning() && !lowerArmature.isRunning());
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drive7Feet.cancel();
		moveArmToLowBarConfiguration.cancel();
		lowerArmature.cancel();
	}
}
