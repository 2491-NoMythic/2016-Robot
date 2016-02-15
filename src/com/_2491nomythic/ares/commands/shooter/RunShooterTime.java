package com._2491nomythic.ares.commands.shooter;

import com._2491nomythic.ares.commands.CommandBase;
import com._2491nomythic.ares.settings.Constants;

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
		shooter.set(Constants.shooterSpeed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(timer.get() > time) {
			shooter.stop();
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
