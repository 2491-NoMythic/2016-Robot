package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 * Moves the robot in a specified direction for a specified amount of time
 */
public class DriveTime extends CommandBase {
	private Timer timer;
	double timeOut;
	double leftPower;
	double rightPower;
	
	/**
	 * Moves the robot in a specified direction for a specified amount of time
	 * 
	 * @param timeOut
	 *            The amount of time for the robot to move
	 * @param leftPower
	 *            The power fed to the left drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 * @param rightPower
	 *            The power fed to the right drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public DriveTime(double timeOut, double leftPower, double rightPower) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		this.timeOut = timeOut;
		this.leftPower = leftPower;
		this.rightPower = rightPower;
		timer = new Timer();
	}
	
	/**
	 * Moves the robot in a specified direction for a specified amount of time
	 * 
	 * @param timeOut
	 *            The amount of time for the robot to move
	 * @param power
	 *            The power fed to all drive motors, ranging from -1 to 1, where negative values run the motors backwards
	 */
	public DriveTime(double timeOut, double power) {
		requires(drivetrain);
		this.timeOut = timeOut;
		this.leftPower = power;
		this.rightPower = power;
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
