package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrainWithGyro;
import com._2491nomythic.mars.settings.*;
/**
 * Aligns the robot using vision so that we can shoot into the goal
 */
public class AlignShooter extends CommandBase {
	private double requiredMovment, power;
	private boolean turnLeft;
	private RotateDrivetrainWithGyro rotateDrivetrain;
	
	/**
	 * Aligns the robot using vision so that we can shoot into the goal
	 */
	public AlignShooter() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(grip);
    }
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if (grip.getCenterX().length != 0) {
			requiredMovment = (240 - grip.getCenterX()[0] + grip.getWidth()[0]/2)* Constants.degreesPerPixel;
			turnLeft = (requiredMovment > 0) ? true : false;
			power = 0.2;
		}
		else {
			requiredMovment = 0;
			power = 0;
		}
		rotateDrivetrain = new RotateDrivetrainWithGyro(Math.abs(requiredMovment), power, turnLeft);
		rotateDrivetrain.start();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (oi.getAxisDeadzoned(ControllerMap.driveController, ControllerMap.driveLeftAxis) != 0 || oi.getAxisDeadzoned(ControllerMap.driveController, ControllerMap.driveRightAxis) != 0) this.cancel();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (!rotateDrivetrain.isRunning());
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		rotateDrivetrain.cancel();
	}
}
