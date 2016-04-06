package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.armature.ArmatureTime;
import com._2491nomythic.mars.commands.armature.KeepArmatureStill;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import edu.wpi.first.wpilibj.Timer;

/**
 * An autonomous that drives under the Portcullis, starting from directly in front of it on the starting line
 */
public class DriveOverChevalDeFrise extends CommandBase {
	private ArmatureTime lowerArmature;
	private DriveStraightToPosition driveToCheval;
	private ArmaturePositionSet raiseArmature;
	private DriveStraightToPosition driveOverCheval;
	private KeepArmatureStill keepArmatureStill;
	private int state;
	Timer timer;
	
	/**
	 * An autonomous that drives under the Portcullis, starting from directly in front of it on the starting line
	 */
	public DriveOverChevalDeFrise() {
		lowerArmature = new ArmatureTime(0.5, 0.8); // takes in positive power
		driveToCheval = new DriveStraightToPosition(6, 0.5);
		driveOverCheval = new DriveStraightToPosition(7, 0.5);
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
				driveToCheval.start();
				state = 1;
				break;
			case 1:
				if (!driveToCheval.isRunning()) {
					keepArmatureStill.cancel();
					lowerArmature.start();
					state = 2;
				}
				break;
			case 2:
				if (!lowerArmature.isRunning()) {
					driveOverCheval.start();
					state = 3;
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
				break;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == 3 && !driveOverCheval.isRunning();
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		lowerArmature.cancel();
		driveToCheval.cancel();
		raiseArmature.cancel();
		driveOverCheval.cancel();
	}
}
