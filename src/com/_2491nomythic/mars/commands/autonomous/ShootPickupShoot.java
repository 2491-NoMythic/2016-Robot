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
    		armLowBar.start();
    		state++;
    	case 1:
    		if(!armLowBar.isRunning()) {
    		drive13ft.start();
    		state++;
    		}
    	case 2:
    		if(!drive13ft.isRunning()){
    		rotate45.start();
    		state++;
    		}
    	case 3:
    		if(!rotate45.isRunning()){
    		drive5ft.start();
    		state++;
    		}
    	case 4:
    		if(!drive5ft.isRunning()){
    		raiseShooter.start();
    		state++;
    		}
    	case 5:
    		if(!raiseShooter.isRunning()){
    		shoot.start();
    		state++;
    		}
    	case 6:
    		if(!shoot.isRunning()){
    		rotate180.start();
    		state++;
    		}
    	case 7:
    		if(!rotate180.isRunning()){
    		drive5ft.start();
    		state++;
    		}
    	case 8:
    		if(!drive5ft.isRunning()){
    		rotate45.start();
    		state++;
    		}
    	case 9:
    		if(!rotate45.isRunning()){
    		drive13ft.start();
    		state++;
    		}
    	case 10:
    		if(!drive13ft.isRunning()){
    		armUp.start();
    		state++;
    		}
    	case 11:
    		if(!armUp.isRunning()){
    		rotate90.start();
    		state++;
    		}
    	case 12:
    		if(!rotate90.isRunning()){
    		armIntake.start();
    		state++;
    		}
    	case 13:
    		if(!armIntake.isRunning()){
    		rotate90.start();
    		state++;
    		}
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
    	raiseShooter.cancel();
    	shoot.cancel();
    	drive13ft.cancel();
    	lowerShooter.cancel();
    	rotate45.cancel();
    	rotateNeg45.cancel();
    	rotate90.cancel();
    	drive5ft.cancel();
    	rotate180.cancel();
    	armUp.cancel();
    	armIntake.cancel();
    	armLowBar.cancel();
    	intakeBall.cancel();
    }
}
