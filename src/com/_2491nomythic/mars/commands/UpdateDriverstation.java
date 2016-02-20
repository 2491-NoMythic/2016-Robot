package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.Variables;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateDriverstation extends CommandBase {
	private Timer timer;
	private double nextRun;

    public UpdateDriverstation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//    	requires(grip);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    	nextRun = timer.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get() > nextRun) {
			nextRun = nextRun + 0.1;
//			SmartDashboard.putBoolean("Tower in range: ", (Math.abs(Constants.visionCenterXValue - grip.getCenterX()[0]) < Constants.acceptableVisionCenterXDifference) && (Math.abs(Constants.visionCenterYValue - grip.getCenterY()[0]) < Constants.acceptableVisionCenterYDifference) && (Math.abs(Constants.visionAreaValue - grip.getArea()[0]) < Constants.acceptableVisionAreaDifference) && (Math.abs(Constants.visionHeightValue - grip.getHeight()[0]) < Constants.acceptableVisionHeightDifference) && (Math.abs(Constants.visionWidthValue - grip.getWidth()[0]) < Constants.acceptableVisionWidthDifference));
			Variables.shooterSpeed = SmartDashboard.getNumber("Shooter Speed");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
