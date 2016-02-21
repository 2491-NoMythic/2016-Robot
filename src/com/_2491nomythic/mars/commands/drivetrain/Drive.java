package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.ControllerMap;

/**
 *
 */
public class Drive extends CommandBase {
	double leftPower, rightPower;
	double directionMultiplierLeft;
	double directionMultiplierRight;

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveLeftAxis);
    	rightPower = oi.getAxisDeadzonedSquared(ControllerMap.driveController, ControllerMap.driveRightAxis);
    	directionMultiplierLeft = leftPower / Math.abs(leftPower); //Multiply by -1 or 1 depending on joystick input
    	directionMultiplierRight = rightPower / Math.abs(rightPower); //Multiply by -1 or 1 depending on joystick input
    	drivetrain.drive(directionMultiplierLeft * (Math.pow(2, Math.abs(leftPower) * 10)/1024), directionMultiplierRight * (Math.pow(2, Math.abs(rightPower) * 10)/1024));
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
