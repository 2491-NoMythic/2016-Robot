package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.commands.intake.RunIntakeTime;
import com._2491nomythic.mars.commands.shooter.LowerShooter;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.Timer;

/**
 * Shoots a loaded boulder
 */
public class Shoot extends CommandBase {
	RaiseShooter raiseShooter;
	RunShooterTime runShooter4Secs;
	RunIntakeTime runIntake2Secs;
	LowerShooter lowerShooter;
	Timer timer;
	int state;
	
	/**
	 * Shoots a loaded boulder
	 */
	public Shoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		raiseShooter = new RaiseShooter();
		runShooter4Secs = new RunShooterTime(4);
		runIntake2Secs = new RunIntakeTime(2);
		lowerShooter = new LowerShooter();
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (state) {
			case 0:
				if (shooter.isRaised() == false) {
					raiseShooter.start();
				}
				state = 1;
				break;
			case 1:
				if (!raiseShooter.isRunning()) {
					runShooter4Secs.start();
					timer.start();
					timer.reset();
					state = 2;
				}
				break;
			case 2:
				if (timer.get() > 2.5) {
					runIntake2Secs.start();
					state = 3;
				}
				break;
			case 3:
				if (!runShooter4Secs.isRunning() && !runIntake2Secs.isRunning()) {
					lowerShooter.start();
					state++;
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Something went wrong while shooting! The state is " + state);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !lowerShooter.isRunning() && state == 3;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		raiseShooter.cancel();
		runShooter4Secs.cancel();
		runIntake2Secs.cancel();
		lowerShooter.cancel();
	}
}
