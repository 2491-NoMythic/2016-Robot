package com._2491nomythic.mars.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Runs the second of two cameras and sends the image to the SmartDashboard
 */
public class RunCamera2 extends CommandBase {
	
	private Timer timer;
	private double nextRun;
	
	/**
	 * Runs the second of two cameras and sends the image to the SmartDashboard
	 */
	public RunCamera2() {
		requires(camera2);
		timer = new Timer();
		setRunWhenDisabled(true);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		camera2.startImageAcquisition();
		timer.start();
		timer.reset();
		nextRun = timer.get();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > nextRun) {
			nextRun = nextRun + 0.05;
			camera2.updateDriverstationImage();
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		camera1.stopImageAcquisition();
		timer.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
