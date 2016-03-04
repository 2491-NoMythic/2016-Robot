package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.drivetrain.DriveToPosition;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 * An autonomous that drives under the Portcullis, starting from directly in front of it on the starting line
 */
public class DriveUnderPortcullis extends CommandBase {
	private ArmaturePositionSet lowerArmature;
	private DriveToPosition driveToPortcullis;
	private ArmaturePositionSet raiseArmature;
	private DriveToPosition driveThroughPortcullis;
	private int state;
	Timer timer;

	/**
	 * An autonomous that drives under the Portcullis, starting from directly in front of it on the starting line
	 */
    public DriveUnderPortcullis() {
    	lowerArmature = new ArmaturePositionSet(Constants.armatureDownPositionValue);
    	driveToPortcullis = new DriveToPosition(5.5, 0.5);
    	raiseArmature = new ArmaturePositionSet(Constants.armatureUpPositionValue);
    	driveThroughPortcullis = new DriveToPosition(7, 0.2);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (state) {
    		case 0:
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
    				raiseArmature.start();
    				state = 3;
    			}
    			break;
    		case 3:
    			if (!raiseArmature.isRunning()) {
    				driveThroughPortcullis.start();
    				state = 4;
    			}
    			break;
    		case 4:
    			break;
    		default:
    			System.out.println("Something's wrong in autonomous!  State is " + state);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == 4 && !driveThroughPortcullis.isRunning();
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
