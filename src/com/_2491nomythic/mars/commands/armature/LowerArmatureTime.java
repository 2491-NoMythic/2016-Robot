package com._2491nomythic.mars.commands.armature;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Lowers the armature for a specified amount of time at a specified power
 */
public class LowerArmatureTime extends CommandBase {
	Timer timer;
	double power;
	double time;
    
	/**
	 * Lowers the armature for a specified amount of time at a specified power
	 * @param power The power fed to the armature motor, ranging from -1 to 1, where negative values run the motor backwards
	 * @param time The amount of time the armature motor is run for
	 */
	public LowerArmatureTime(double power, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(armature);
    	timer = new Timer();
    	this.power = power;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    	armature.set(-1.0 * power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() > time);
    }

    // Called once after isFinished returns true
    protected void end() {
    	armature.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	armature.stop();
    }
}
