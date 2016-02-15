package com._2491nomythic.ares.commands;

import com._2491nomythic.ares.commands.intake.RunIntakeTime;
import com._2491nomythic.ares.commands.shooter.RaiseShooter;
import com._2491nomythic.ares.commands.shooter.RunShooterTime;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shoot extends CommandGroup {
	Timer timer;
    
    public  Shoot() {
    	timer = new Timer();
    	timer.start();
    	timer.reset();
    	
    	if(CommandBase.shooter.getLeftSolenoidValue() == false) {
    		addSequential(new RaiseShooter());
    		Timer.delay(1);
    	}
    	
    	addSequential(new RunShooterTime(4));
    	Timer.delay(1.5);
    	addSequential(new RunIntakeTime(2));
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
