package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveStraightTime extends CommandBase {
	double speed, timeOut;
	Timer timer;
	
	public DriveStraightTime(double timeOut, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		this.speed = speed;
		this.timeOut = timeOut;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.resetGyro();
		timer.start();
		timer.reset();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.drive(Math.min(speed, speed - Math.min(0.5 * speed, 0.01 * drivetrain.getCurrentGyroDegrees())), Math.min(speed, speed + Math.max(-0.5 * speed, 0.01 * drivetrain.getCurrentGyroDegrees())));
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