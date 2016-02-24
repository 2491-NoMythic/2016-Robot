package com._2491nomythic.mars;

import com._2491nomythic.mars.commands.ChevalDeFriseConfiguration;
import com._2491nomythic.mars.commands.LowBarConfiguration;
import com._2491nomythic.mars.commands.PickUpBallConfiguration;
import com._2491nomythic.mars.commands.Shoot;
import com._2491nomythic.mars.commands.StartingConfiguration;
import com._2491nomythic.mars.commands.armature.ManualArmatureDownControl;
import com._2491nomythic.mars.commands.armature.ManualArmatureUpControl;
import com._2491nomythic.mars.commands.drivetrain.ShiftGear;
import com._2491nomythic.mars.commands.intake.IntakeBall;
import com._2491nomythic.mars.commands.intake.ManualSpitOut;
import com._2491nomythic.mars.commands.intake.ManualTakeIn;
import com._2491nomythic.mars.commands.shooter.LowerShooter;
import com._2491nomythic.mars.commands.shooter.ManualShooterControl;
import com._2491nomythic.mars.commands.shooter.RaiseShooter;
import com._2491nomythic.mars.settings.Constants;
import com._2491nomythic.mars.settings.ControllerMap;
import com._2491nomythic.util.JoystickAxisButton;
import com._2491nomythic.util.JoystickPOVButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
	private final Joystick[] controllers = new Joystick[2];
	Button shoot, chevalDeFriseConfiguration, lowBarConfiguration, pickUpBallConfiguration, startingConfiguration, shiftGear, manualShooterControl, manualTakeIn, manualSpitOut, raiseShooter, lowerShooter, intakeBall, manualArmatureUpControl, manualArmatureDownControl;
	
	
	/**
	 * Initiates some joysticks and buttons.
	 */
	public void init() {
		controllers[0] = new Joystick(Constants.ControllerOnePort);
		controllers[1] = new Joystick(Constants.ControllerTwoPort);
		
		//Commands
		shoot = new JoystickButton(controllers[ControllerMap.shooterController], ControllerMap.shootButton);
		shoot.whenPressed(new Shoot());
		
		chevalDeFriseConfiguration = new JoystickButton(controllers[ControllerMap.configurationController], ControllerMap.chevalDeFriseConfigutationButton);
		chevalDeFriseConfiguration.whenPressed(new ChevalDeFriseConfiguration());
		
		lowBarConfiguration = new JoystickButton(controllers[ControllerMap.configurationController], ControllerMap.lowBarConfigurationButton);
		lowBarConfiguration.whenPressed(new LowBarConfiguration());
		
		pickUpBallConfiguration = new JoystickButton(controllers[ControllerMap.configurationController], ControllerMap.pickUpBallConfigurationButton);
		pickUpBallConfiguration.whenPressed(new PickUpBallConfiguration());
		
		startingConfiguration = new  JoystickButton(controllers[ControllerMap.configurationController], ControllerMap.startingConfigurationButton);
		startingConfiguration.whenPressed(new StartingConfiguration());
		
		//Drivetrain
		shiftGear = new JoystickButton(controllers[ControllerMap.driveController], ControllerMap.driveShiftButton);
		shiftGear.whileHeld(new ShiftGear());
		
		//Shooter
		manualShooterControl = new JoystickButton(controllers[ControllerMap.manualShooterController], ControllerMap.manualShooterButton);
		manualShooterControl.whileHeld(new ManualShooterControl());
		
		raiseShooter = new JoystickPOVButton(controllers[ControllerMap.manualShooterController], ControllerMap.manualShooterPositionUpPOV);
		raiseShooter.whenPressed(new RaiseShooter());
		
		lowerShooter = new JoystickPOVButton(controllers[ControllerMap.manualShooterController], ControllerMap.manualShooterPositionDownPOV);
		lowerShooter.whenPressed(new LowerShooter());
		
		//Intake
		manualTakeIn = new JoystickButton(controllers[ControllerMap.manualIntakeController], ControllerMap.manualTakeInButton);
		manualTakeIn.whileHeld(new ManualTakeIn());
		
		manualSpitOut = new JoystickButton(controllers[ControllerMap.manualIntakeController], ControllerMap.manualSpitOutButton);
		manualSpitOut.whileHeld(new ManualSpitOut());
		
		intakeBall = new JoystickButton(controllers[ControllerMap.intakeController], ControllerMap.intakeBallButton);
		intakeBall.whileHeld(new IntakeBall());
		
		//Armature
		manualArmatureUpControl = new JoystickAxisButton(controllers[ControllerMap.manualArmatureController], ControllerMap.manualArmatureAxis, -0.8);
		manualArmatureUpControl.whileHeld(new ManualArmatureControl(false));
		
		manualArmatureDownControl = new JoystickAxisButton(controllers[ControllerMap.manualArmatureController], ControllerMap.manualArmatureAxis, 0.8);
		manualArmatureDownControl.whileHeld(new ManualArmatureControl(true));
	}
	
	/**
	 * Get a controller
	 * 
	 * @param id the ID of the controller. 0 = left or driver, 1 = right or codriver.
	 * @return the instance of the controller requested
	 */
	public Joystick getController(int id) {
		return controllers[id];
	}
	
	/**
	 * Get a button from a controller
	 * 
	 * @param joystickID
	 *            The id of the controller. 0 = left or driver, 1 = right or
	 *            codriver.
	 * @param axisID
	 *            The id of the button (for use in getRawButton)
	 * @return the result from running getRawButton(button)
	 */
	public boolean getButton(int joystickID, int buttonID) {
		return controllers[joystickID].getRawButton(buttonID);
	}
	
	/**
	 * Get an axis from a controller
	 * 
	 * @param joystickID
	 *            The id of the controller. 0 = left or driver, 1 = right or
	 *            codriver.
	 * @param axisID
	 *            The id of the axis (for use in getRawAxis)
	 * @return the result from running getRawAxis(axis)
	 */
	public double getAxis(int joystickID, int axisID) {
		return controllers[joystickID].getRawAxis(axisID);
	}
	
	/**
	 * Get an axis from a controller that is automatically deadzoned
	 * 
	 * @param joystickID The id of the controller. 0 = left or driver, 1 = right or driver
	 * @param axisID The id of the axis (for use in getRawAxis)
	 * @return the deadzoned result from running getRawAxis
	 */
	public double getAxisDeadzoned(int joystickID, int axisID) {
		double result = controllers[joystickID].getRawAxis(axisID);
		return Math.abs(result) > 0.05 ? result : 0;
	}
	
	/**
	 * Get an axis from a controller that is automatically squared and deadzoned
	 * 
	 * @param joystickID The id of the controller. 0 = left or driver, 1 = right or driver
	 * @param axisID The id of the axis (for use in getRawAxis)
	 * @return the squared, deadzoned result from running getRawAxis
	 */
	public double getAxisDeadzonedSquared(int joystickID, int axisID) {
		double result = controllers[joystickID].getRawAxis(axisID);
		result = result * Math.abs(result);
		return Math.abs(result) > 0.05 ? result : 0;
	}
	
	/**
	 * Get an axis from a controller that is automatically cubed and deadzoned
	 * 
	 * @param joystickID The id of the controller. 0 = left or driver, 1 = right or driver
	 * @param axisID The id of the axis (for use in getRawAxis)
	 * @return the cubed, deadzoned result from running getRawAxis
	 */
	public double getAxisDeadzonedToTheFourth(int joystickID, int axisID) {
		double result = controllers[joystickID].getRawAxis(axisID);
		result = result * Math.pow(Math.abs(result), 3);
		return Math.abs(result) > 0.05 ? result : 0;
	}
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

