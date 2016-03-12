package com._2491nomythic.mars.subsystems;

import com._2491nomythic.mars.commands.RunCamera1;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The first of two cameras used to help the drivers shoot
 */
public class Camera1 extends Subsystem {
	
	private int session;
	private Image frame;
	private boolean cameraFound = true;
	private static Camera1 instance;
	
	public static Camera1 getInstance() {
		if (instance == null) {
			instance = new Camera1();
		}
		return instance;
	}
	
	/**
	 * The first of two cameras used to help the drivers shoot
	 */
	private Camera1() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0);
		try {
			session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		}
		catch (VisionException e) {
			System.out.println(e.getMessage());
			cameraFound = false;
		}
		if (cameraFound) {
			NIVision.IMAQdxConfigureGrab(session);
		}
	}
	
	/**
	 * Starts receiving video from the Camera
	 */
	public void startImageAcquisition() {
		if (cameraFound) {
			NIVision.IMAQdxStartAcquisition(session);
		}
	}
	
	/**
	 * Stops receiving video from the camera
	 */
	public void stopImageAcquisition() {
		if (cameraFound) {
			NIVision.IMAQdxStopAcquisition(session);
		}
	}
	
	/**
	 * Updates the current image to the Driver Station
	 */
	public void updateDriverstationImage() {
		if (cameraFound) {
			NIVision.IMAQdxGrab(session, frame, 1);
			CameraServer.getInstance().setImage(frame);
		}
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new RunCamera1());
	}
}
