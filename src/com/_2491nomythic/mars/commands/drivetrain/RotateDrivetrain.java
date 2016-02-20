package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

/**
 *
 */
public class RotateDrivetrain extends CommandBase {
	double distance;
	double power;
	boolean turnLeft;

	public RotateDrivetrain(double angleInDegrees, double power, boolean turnLeft) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		distance = (angleInDegrees * (Math.PI / 180)) * 24.42;
		this.power = power;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(turnLeft) {
			drivetrain.drive(-1.0 * power, power);
		}
		else {
			drivetrain.drive(power, -1.0 * power);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return drivetrain.getLeftEncoderPosition() > distance;
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
