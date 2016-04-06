package com._2491nomythic.mars.commands.intake;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 * Runs the intake forwards for a specified amount of time
 */
public class RunIntakeTime extends CommandBase {
	double time;
	Timer timer;
	
	/**
	 * Runs the intake forwards for a specified amount of time
	 * 
	 * @param time
	 *            The time for the intake to run
	 */
	public RunIntakeTime(double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(intake);
		this.time = time;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		timer.reset();
<<<<<<< Updated upstream
        intake.set(Constants.intakeSpeed);
=======
		intake.set(1.0 * Constants.intakeSpeed);
>>>>>>> Stashed changes
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > time) {
			intake.stop();
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.get() > time);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		intake.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
