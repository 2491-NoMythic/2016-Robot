package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.armature.LowerArmatureTime;
import com._2491nomythic.mars.commands.drivetrain.DriveTime;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An autonomous that drives under the low bar, starting from directly in front of it on the starting line (based on time)
 */
public class DriveUnderLowBarTime extends Command {
	
	DriveTime drive5SecsForwards;
	LowerArmatureTime lowerArmature;
	Timer timer;
	boolean hasRun;
	
	/**
	 * An autonomous that drives under the low bar, starting from directly in front of it on the starting line (based on time)
	 */
    public DriveUnderLowBarTime() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive5SecsForwards = new DriveTime(5, -0.7);
    	lowerArmature = new LowerArmatureTime(0.5, 0.8); //takes in positive power
    	timer = new Timer();
    	hasRun = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    	drive5SecsForwards.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 1 && !hasRun) {
    		lowerArmature.start();
    		hasRun = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!drive5SecsForwards.isRunning() && !lowerArmature.isRunning());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive5SecsForwards.cancel();
    	lowerArmature.cancel();
    }
}
