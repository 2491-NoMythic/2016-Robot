package com._2491nomythic.mars.commands;


//import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.Variables;
//import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateDriverstation extends CommandBase {
	private Timer timer;
	private double nextRun;
//	private int numOfParticles;
	public class ParticleInfo {
		double Area;
		double Width;
		double Height;
		double CenterX;
		double CenterY;
	}

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
//			SmartDashboard.putBoolean("Tower in range: ", (Math.abs(Constants.visionCenterXValue - grip.getCenterX()[0]) < Constants.acceptableVisionCenterXDifference) && (Math.abs(Constants.visionCenterYValue - grip.getCenterY()[0]) < Constants.acceptableVisionCenterYDifference) && (Math.abs(Constants.visionAreaValue - grip.getArea()[0]) < Constants.acceptableVisionAreaDifference));
			Variables.shooterSpeed = SmartDashboard.getNumber("Shooter Speed");
			//SmartDashboard
			//Vision code starts here
			//It runs off of grip, so we are simply taking the biggest target
//			numOfParticles = grip.getArea().length;
//			if(numOfParticles == 0) {
//				//do nothing	    		
//	    	}
//			else {
//				//grab the data values of the largest particle
//				ParticleInfo particleInfo = new ParticleInfo();
//				particleInfo.Area = grip.getArea()[0];
//				particleInfo.CenterX = grip.getCenterX()[0];
//				particleInfo.CenterY = grip.getCenterY()[0];
//				particleInfo.Height = grip.getHeight()[0];
//				particleInfo.Width = grip.getWidth()[0];
//			}
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
