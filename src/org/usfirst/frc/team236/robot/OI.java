package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.climber.Climb;
import org.usfirst.frc.team236.robot.commands.drive.AlignDrive;
import org.usfirst.frc.team236.robot.lib.LogitechF310;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick left;
	public Joystick right;
	public Joystick controller;

	public OI() {
		left = new Joystick(ControlMap.PORT_LEFT);
		right = new Joystick(ControlMap.PORT_RIGHT);
		controller = new Joystick(ControlMap.PORT_CONTROLLER);

		JoystickButton gearAlign = new JoystickButton(controller, LogitechF310.X);
		gearAlign.whileHeld(new AlignDrive());
		
		JoystickButton climb = new JoystickButton(controller, LogitechF310.START);
		climb.whileHeld(new Climb());
	}
}
