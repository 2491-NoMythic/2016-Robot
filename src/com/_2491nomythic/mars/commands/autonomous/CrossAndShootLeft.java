package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.AlignShooter;
import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import com._2491nomythic.mars.commands.drivetrain.DriveTime;
import com._2491nomythic.mars.commands.intake.RunIntakeTime;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class CrossAndShootLeft extends CommandBase {
	DriveStraightToPosition drive18ft;
	DriveTime rotateLeft;
	AlignShooter alignShooterOne;
	AlignShooter alignShooterTwo;
	RaiseShooter raiseShooter;
	RunShooterTime runShooter;
	RunIntakeTime runIntake;
	double overallMovement;
	int state;
    public CrossAndShootLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive18ft = new DriveStraightToPosition(13,-1.0);
    	rotateLeft = new DriveTime(2, -1.0, -1.0);
    	alignShooterOne = new AlignShooter();
    	alignShooterTwo = new AlignShooter();
    	raiseShooter = new RaiseShooter();
    	runShooter = new RunShooterTime(8);
    	runIntake = new RunIntakeTime(1);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	overallMovement = 0;
    	state = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state) {
    		case 0:
    			drive18ft.start();
    			state++;
    			break;
    		case 1:
    			rotateLeft.start();
    			raiseShooter.start();
    			state++;
    			break;
    		case 2:
    			if(grip.getCenterX().length != 0) {
    				rotateLeft.cancel();
    				Timer.delay(0.5);
    				alignShooterOne.start();
    				runShooter.start();
    				state++;
    			}
    			break;
    		case 3:
    			if(!alignShooterOne.isRunning()) {
    				Timer.delay(0.5);
    				alignShooterTwo.start();
    				state++;
    			}
    			break;
    		case 4:
    			if(!alignShooterTwo.isRunning()) {
    				Timer.delay(0.5);
    				runIntake.start();
    				state++;
    			}
    			break;
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
