package com._2491nomythic.robot.commands.shooter;

import com._2491nomythic.robot.commands.CommandBase;
import com._2491nomythic.robot.settings.ControllerMap;
import com._2491nomythic.robot.settings.Variables;

/**
 *
 */
public class Shoot extends CommandBase {
	private double leftPower, rightPower;

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftPower = oi.getAxisDeadzonedSquared(ControllerMap.shooterController, ControllerMap.shooterPowerAxis);
    	rightPower = leftPower * Variables.shooterRightToLeftRatio;
    	shooter.setShooter(leftPower, -1.0 * rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.setShooter(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
