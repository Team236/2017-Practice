package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import ticktank.Direction;
import ticktank.commands.Turn;

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
		
		JoystickButton drive = new JoystickButton(controller, LogitechF310.A);
		drive.whileHeld(new DriveStraight(.5));
		
		JoystickButton turn60 = new JoystickButton(controller, LogitechF310.LB);
		turn60.whenPressed(new Turn(Robot.tank, 60, Direction.CW));
		
		JoystickButton invTurn60 = new JoystickButton(controller, LogitechF310.RB);
		invTurn60.whenPressed(new Turn(Robot.tank, 60, Direction.CCW));
	}
}
