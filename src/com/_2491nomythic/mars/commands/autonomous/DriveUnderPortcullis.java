package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.armature.KeepArmatureStill;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 * An autonomous that drives under the Portcullis, starting from directly in front of it on the starting line
 */
public class DriveUnderPortcullis extends CommandBase {
	private ArmaturePositionSet lowerArmature;
	private DriveStraightToPosition driveToPortcullis;
	private ArmaturePositionSet raiseArmature;
	private DriveStraightToPosition driveThroughPortcullis;
	private KeepArmatureStill keepArmatureStill;
	private int state;
	Timer timer;
	
	/**
	 * An autonomous that drives under the Portcullis, starting from directly in front of it on the starting line
	 */
	public DriveUnderPortcullis() {
		lowerArmature = new ArmaturePositionSet(armature.getEncoderPosition() - Constants.armaturePortcullisDifference);
		driveToPortcullis = new DriveStraightToPosition(5.5, 0.5);
		raiseArmature = new ArmaturePositionSet(Constants.armatureUpPositionValue);
		driveThroughPortcullis = new DriveStraightToPosition(7, 0.3);
		timer = new Timer();
		keepArmatureStill = new KeepArmatureStill();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!lowerArmature.isRunning() && !raiseArmature.isRunning()) {
			keepArmatureStill.start();
		}
		switch (state) {
			case 0:
				keepArmatureStill.cancel();
				lowerArmature.start();
				timer.start();
				timer.reset();
				state = 1;
				break;
			case 1:
				if (timer.get() > 1) {
					driveToPortcullis.start();
					state = 2;
				}
				break;
			case 2:
				if (!driveToPortcullis.isRunning()) {
					keepArmatureStill.start();
					driveThroughPortcullis.start();
					//raiseArmature.start();
					state = 3;
				}
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
				break;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == 3 && !driveThroughPortcullis.isRunning();
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		lowerArmature.cancel();
		driveToPortcullis.cancel();
		raiseArmature.cancel();
		driveThroughPortcullis.cancel();
	}
}
