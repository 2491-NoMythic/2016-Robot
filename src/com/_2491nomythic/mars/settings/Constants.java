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
	public static final int driveEncoderRightChannel1 = 4;
	public static final int driveEncoderRightChannel2 = 3;
	
	// Relay Channels
	public static final int shooterPhotonCannonChannel = 0;
	
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
	public static final double armatureLowBarDifference = -1000;
	public static final double armaturePortcullisDifference = -1400;
	
	// Vision Constants
	public static final double cameraArea = 480 * 360;
	public static final double cameraHorizontalViewAngle = 61; // degrees
	public static final double FOVpixelWidth = 480;	//pixels
	public static final double FOVpixelHight = 360; //pixels
	public static final double TargetWidth = 1.66; //feet
	public static final double TargetHight = 0; //feet
	public static final double degreesPerPixel = 61.0 / 480.0;
	
}