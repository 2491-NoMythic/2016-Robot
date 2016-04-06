package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Drives the robot straight using the gyro for a specified amount of time at a specified power
 */
public class DriveStraightTime extends CommandBase {
	double speed, timeOut;
	Timer timer;
	
	/**
	 * Drives the robot straight using the gyro to a specified position at a specified power
	 * 
	 * @param timeOut
	 *            The amount of time for the robot to move
	 * @param speed
	 *            The power fed to all drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
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
		if (speed > 0) {
			drivetrain.drive(Math.min(speed, speed - Math.min(0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())), Math.min(speed, speed + Math.max(-0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())));
		}
		else {
			drivetrain.drive(Math.max(speed, speed - Math.max(0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())), Math.max(speed, speed + Math.min(-0.5 * speed, 0.1 * drivetrain.getCurrentGyroDegrees())));
		}
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
