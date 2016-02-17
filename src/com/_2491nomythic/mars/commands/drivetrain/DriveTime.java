package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class DriveTime extends CommandBase {
	private Timer timer;
	double timeOut;
	double leftPower;
	double rightPower;
    public DriveTime(double timeOut, double leftPower, double rightPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	this.timeOut = timeOut;
    	this.leftPower = leftPower;
    	this.rightPower = rightPower;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
		drivetrain.drive(leftPower, rightPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > timeOut;
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
