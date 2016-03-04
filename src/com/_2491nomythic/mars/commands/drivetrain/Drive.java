package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.ControllerMap;

/**
 * Moves the robot based on joystick tank drive control
 */
public class Drive extends CommandBase {
	double currentLeftPower, currentRightPower;
	double directionMultiplierLeft;
	double directionMultiplierRight;
	double lastRightPower;
	double lastLeftPower;

	/**
	 * Moves the robot based on joystick tank drive control
	 */
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//When the command first starts up the current power should be zero so that it always accelerates smoothly
    	currentLeftPower = 0;
    	currentRightPower = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	lastLeftPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveLeftAxis);
    	lastRightPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveRightAxis);
    	drivetrain.drive(lastLeftPower, lastRightPower);
    	lastLeftPower = currentLeftPower;
    	lastRightPower = currentRightPower;
    	currentLeftPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveLeftAxis);
    	currentRightPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveRightAxis);
    	if(Math.abs(lastLeftPower - currentLeftPower) > 0.05) {
    		currentLeftPower = lastLeftPower + 0.05;
    	}
    	else {
    		//...the power is below 0.05 accel and is fine
    	}
    	if(Math.abs(lastRightPower - currentRightPower) > 0.05) {
    		currentRightPower = lastLeftPower + 0.05;
    	}
    	else {
    		//...the power is below 0.05 accel and is fine
    	}
    	drivetrain.drive(currentLeftPower, currentRightPower);
    	
    	System.out.println(drivetrain.getLeftEncoderDistance());
    	System.out.println(drivetrain.getRightEncoderDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
