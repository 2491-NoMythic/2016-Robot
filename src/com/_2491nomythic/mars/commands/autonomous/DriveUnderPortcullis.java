package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmatureDownPosition;
import com._2491nomythic.mars.commands.armature.ArmatureUpPosition;
import com._2491nomythic.mars.commands.drivetrain.DriveToPosition;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveUnderPortcullis extends CommandBase {
	private ArmatureDownPosition lowerArmature;
	private DriveToPosition driveToPortcullis;
	private ArmatureUpPosition raiseArmature;
	private DriveToPosition driveThroughPortcullis;
	private int state;
	Timer timer;

    public DriveUnderPortcullis() {
    	lowerArmature = new ArmatureDownPosition();
    	driveToPortcullis = new DriveToPosition(5.5, 0.5);
    	raiseArmature = new ArmatureUpPosition();
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
