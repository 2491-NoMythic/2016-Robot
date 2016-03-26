package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmatureTime;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrainWithGyro;
import com._2491nomythic.mars.commands.intake.RunIntakeTime;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class CrossAndShoot extends CommandBase {
	Timer timer;
	DriveStraightToPosition drive20ft;
	ArmatureTime lowerArmature;
	RotateDrivetrainWithGyro rotate60Degrees;
	DriveStraightToPosition drive2ft;
	RunShooterTime runShooter5Secs;
	RunIntakeTime intake;
    public CrossAndShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer = new Timer();
    	drive20ft = new DriveStraightToPosition(19.88, -0.8);
    	lowerArmature = new ArmatureTime(0.5, 0.8); 
    	rotate60Degrees = new RotateDrivetrainWithGyro(-60, 0.5, false);
    	drive2ft = new DriveStraightToPosition(2, -0.8);
    	runShooter5Secs = new RunShooterTime(5);
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
