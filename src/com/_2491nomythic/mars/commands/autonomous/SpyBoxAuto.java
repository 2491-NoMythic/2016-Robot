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
public class SpyBoxAuto extends Command {
	RaiseShooter raiseShooter;
	RunShooterTime runShooter;
	RunIntakeTime intake;
	DriveStraightToPosition driveToDefense;
	RotateDrivetrainWithGyro turnRight;
	ArmatureTime lowerArmature;
	DriveStraightToPosition driveOverDefense;
	int defenseNumber;
	int state;
	
    public SpyBoxAuto(int defenseToDriveTo, boolean portcullis) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	raiseShooter = new RaiseShooter();
    	runShooter = new RunShooterTime(8);
    	intake = new RunIntakeTime(2);
    	defenseNumber = defenseToDriveTo;
    	
    	switch(defenseToDriveTo) {
    		case 0:
    			break;
    		case 1:
    			//Low Bar
    			driveToDefense = new DriveStraightToPosition(0,0);
    			turnRight = new RotateDrivetrainWithGyro(67, 1, true);
    			driveOverDefense = new DriveStraightToPosition(-18, -1);
    			lowerArmature = new ArmatureTime(0.5, 0.8); // takes in positive power
    		case 2:
    			
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(defenseNumber) {
    		case 0:
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
    					break;
    				case 3:
    					break;
    			}
    			break;
    		case 1:
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
    					break;
    				case 3:
    					break;
    			 
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
    }
}
