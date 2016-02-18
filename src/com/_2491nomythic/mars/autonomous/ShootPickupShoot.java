package com._2491nomythic.mars.autonomous;

import com._2491nomythic.ares.commands.shooter.RaiseShooter;
import com._2491nomythic.ares.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootPickupShoot extends Command {
	RaiseShooter raiseShooter;
	RunShooterTime shoot;
    public ShootPickupShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
