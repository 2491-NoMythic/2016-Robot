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
	DriveToPosition drive1ft, drive2ft, drive24ft, drive19ft;
	LowerShooter lowerShooter;
	RotateDrivetrain rotateNeg90,rotate90, rotateNeg22;
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
    	drive1ft = new DriveToPosition(0.7, 1);
    	lowerShooter = new LowerShooter();
    	rotate90 = new RotateDrivetrain(90, 0.7, true);
    	rotateNeg90 = new RotateDrivetrain(90, 0.7, false);
    	drive24ft = new DriveToPosition(0.7, 24);
    	armUp = new ArmaturePositionSet(Constants.armatureUpPositionValue);
    	armIntake = new ArmaturePositionSet(Constants.armatureIntakePositionValue);
    	armLowBar = new ArmaturePositionSet(Constants.armatureLowBarPositionValue);
    	intakeBall = new IntakeBall();
    	drive2ft = new DriveToPosition(0.7, 2);
    	drive19ft = new DriveToPosition(0.7, 19);
    	rotateNeg22 = new RotateDrivetrain(22, 0.7, false);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state) {
    	case 0:
    		raiseShooter.start();
    		
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
