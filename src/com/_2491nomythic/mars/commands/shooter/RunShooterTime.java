package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

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
		shooter.getLeftMotor().changeControlMode(TalonControlMode.Voltage);
		shooter.getRightMotor().changeControlMode(TalonControlMode.Voltage);
		shooter.getLeftMotor().setVoltageCompensationRampRate(4.0); 
		shooter.getRightMotor().setVoltageCompensationRampRate(4.0);
		shooter.getRightMotor().set(-1.0 * 12.0);
		shooter.getLeftMotor().set(-1.0 * 12.0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.get() > time);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		shooter.getLeftMotor().changeControlMode(TalonControlMode.PercentVbus);
		shooter.getRightMotor().changeControlMode(TalonControlMode.PercentVbus);
		shooter.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
