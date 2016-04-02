package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrainWithGyro;
import com._2491nomythic.mars.commands.shooter.*;
import com._2491nomythic.mars.settings.*;
import com._2491nomythic.mars.commands.intake.RunIntakeTime;

/**
 * Aligns the robot using vision so that we can shoot into the goal
 */
public class AlignShooter extends CommandBase {
	private int state;
	private double requiredMovment, power;
	private boolean turnLeft, shooterHasRun, shooterHasShot, done;
	private RotateDrivetrainWithGyro rotateDrivetrain;
	private RaiseShooter raiseShooter;
	private LowerShooter lowerShooter;
	private RunShooterTime runShooterTime;
	private RunIntakeTime runIntakeTime;
	
	/**
	 * Aligns the robot using vision so that we can shoot into the goal
	 */
	public AlignShooter() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(grip);
		raiseShooter = new RaiseShooter();
		lowerShooter = new LowerShooter();
		runShooterTime = new RunShooterTime(10);
		runIntakeTime = new RunIntakeTime(2);
		rotateDrivetrain = new RotateDrivetrainWithGyro(0, 0, false);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
		shooterHasRun = false;
		shooterHasShot = false;
		done = false;

	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (oi.getAxisDeadzoned(ControllerMap.driveController, ControllerMap.driveLeftAxis) != 0 || oi.getAxisDeadzoned(ControllerMap.driveController, ControllerMap.driveRightAxis) != 0) this.cancel();
		switch(state) {
			case 0:
				raiseShooter.start();
				state++;
				break;
			case 1:
				if (!raiseShooter.isRunning() && !shooterHasRun) {
					runShooterTime.start(); 
					shooterHasRun = true;
					state++;
					}
				break;
			case 2:
				if (grip.getCenterX().length != 0 && !rotateDrivetrain.isRunning()) {
					if ( !(Variables.lowerPixelLimit < grip.getCenterX()[0]) || !(grip.getCenterX()[0] < Variables.upperPixelLimit)) {
						requiredMovment = (Constants.FOVPixelWidth/2 - grip.getCenterX()[0] + grip.getWidth()[0]/2)* Constants.degreesPerPixel;
						turnLeft = (requiredMovment > 0) ? true : false;
						power = 0.2;
					}
					else {
						requiredMovment = 0;
						power = 0;
						runIntakeTime.start();
						shooterHasShot = true;
						state++;
					}
					rotateDrivetrain = new RotateDrivetrainWithGyro(Math.abs(requiredMovment), power, turnLeft);
					rotateDrivetrain.start();	
				}
				break;
			case 3:
				if (shooterHasShot && !runIntakeTime.isRunning()) {
					runShooterTime.cancel();
					lowerShooter.start();
					done = true;
				}
				break;
			default:
				System.out.println("Broken at state: "+ state);
				break;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (done && !lowerShooter.isRunning());
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		rotateDrivetrain.cancel();
		runIntakeTime.cancel();
		runShooterTime.cancel();
	}
}
