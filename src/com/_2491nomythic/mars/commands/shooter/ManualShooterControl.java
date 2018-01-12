package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Variables;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Runs the shooter with a two second ramp-up
 */
public class ManualShooterControl extends CommandBase {
	
	/**
	 * Runs the shooter with a two second ramp-up
	 */
	public ManualShooterControl() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(shooter);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if(shooter.getLeftEncoderVelocity() > 5000) {
		// shooter.setLeft(0);
		// }
		// else {
		// shooter.setLeft(Variables.shooterSpeed);
		// }
		// if(shooter.getRightEncoderVelocity() > 5000) {
		// shooter.setRight(0);
		// }
		// else {
		// shooter.setRight(Variables.shooterSpeed);
		// }
		
		shooter.getLeftMotor().enableVoltageCompensation(true);
		shooter.getRightMotor().enableVoltageCompensation(true);
		shooter.getLeftMotor().configOpenloopRamp(Variables.shooterRampRate, 0);
		shooter.getRightMotor().configOpenloopRamp(Variables.shooterRampRate, 0);
		shooter.getRightMotor().set(ControlMode.PercentOutput, -1.0 * Variables.shooterSpeed);
		shooter.getLeftMotor().set(ControlMode.PercentOutput, Variables.shooterSpeed);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
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
