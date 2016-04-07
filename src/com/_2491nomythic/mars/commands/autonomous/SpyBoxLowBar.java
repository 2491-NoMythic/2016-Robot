package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.armature.ArmatureTime;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrainWithGyro;
import com._2491nomythic.mars.commands.intake.RunIntakeTime;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpyBoxLowBar extends Command {
	RaiseShooter raiseShooter;
	RunShooterTime runShooter;
	RunIntakeTime intake;
	RotateDrivetrainWithGyro turnLeft;
	DriveStraightToPosition drive18ft;
	ArmatureTime lowerArmature;
	int state;
    public SpyBoxLowBar() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	raiseShooter = new RaiseShooter();
    	runShooter = new RunShooterTime(8);
    	intake = new RunIntakeTime(2);
    	turnLeft = new RotateDrivetrainWithGyro(67, 1.0, true);
    	lowerArmature = new ArmatureTime(0.5, 0.8); 
    	drive18ft = new DriveStraightToPosition(-18, -1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state) {
    		case 0:
    			raiseShooter.start();
    			state++;
    			break;
    		case 1:
    			if(!raiseShooter.isRunning()) {
    			runShooter.start();
    			state++;
    			}
    			break;
    		case 2:
    			Timer.delay(2);
    			intake.start();
    			state++;
    			break;
    		case 3:
    			if(!intake.isRunning()) {
    				turnLeft.start();
    				state++;
    			}
    			break;
    		case 4:
    			if(!turnLeft.isRunning()) {
    				drive18ft.start();
    				state++;
    			}
    			break;
    		case 5:
    			Timer.delay(1.5);
    			lowerArmature.start();
    			state++;
    			break;
    		case 6:
    			break;
    		default:
    			System.out.println("Something is wrong in auto. State: " + state);
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
