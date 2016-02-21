package com._2491nomythic.mars.settings;

public class Constants {
	//Joysticks
	public static final int ControllerOnePort = 0;
	public static final int ControllerTwoPort = 1;
	
	//Digital I/O
	public static final int armatureLimitSwitchChannel = 0;
	public static final int intakeLimitSwitch1Channel = 1;
	public static final int intakeLimitSwitch2Channel = 2;
	
	//Channels
	public static final int driveTalonLeft1Channel = 18;
	public static final int driveTalonLeft2Channel = 17;
	public static final int driveTalonLeft3Channel = 16;
	public static final int driveTalonRight1Channel = 11;
	public static final int driveTalonRight2Channel = 12;
	public static final int driveTalonRight3Channel = 13;
	public static final int driveSolenoidChannel = 6;

	public static final int intakeChannel = 19;

	public static final int armatureChannel = 10;
	
	public static final int shooterTalonLeftChannel = 15;
	public static final int shooterTalonRightChannel = 14;
	public static final int shooterSolenoidChannel = 7;
	
	//Computation Constants
	public static final double driveEncoderToFeet = 1; //TODO get this value
	public static final double acceptableArmatureDifference = 5; //TODO get this value
	public static final double intakeSpeed = 1;
	public static final double armatureSpeed = 0.5;
	public static final long armatureRunTimeMs = 500;  // Time the armature will run trying to find the correct position
	public static final double armatureUpPositionValue = 0;
	public static final double armatureDownPositionValue = -1816;
	public static final double armatureIntakePositionValue = -1403;
	public static final double armatureLowBarPositionValue = -1395;
	
	//Vision Constants
	public static final double visionCenterXValue = 1; //TODO
	public static final double visionCenterYValue = 1; //TODO
	public static final double visionAreaValue = 1; //TODO
	public static final double visionHeightValue = 1; //TODO
	public static final double visionWidthValue = 1; //TODO
	public static final double acceptableVisionCenterXDifference = 1; //TODO
	public static final double acceptableVisionCenterYDifference = 1; //TODO
	public static final double acceptableVisionAreaDifference = 1; //TODO
	public static final double acceptableVisionHeightDifference = 1; //TODO
	public static final double acceptableVisionWidthDifference = 1; //TODO

}