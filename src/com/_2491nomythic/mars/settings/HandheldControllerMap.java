package com._2491nomythic.mars.settings;

/**
 * Assigns the handheld controller buttons to variables for consistency across files and convenience
 */
public class HandheldControllerMap {
	//Configuration
	public static final int configurationController = 0;
	public static final int chevalDeFriseConfigutationButton = 1;
	public static final int lowBarConfigurationButton = 2;
	public static final int pickUpBallConfigurationButton = 3;
	public static final int startingConfigurationButton = 4;
	
	//Drive
	public static final int driveController = 0;
	public static final int driveLeftAxis = 1;
	public static final int driveRightAxis = 3;
	public static final int driveShiftButton = 8;
	
	//Shooter
	public static final int shooterController = 0;
	public static final int shootButton = 6;
	public static final int manualShooterController = 1;
	public static final int manualShooterButton = 8;
	public static final int manualShooterPositionUpPOV = 0;
	public static final int manualShooterPositionDownPOV = 180;
	
	//Intake
	public static final int intakeController = 0;
	public static final int intakeBallButton = 7;
	public static final int manualIntakeController = 1;
	public static final int manualIntakeAxis = 1;
	
	//Armature
	public static final int manualArmatureController = 1;
	public static final int manualArmatureAxis = 3;
	public static final int manualArmatureFastButton = 6;
}
