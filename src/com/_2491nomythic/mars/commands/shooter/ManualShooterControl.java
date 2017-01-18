package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Variables;

import com.ctre.CANTalon.TalonControlMode;

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
		
		shooter.getLeftMotor().changeControlMode(TalonControlMode.Voltage);
		shooter.getRightMotor().changeControlMode(TalonControlMode.Voltage);
		shooter.getLeftMotor().setVoltageCompensationRampRate(Variables.shooterRampRate);
		shooter.getRightMotor().setVoltageCompensationRampRate(Variables.shooterRampRate);
		shooter.getRightMotor().set(-1.0 * Variables.shooterSpeed);
		shooter.getLeftMotor().set(Variables.shooterSpeed);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
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
