package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.ControllerMap;
import com._2491nomythic.mars.settings.Variables;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	lastLeftPower = currentLeftPower;
    	lastRightPower = currentRightPower;
    	currentLeftPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveLeftAxis);
    	currentRightPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveRightAxis);
		double leftAcceleration = (currentLeftPower - lastLeftPower);
		double signOfLeftAcceleration = leftAcceleration / Math.abs(leftAcceleration);
//    	if(Math.abs(leftAcceleration) > Variables.accelerationSpeed) {
//    		if (Math.abs(currentLeftPower) - Math.abs(lastLeftPower) > 0) {
//    			System.out.println(currentLeftPower + " was too high, setting to " + (lastLeftPower + (Variables.accelerationSpeed * signOfLeftAcceleration)));
//    			currentLeftPower = lastLeftPower + (Variables.accelerationSpeed * signOfLeftAcceleration);
//    			
//    		}
//    		//if the difference between the numbers is positive it is going up
//    		
//    	}
//    	else {
//    		//...the power is below accel and is fine
//    	}
//    	double rightAcceleration = (currentRightPower - lastRightPower);
//    	double signOfRightAcceleration = rightAcceleration / Math.abs(rightAcceleration);
//    	if(Math.abs(rightAcceleration) > Variables.accelerationSpeed) {
//    		if (Math.abs(currentRightPower) - Math.abs(lastRightPower) > 0) {
//    			System.out.println(currentRightPower + " was too high, setting to " + (lastRightPower + (Variables.accelerationSpeed * signOfRightAcceleration)));
//    			currentRightPower = lastRightPower + (Variables.accelerationSpeed * signOfRightAcceleration);
//    		}
//    		//if the difference between the numbers is positive it is going up
//    	}
//    	else {
//    		//...the power is below 0.05 accel and is fine
//    	}
    	drivetrain.drive(currentLeftPower, currentRightPower);
    	
    	SmartDashboard.putNumber("Right Encoder Distance", drivetrain.getRightEncoderDistance());
    	SmartDashboard.putNumber("Left Encoder Distance" ,drivetrain.getLeftEncoderDistance());
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
