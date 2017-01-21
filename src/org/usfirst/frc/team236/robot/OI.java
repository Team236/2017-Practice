package org.usfirst.frc.team236.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick leftStick;
	public Joystick rightStick;
	public Joystick controller;

	public OI() {
		leftStick = new Joystick(ControlMap.PORT_LEFT);
		rightStick = new Joystick(ControlMap.PORT_RIGHT);
		controller = new Joystick(ControlMap.PORT_CONTROLLER);
	}
}
