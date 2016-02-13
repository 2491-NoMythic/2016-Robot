package com._2491nomythic.ares.commands.armature;
import com._2491nomythic.ares.commands.CommandBase;
import com._2491nomythic.ares.settings.Constants;

public class AutomaticArmaturePositionOne extends CommandBase{
	
	
	public AutomaticArmaturePositionOne() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
	requires(armature);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		double PositionFourEncoderValue = 100;
		double CurentEncoderValue = armature.getEncoderPosition();
		
		if(CurentEncoderValue == PositionFourEncoderValue){
			//the motor is in the correct position
			end();
		}
		else if(CurentEncoderValue > PositionFourEncoderValue){
			//motor position is greater then where it needs to be
			armature.set(-1.0);
		}
		else{
			//motor position is less then where it needs to be
			armature.set(1.0);
		}
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
		armature.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}