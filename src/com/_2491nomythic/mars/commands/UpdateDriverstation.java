package com._2491nomythic.mars.commands;

import java.util.Comparator;
import java.util.Vector;

//import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.Variables;
import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateDriverstation extends CommandBase {
	private Timer timer;
	private double nextRun;
	NIVision.ParticleFilterCriteria2 criteria[];
	NIVision.ParticleFilterOptions2 filterOptions;
	Scores scores;
	Vector<ParticleReport> particles;
	private int numOfParticles;
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double PercentAreaToImageArea;
		double Area;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.Area - this.Area);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.Area - r2.Area);
		}
	};
	public class Scores {
		double Area;
		double Aspect;
	};

    public UpdateDriverstation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//    	requires(grip);
    	timer = new Timer();
    	criteria = new NIVision.ParticleFilterCriteria2[1];
		filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
		scores = new Scores();
    	particles = new Vector<ParticleReport>();
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
			
			//Vision code starts here
			//numOfParticles = grip.getArea().length;
//	    	for(int particleIndex = 0; particleIndex < numOfParticles; particleIndex++) {
//	    		ParticleReport par = new ParticleReport();
//	    		
//	    	}
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
