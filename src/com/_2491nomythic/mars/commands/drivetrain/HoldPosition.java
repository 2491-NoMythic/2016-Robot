package com._2491nomythic.mars.commands.drivetrain;

import com._2491nomythic.mars.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldPosition extends CommandBase {
    public HoldPosition() {
        // Use requires() here to declare subsystem dependencies
        requires(drivetrain);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.resetLeftEncoder();
    	drivetrain.resetRightEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(drivetrain.getLeftEncoderDistance() > 0.05) {
    		drivetrain.drive(-0.15);
    	}
    	else if(drivetrain.getLeftEncoderDistance() < -0.05) {
    		drivetrain.drive(0.15);
    	}
    	else {
    		drivetrain.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
