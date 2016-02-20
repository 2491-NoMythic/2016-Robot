package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.armature.ArmatureIntakePosition;
import com._2491nomythic.mars.commands.armature.ArmatureLowBarPosition;
import com._2491nomythic.mars.commands.armature.ArmatureUpPosition;
import com._2491nomythic.mars.commands.drivetrain.DriveToPosition;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrain;
import com._2491nomythic.mars.commands.intake.IntakeBall;
import com._2491nomythic.mars.commands.shooter.LowerShooter;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrain;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootPickupShoot extends Command {
	RaiseShooter raiseShooter;
	RunShooterTime shoot;
	DriveToPosition drive1ft;
	LowerShooter lowerShooter;
	RotateDrivetrain rotateNeg90;
	DriveToPosition drive24ft;
	RotateDrivetrain rotate90;
	ArmatureUpPosition armUp;
	ArmatureIntakePosition armIntake;
	ArmatureLowBarPosition armLowBar;
	IntakeBall intakeBall;
	DriveToPosition drive2ft;
	DriveToPosition drive19ft;
	RotateDrivetrain rotateNeg22;
    public ShootPickupShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	raiseShooter = new RaiseShooter();
    	shoot = new RunShooterTime(5);
    	drive1ft = new DriveToPosition(0.7, 1);
    	lowerShooter = new LowerShooter();
    	
    	
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
