package com._2491nomythic.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickPOVButton extends Button {
	
	GenericHID m_joystick;
	int m_POVNumber;
	
	/**
	 * Create a joystick button for triggering commands
	 *$
	 * @param joystick The GenericHID object that has the button (e.g. Joystick,
	 *        KinectStick, etc)
	 * @param POVNumber The POV number
	 */
	public JoystickPOVButton(GenericHID joystick, int POVNumber) {
	    m_joystick = joystick;
	    m_POVNumber = POVNumber;
	}
	
	/**
	 * Gets the value of the joystick POV button
	 *$
	 * @return The value of the joystick POV button
	 */
	public boolean get() {
		return (m_joystick.getPOV() == m_POVNumber);
	}
}
