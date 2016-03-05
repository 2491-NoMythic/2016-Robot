package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 * Shifts the drivetrain to high gear, giving it more speed but less torque
 */
public class ShiftGearVelocity extends CommandBase {
	boolean hasBeenShifted;
	Timer timer;
    public ShiftGearVelocity() {

	/**
	 * Shifts the drivetrain to high gear, giving it more speed but less torque
	 */

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer= new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {		
//		if (drivetrain.getLeftEncoderRate() > Constants.driveHighGearShiftSpeed) { //Shifts immediately if already moving
//			drivetrain.shiftToHighGear();
//			hasBeenShifted = true;
//		}
//		else {
//			hasBeenShifted = false;
//		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!hasBeenShifted) {
//   			if (drivetrain.getLeftEncoderRate() > Constants.driveHighGearShiftSpeed) { //Shifts
//    			drivetrain.shiftToHighGear();
//    			hasBeenShifted = true;
//    		}
    	}
    	else if ((drivetrain.getCurrentLeftSpeed() == 0) || (drivetrain.getCurrentRightSpeed() == 0)) { //Provides a catch for starting and stopping while this command is running
    		drivetrain.shiftToLowGear();
    		hasBeenShifted = false;
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
