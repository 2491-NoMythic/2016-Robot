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
 * @deprecated
 */
public class CrossAndShootGeneral extends CommandBase {
	DriveStraightToPosition drive18ft;
	RotateDrivetrainWithGyro rotate180DegreesLeft;
	AlignShooter alignShooterOne;
	AlignShooter alignShooterTwo;
	RaiseShooter raiseShooter;
	RunShooterTime runShooter;
	RunIntakeTime runIntake;
	Timer timer;
	double overallMovement;
	int state;
	
    public CrossAndShootGeneral() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer = new Timer();
    	
    	drive18ft = new DriveStraightToPosition(16, 1.0);
    	rotate180DegreesLeft = new RotateDrivetrainWithGyro(360, 0.2, true);
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
				raiseShooter.start();
    			state++;
    			break;
    		case 1:
    			if(!drive18ft.isRunning()) {
    			rotate180DegreesLeft.start();
    			state++;
    			}
    			break;
    		case 2:
    			if(grip.getCenterX().length != 0) {
    				rotate180DegreesLeft.cancel();
    				timer.start();
    				timer.reset();
    				state = 2491;
    			}
    			break;
    		case 2491:
    			if(timer.get() > 1) {
    				alignShooterOne.start();
    				runShooter.start();
    				timer.reset();
    				state = 3;
    			}
    		case 3:
    			if(!alignShooterOne.isRunning() && timer.get() > 2) {
    				alignShooterTwo.start();
    				timer.reset();
    				state++;
    			}
    			break;
    		case 4:
    			if(!alignShooterTwo.isRunning() && timer.get() > 0.5) {
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
