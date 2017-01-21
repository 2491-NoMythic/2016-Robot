package com._2491nomythic.mars.commands;


import com._2491nomythic.mars.settings.Constants;
//import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.Variables;
//import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Updates the variables being sent to and from the SmartDashboard
 */
public class UpdateDriverstation extends CommandBase {
	private Timer timer;
	private double nextRun;
	private double centerX;
	private double width;
	
	/**
	 * Updates the variables being sent to and from the SmartDashboard
	 */
	public UpdateDriverstation() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(grip);
		setRunWhenDisabled(true);
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
			// SmartDashboard.putBoolean("Tower in range: ",
			// (Math.abs(Constants.visionCenterXValue - grip.getCenterX()[0]) <
			// Constants.acceptableVisionCenterXDifference) &&
			// (Math.abs(Constants.visionCenterYValue - grip.getCenterY()[0]) <
			// Constants.acceptableVisionCenterYDifference) &&
			// (Math.abs(Constants.visionAreaValue - grip.getArea()[0]) <
			// Constants.acceptableVisionAreaDifference));
			Variables.shooterSpeed = SmartDashboard.getNumber("Shooter Power", Variables.shooterSpeed);
			Variables.shooterRampRate = SmartDashboard.getNumber("Shooter Ramp Rate", Variables.shooterRampRate);
			Variables.useLinearAcceleration = SmartDashboard.getBoolean("Use Linear Acceleration", true);
			SmartDashboard.putNumber("Left Drive Encoder", drivetrain.getLeftEncoderDistance());
			SmartDashboard.putNumber("Right Drive Encoder", drivetrain.getRightEncoderDistance());
			SmartDashboard.putNumber("Gyro Value in Degrees", drivetrain.getCurrentGyroDegrees());
			SmartDashboard.putNumber("Armature Encoder Value", armature.getEncoderPosition());
			SmartDashboard.putBoolean("Intake Limit Switch One", intake.getLimitSwitch1Value());
			SmartDashboard.putBoolean("Intake Limit Switch Two", intake.getLimitSwitch2Value());

			if(grip.getCenterX().length == 0) {
				centerX = 0;
				width = 0.01;
			}
			else {
				centerX = grip.getCenterX()[0];
				width = grip.getWidth()[0];
			}
			SmartDashboard.putNumber("Center X Value", centerX);
			SmartDashboard.putBoolean("Can Shoot", (centerX >= 262 && centerX <= 279));
			SmartDashboard.putNumber("Distace to Tower", (Constants.TargetWidth*Constants.FOVpixelWidth)/(2*width*Math.tan(Constants.cameraHorizontalViewAngle/2)));
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
