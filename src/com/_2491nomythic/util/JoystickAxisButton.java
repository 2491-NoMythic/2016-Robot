/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com._2491nomythic.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Creates a joystick button for triggering commands using an axis
 */
public class JoystickAxisButton extends Button {
	
	GenericHID m_joystick;
	int m_buttonNumber;
	double m_cutoff;
	boolean m_isNegative;
	
	/**
	 * Creates a joystick button for triggering commands using an axis
	 * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
	 * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) })
	 * @param cutoff The cutoff point between off and on
	 */
	public JoystickAxisButton(GenericHID joystick, int buttonNumber, double cutoff) {
		m_joystick = joystick;
		m_buttonNumber = buttonNumber;
		m_cutoff = cutoff;
		m_isNegative = (cutoff < 0);
	}
	
	/**
	 * Gets the value of the joystick axis button
	 * @return The value of the joystick axis button
	 */
	public boolean get() {
		double currentValue = m_joystick.getRawAxis(m_buttonNumber);
		if (m_isNegative) {
			return currentValue <= m_cutoff;
		}
		else {
			return currentValue >= m_cutoff;
		}
	}
	
}
