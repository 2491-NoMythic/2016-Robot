package com._2491nomythic.ares.commands.drivetrain;

import com._2491nomythic.ares.commands.CommandBase;
import com._2491nomythic.ares.settings.Constants;

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
    	initialEncoderFeetPos = drivetrain.getLeftEncoderPosition() * Constants.driveEncoderToFeet;
    	drivetrain.drive(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.getLeftEncoderPosition() * Constants.driveEncoderToFeet < (initialEncoderFeetPos + distance);
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
