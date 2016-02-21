
package com._2491nomythic.mars;

import com._2491nomythic.mars.commands.CommandBase;
import com._2491nomythic.mars.commands.autonomous.DoNothing;
import com._2491nomythic.mars.commands.autonomous.DriveOverRockWall;
import com._2491nomythic.mars.commands.autonomous.DriveUnderLowBar;
import com._2491nomythic.mars.commands.autonomous.DriveUnderLowBarTime;
import com._2491nomythic.mars.commands.autonomous.DriveUnderPortcullis;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	private Command autoCommand;
	private SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		CommandBase.init();
        // instantiate the command used for the autonomous period
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new DoNothing());
		autoChooser.addObject("Drive Under Low Bar Time", new DriveUnderLowBarTime());
		autoChooser.addObject("Drive Over Rock Wall", new DriveOverRockWall());
		autoChooser.addObject("Drive Under Low Bar", new DriveUnderLowBar());
		autoChooser.addObject("Drive Under Portcullis", new DriveUnderPortcullis());
		SmartDashboard.putData("Autonomous", autoChooser);
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autoCommand != null) autoCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
