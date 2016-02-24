package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ShiftGear extends CommandBase {
	boolean hasBeenDelayed, hasBeenShifted;
	Timer timer;
	
    public ShiftGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer= new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		hasBeenDelayed = false;
		
		if ((Math.abs(drivetrain.getCurrentLeftSpeed()) > 0) && (Math.abs(drivetrain.getCurrentRightSpeed()) > 0)) { //Shifts immediately if already moving
			drivetrain.shiftToHighGear();
			hasBeenShifted = true;
		}
		else {
			hasBeenShifted = false;
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!hasBeenShifted) {
    		if ((Math.abs(drivetrain.getCurrentLeftSpeed()) > 0) && (Math.abs(drivetrain.getCurrentRightSpeed()) > 0)) {
    			if (!hasBeenDelayed) { //Delays the shift
    				timer.start();
    				timer.reset();
    				hasBeenDelayed = true;
    			}
    			else if (timer.get() > Constants.driveHighGearDelay) { //Shifts
    				drivetrain.shiftToHighGear();
    				hasBeenShifted = true;
    				hasBeenDelayed = false;
    			}
    		}
    		else { //stops the delay because the drivetrain is stopped
				hasBeenDelayed = false;
			}
    	}
    	else if ((drivetrain.getCurrentLeftSpeed() == 0) || (drivetrain.getCurrentRightSpeed() == 0)) { //Provides a catch for starting and stopping while this command is running
    		drivetrain.shiftToLowGear();
    		hasBeenShifted = false;
    		hasBeenDelayed = false;
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
