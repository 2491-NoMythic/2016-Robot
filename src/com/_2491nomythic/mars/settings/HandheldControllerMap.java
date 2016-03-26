package com._2491nomythic.mars.settings;

/**
 * Assigns the handheld controller buttons to variables for consistency across files and convenience
 */
public class HandheldControllerMap {
	// Configuration
	public static final int configurationController = 0;
	public static final int chevalDeFriseConfigutationButton = 9001;
	public static final int lowBarConfigurationButton = 9001;
	public static final int pickUpBallConfigurationButton = 9001;
	public static final int startingConfigurationButton = 9001;
	
	// Vision
	public static final int alignShooterButton = 5;
	
	// Drive
	public static final int driveController = 0;
	public static final int driveLeftAxis = 1;
	public static final int driveRightAxis = 3;
	public static final int driveShiftButton = 8;
	public static final int driveStraightPOV = 0;
	public static final int driveTurnLeftPOV = 270;
	public static final int driveTurnRightPOV = 90;
	public static final int driveTurnAroundPOV = 180;
	
	// Shooter
	public static final int shooterController = 0;
	public static final int shootButton = 6;
	public static final int photonCannonButton = 1;
	
	public static final int manualShooterController = 1;
	public static final int manualShooterButton = 8;
	public static final int manualShooterPositionUpPOV = 0;
	public static final int manualShooterPositionDownPOV = 180;
	
	// Intake
	public static final int intakeController = 0;
	public static final int intakeBallButton = 7;
	
	public static final int manualIntakeController = 1;
	public static final int manualIntakeAxis = 1;
	
	// Armature
	public static final int manualArmatureController = 1;
	public static final int manualArmatureAxis = 3;
	public static final int manualArmatureFastButton = 6;
}
