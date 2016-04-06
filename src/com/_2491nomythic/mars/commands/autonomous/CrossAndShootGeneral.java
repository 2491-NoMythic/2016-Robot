package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.AlignShooter;
import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.drivetrain.DriveStraightToPosition;
import com._2491nomythic.mars.commands.drivetrain.RotateDrivetrainWithGyro;
import com._2491nomythic.mars.commands.intake.RunIntakeTime;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class CrossAndShootGeneral extends CommandBase {
	DriveStraightToPosition drive18ft;
	RotateDrivetrainWithGyro rotate180DegreesLeft;
	AlignShooter alignShooterOne;
	AlignShooter alignShooterTwo;
	RaiseShooter raiseShooter;
	RunShooterTime runShooter;
	RunIntakeTime runIntake;
	double overallMovement;
	int state;
    public CrossAndShootGeneral() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive18ft = new DriveStraightToPosition(14, 1.0);
    	rotate180DegreesLeft = new RotateDrivetrainWithGyro(180, 0.2, true);
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
    			if(!drive18ft.isRunning()) {
    			rotate180DegreesLeft.start();
    			raiseShooter.start();
    			state++;
    			}
    			break;
    		case 2:
    			if(grip.getCenterX().length != 0) {
    				rotate180DegreesLeft.cancel();
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
    		case 5:
    			break;
    		default:
    			System.out.println("Something wrong in auto. State: " + state);
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
