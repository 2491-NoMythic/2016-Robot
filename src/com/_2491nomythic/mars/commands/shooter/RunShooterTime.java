package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Variables;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class RunShooterTime extends CommandBase {
	double time;
	Timer timer;

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
		shooter.set(Variables.shooterSpeed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(shooter.getLeftEncoderVelocity() > 5000) {
    		shooter.setLeft(0);
    	}
    	else {
    		shooter.setLeft(Variables.shooterSpeed);
    	}
    	if(shooter.getRightEncoderVelocity() > 5000) {
    		shooter.setRight(0);
    	}
    	else {
    		shooter.setRight(Variables.shooterSpeed);
    	}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.get() > time);
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		shooter.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
