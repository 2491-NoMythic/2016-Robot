package com._2491nomythic.ares.settings;

public class Constants {
	//Joysticks
	public static final int ControllerOnePort = 0;
	public static final int ControllerTwoPort = 1;
	
	//Channels
	public static final int driveTalonLeft1Channel = 18;
	public static final int driveTalonLeft2Channel = 17;
	public static final int driveTalonLeft3Channel = 16;
	public static final int driveTalonRight1Channel = 11;
	public static final int driveTalonRight2Channel = 12;
	public static final int driveTalonRight3Channel = 13;
	public static final int driveEncoderLeftChannel1 = 24;
	public static final int driveEncoderLeftChannel2 = 25;
	public static final int driveEncoderRightChannel1 = 26;
	public static final int driveEncoderRightChannel2 = 27;
	public static final int driveSolenoidLeftChannel = 0;
	public static final int driveSolenoidRightChannel = 1;

	public static final int intakeChannel = 10;
	public static final int intakeLimitSwitchChannel = 33;

	public static final int armatureChannel = 19;
	
	public static final int shooterTalonLeftChannel = 15;
	public static final int shooterTalonRightChannel = 14;
	public static final int shooterSolenoidLeftChannel = 2;
	public static final int shooterSolenoidRightChannel = 3;
	
	//Computation Constants
	public static final double driveEncoderToFeet = 1; //TODO get this value
	public static final double acceptableArmatureDifference = 5; //TODO get this value
	public static final double shooterSpeed = 0.9;
	public static final double intakeSpeed = 1;
	public static final double armatureSpeed = 0.5;
}