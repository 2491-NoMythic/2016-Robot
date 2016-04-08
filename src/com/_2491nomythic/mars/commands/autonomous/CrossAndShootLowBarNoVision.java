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
public class CrossAndShootLowBarNoVision extends CommandBase {
	Timer timer;
	DriveStraightToPosition drive20ft;
	ArmatureTime lowerArmature;
	RotateDrivetrainWithGyro rotate60DegreesRight;
	DriveStraightToPosition drive2ft;
	RunShooterTime runShooter5Secs;
	RunIntakeTime intake;
	DriveStraightToPosition drive2ftForward;
	RotateDrivetrainWithGyro rotate60DegreesLeft;
	DriveStraightToPosition drive20ftForward;
	int state;
    public CrossAndShootLowBarNoVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer = new Timer();
    	drive20ft = new DriveStraightToPosition(19.88, -0.8);
    	lowerArmature = new ArmatureTime(0.5, 0.8); 
    	rotate60DegreesRight = new RotateDrivetrainWithGyro(-60, 0.5, false);
    	drive2ft = new DriveStraightToPosition(2, -0.8);
    	runShooter5Secs = new RunShooterTime(5);
    	intake = new RunIntakeTime(2);
    	rotate60DegreesLeft = new RotateDrivetrainWithGyro(60, 0.5, true);
    	drive2ftForward = new DriveStraightToPosition(2, 0.8);
    	drive20ftForward = new DriveStraightToPosition(19.88, 0.8);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state) {
    		case 0:
    			drive20ft.start();
    			state++;
    			break;
    		case 1:
    			if(timer.get() > 1) {
    				lowerArmature.start();
    				state++;
    			}
    			break;
    		case 2:
    			if(!drive20ft.isRunning()) {
    				rotate60DegreesRight.start();
    				state++;
    			}
    			break;
    		case 3:
    			if(!rotate60DegreesRight.isRunning()) {
    				drive2ft.start();
    				state++;
    			}
    			break;
    		case 4:
    			if(!drive2ft.isRunning()) {
    				runShooter5Secs.start();
    				timer.reset();
    				state++;
    			}
    			break;
    		case 5:
    			if(timer.get() > 2) {
    				intake.start();
    				state++;
    			}
    			break;
    		case 6:
    			if(!intake.isRunning()) {
    				drive2ftForward.start();
    				state++;
    			}
    			break;
    		case 7:
    			if(!drive2ftForward.isRunning()) {
    				rotate60DegreesLeft.start();
    				state++;
    			}
    			break;
    		case 8:
    			if(!rotate60DegreesLeft.isRunning()) {
    				drive20ftForward.start();
    			}
    			break;
    		default:
    			System.out.println("Something wrong in Autonomous, state: " + state);
    			break;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == 8 && !drive20ftForward.isRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive20ft.cancel();
    	lowerArmature.cancel();
    	rotate60DegreesRight.cancel();
    	drive2ft.cancel();
    	runShooter5Secs.cancel();
    	intake.cancel();
    	drive2ftForward.cancel();
    	rotate60DegreesLeft.cancel();
    	drive20ftForward.cancel();
    }
}
