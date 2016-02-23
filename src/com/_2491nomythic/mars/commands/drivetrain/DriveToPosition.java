package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

/**
 *
 */
public class DriveToPosition extends CommandBase {
	double distance;
	double initialEncoderFeetPos;
	double speed;
    public DriveToPosition(double feet, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	distance = feet;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialEncoderFeetPos = drivetrain.getLeftEncoder().getDistance();
    	drivetrain.drive(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(distance + initialEncoderFeetPos > initialEncoderFeetPos) {
    		return (drivetrain.getLeftEncoder().getDistance() > (initialEncoderFeetPos + distance));
    	}
    	else {
    		if(speed > 0) {
    			drivetrain.drive(-1.0 * speed, -1.0 * speed);
    		}
    		return (drivetrain.getLeftEncoder().getDistance() < (initialEncoderFeetPos + distance));
    	}
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
