package com._2491nomythic.mars.settings;

/**
 * All of the constants that we use to control the robot
 */
public class Constants {
	// Regular Encoder ticks (non-talon)
	public static final int ticksPerRev = 256;
	
	// Joysticks
	public static final int ControllerOnePort = 0;
	public static final int ControllerTwoPort = 1;
	
	// Digital I/O
	public static final int armatureLimitSwitchChannel = 0;
	public static final int intakeLimitSwitch1Channel = 1;
	public static final int intakeLimitSwitch2Channel = 2;
	
	// CAN Channels
	public static final int driveTalonLeft1Channel = 18;
	public static final int driveTalonLeft2Channel = 17;
	public static final int driveTalonLeft3Channel = 16;
	public static final int driveTalonRight1Channel = 11;
	public static final int driveTalonRight2Channel = 12;
	public static final int driveTalonRight3Channel = 13;
	public static final int intakeChannel = 10;
	public static final int armatureChannel = 19;
	public static final int shooterTalonLeftChannel = 15;
	public static final int shooterTalonRightChannel = 14;
	
	// PCM Channels
	public static final int driveSolenoidChannel = 6;
	public static final int shooterShootSolenoidChannel = 7;
	public static final int shooterLockSolenoidChannel = 5; // TODO make sure this is right
	
	// Digital Input Channels
	public static final int driveEncoderLeftChannel1 = 5;
	public static final int driveEncoderLeftChannel2 = 6;
	public static final int driveEncoderRightChannel1 = 3;
	public static final int driveEncoderRightChannel2 = 4;
	
	// Analog Input Channels
	public static final int gyroChannel = 0;
	
	// Computation Constants
	public static final double driveEncoderToFeet = 7.0 * 1.125 * Math.PI / 12 / 256;
	public static final double driveHighGearDelay = 1;
	public static final double driveHighGearShiftSpeed = 3; // TODO find actual speed
	public static final double acceptableArmatureDifference = 5;
	public static final double intakeSpeed = 1;
	public static final double armatureSpeed = 0.3;
	public static final double armatureSpeedFast = 0.5;
	public static final double armatureUpPositionValue = 0;
	public static final double armatureDownPositionValue = -1816;
	public static final double armatureIntakePositionValue = -1403;
	public static final double armatureLowBarPositionValue = -1395;
	public static final double armatureDifference = -1000;
	
	// Vision Constants
	// public static final double visionCenterXValue = 1; //TODO
	// public static final double visionCenterYValue = 1; //TODO
	// public static final double visionAreaValue = 1; //TODO
	// public static final double visionHeightValue = 1; //TODO
	// public static final double visionWidthValue = 1; //TODO
	// public static final double acceptableVisionCenterXDifference = 1; //TODO
	// public static final double acceptableVisionCenterYDifference = 1; //TODO
	// public static final double acceptableVisionAreaDifference = 1; //TODO
	// public static final double acceptableVisionHeightDifference = 1; //TODO
	// public static final double acceptableVisionWidthDifference = 1; //TODO
	public static final double visionHorizontalCompensation = 1; // TODO
	public static final double cameraArea = 800 * 600;
	public static final double cameraHorizontalViewAngle = 67; // degrees
	public static final double targetWidthToHeightAspectRatio = 10 / 7; // width / height in feet
	public static final double degreesPerPixel = 67 / 800;
	
}