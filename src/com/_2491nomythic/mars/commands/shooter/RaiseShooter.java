package com._2491nomythic.mars.commands.shooter;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Raises the shooter using the solenoid
 */
public class RaiseShooter extends CommandBase {
	Timer timer;
	boolean hasBeenLocked;
	
	/**
	 * Raises the shooter using the solenoid
	 */
	public RaiseShooter() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(shooter);
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		setInterruptible(false);
		hasBeenLocked = false;
		shooter.unlock();
		shooter.raise();
		timer.start();
		timer.reset();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > 0.75 && !hasBeenLocked) {
			shooter.lock();
			hasBeenLocked = true;
			setInterruptible(true);
			System.out.println("Shooter raised");
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() > 1 && hasBeenLocked;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
