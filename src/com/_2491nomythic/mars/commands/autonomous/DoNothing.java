package com._2491nomythic.mars.commands.autonomous;

import com._2491nomythic.mars.commands.CommandBase;

/**
 * An autonomous that does nothing.
 */
public class DoNothing extends CommandBase {
    
	/**
	 * An autonomous that does nothing.
	 */
    public  DoNothing() {
    }

	protected void initialize() {}

	protected void execute() {}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {}

	protected void interrupted() {}
}
