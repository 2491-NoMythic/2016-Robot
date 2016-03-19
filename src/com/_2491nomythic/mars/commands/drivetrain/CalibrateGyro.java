package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Calibrates the Gyro
 */
public class CalibrateGyro extends CommandBase {
	Timer timer;
	
    public CalibrateGyro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setInterruptible(false);
    	setRunWhenDisabled(true);
    	requires(drivetrain);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    	drivetrain.calibrateGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 4;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
