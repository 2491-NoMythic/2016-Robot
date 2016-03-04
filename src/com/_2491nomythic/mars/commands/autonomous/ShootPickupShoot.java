package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.drivetrain.DriveToPosition;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrain;
import com._2491nomythic.mars.commands.intake.IntakeBall;
import com._2491nomythic.mars.commands.shooter.LowerShooter;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 * <b>[In Progress]</b> An autonomous that shoots a ball from the spy zone, then crosses under the low bar, picks up another ball, and comes back to shoot again
 */
public class ShootPickupShoot extends CommandBase {
	int state = 0;
	RaiseShooter raiseShooter;
	RunShooterTime shoot;
	DriveToPosition drive13ft, drive5ft;
	LowerShooter lowerShooter;
	RotateDrivetrain rotateNeg45,rotate45, rotate90, rotate180;
	ArmaturePositionSet armUp, armIntake, armLowBar;
	IntakeBall intakeBall;
	Timer timer;
	
	/**
	 * <b>[In Progress]</b> An autonomous that shoots a ball from the spy zone, then crosses under the low bar, picks up another ball, and comes back to shoot again
	 */
    public ShootPickupShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	state = 0;
    	timer = new Timer();
    	raiseShooter = new RaiseShooter();
    	shoot = new RunShooterTime(5);
    	drive13ft = new DriveToPosition(13,0.7);
    	lowerShooter = new LowerShooter();
    	rotate45 = new RotateDrivetrain(45, 0.7, true);
    	rotateNeg45 = new RotateDrivetrain(45, 0.7, false);
    	rotate90 = new RotateDrivetrain(90, 0.7, true);
    	drive5ft = new DriveToPosition(5,0.7);
    	rotate180 = new RotateDrivetrain(180,0.7,true);
    	armUp = new ArmaturePositionSet(Constants.armatureUpPositionValue);
    	armIntake = new ArmaturePositionSet(Constants.armatureIntakePositionValue);
    	armLowBar = new ArmaturePositionSet(Constants.armatureLowBarPositionValue);
    	intakeBall = new IntakeBall();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state) {
    	case 0:
    		drive13ft.start();
    	case 1:
    		rotateNeg45.start();
    	case 2:
    		drive5ft.start();
    	case 3:
    		rotate180.start();
    	case 4:
    		raiseShooter.start();
    	case 5:
    		shoot.start();
    	case 6:
    		lowerShooter.start();
    	case 7:
    		drive5ft.start();
    	case 8:
    		rotate45.start();
    	case 9:
    		drive13ft.start();
    	case 10:
    		rotate90.start();
    	case 11:
    		intakeBall.start();
    	case 12:
    		rotate90.start();
    	case 13:
    		drive13ft.start();
    	case 14:
    		rotate45.start();
    	case 15:
    		drive5ft.start();
    	case 16:
    		raiseShooter.start();
    	case 17:
    		shoot.start();
    	}
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
