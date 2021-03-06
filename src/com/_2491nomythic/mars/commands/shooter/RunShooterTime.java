package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Variables;

import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Runs the shooter for a specified amount of time with a two second ramp-up
 */
public class RunShooterTime extends CommandBase {
	double time;
	Timer timer;
	
	/**
	 * Runs the shooter for a specified amount of time with a two second ramp-up
	 * 
	 * @param time
	 *            The amount of time for the shooter to run
	 */
	public RunShooterTime(double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(shooter);
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
		shooter.getLeftMotor().enableVoltageCompensation(true);
		shooter.getRightMotor().enableVoltageCompensation(true);
		shooter.getLeftMotor().configOpenloopRamp(Variables.shooterRampRate, 0);
		shooter.getRightMotor().configOpenloopRamp(Variables.shooterRampRate, 0);
		shooter.getRightMotor().set(ControlMode.PercentOutput, -1.0 * Variables.shooterSpeed);
		shooter.getLeftMotor().set(ControlMode.PercentOutput, Variables.shooterSpeed);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.get() > time);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		shooter.getLeftMotor().enableVoltageCompensation(false);
		shooter.getRightMotor().enableVoltageCompensation(false);
		shooter.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
