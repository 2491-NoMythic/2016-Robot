package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ShiftGearWithDelay extends CommandBase {
	private boolean isShifted, isDelayed;
	private Timer timer;
	
    public ShiftGearWithDelay() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDelayed = false;
    	
    	if (Math.abs(drivetrain.getCurrentLeftSpeed()) > 0 && Math.abs(drivetrain.getCurrentRightSpeed()) > 0) {
    		drivetrain.shiftToHighGear();
    		isShifted = true;
    	}
    	else {
    		isShifted = false;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!isShifted) {
    		if(Math.abs(drivetrain.getCurrentLeftSpeed()) > 0 && Math.abs(drivetrain.getCurrentRightSpeed()) > 0) {
    			if (!isDelayed) {
    				timer.start();
    				timer.reset();
    				isDelayed = true;
    			}
    			else if (timer.get() > 0.75) {
    				drivetrain.shiftToHighGear();
    				isShifted = true;
    				isDelayed = false;
    			}
    		}
    		else {
    			isDelayed = false;
    		}
    	}
    	else if (drivetrain.getCurrentLeftSpeed() == 0 || drivetrain.getCurrentRightSpeed() == 0) {
    		isDelayed = false;
    		isShifted = false;
    		drivetrain.shiftToLowGear();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.shiftToLowGear();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
