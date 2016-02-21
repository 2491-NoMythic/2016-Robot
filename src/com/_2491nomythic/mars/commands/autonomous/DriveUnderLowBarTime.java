package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.drivetrain.DriveTime;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUnderLowBarTime extends Command {
	DriveTime drive5SecsForeward;
    public DriveUnderLowBarTime() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive5SecsForeward = new DriveTime(5, 0.7, 0.7);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive5SecsForeward.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!drive5SecsForeward.isRunning());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive5SecsForeward.cancel();
    }
}
