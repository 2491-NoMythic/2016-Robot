package com._2491nomythic.mars.commands;

import com._2491nomythic.mars.commands.armature.ArmaturePositionSet;
import com._2491nomythic.mars.settings.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Sets the robot up to go across the cheval de frise
 */
public class ChevalDeFriseConfiguration extends CommandGroup {
    
	/**
	 * Sets the robot up to go across the cheval de frise
	 */
    public  ChevalDeFriseConfiguration() {
    	addSequential(new ArmaturePositionSet(Constants.armatureDownPositionValue));
    	
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
