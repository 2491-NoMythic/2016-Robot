package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.commands.drivetrain.DriveToPosition;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrain;
import com._2491nomythic.mars.commands.intake.IntakeBall;
import com._2491nomythic.mars.commands.shooter.LowerShooter;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootPickupShoot extends Command {
	int state = 0;
	RaiseShooter raiseShooter;
	RunShooterTime shoot;
	DriveToPosition drive13ft, drive5ft;
	LowerShooter lowerShooter;
	RotateDrivetrain rotateNeg45,rotate45, rotate180;
	ArmaturePositionSet armUp, armIntake, armLowBar;
	IntakeBall intakeBall;
	Timer timer;
	
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
    		rotate45.start();
    	case 2:
    		
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
