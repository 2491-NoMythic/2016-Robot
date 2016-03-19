package com._2491nomythic.mars.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Basically just runs a Timer.delay()
 */
public class Delay extends CommandBase {
	Timer timer;
	double time;
	
	/**
	 * Basically just runs a Timer.delay()
	 */
	public Delay(double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.time = time;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		timer.reset();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() >= time;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
