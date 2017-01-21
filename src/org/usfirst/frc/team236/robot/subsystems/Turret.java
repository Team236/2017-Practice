package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.TurretWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

//
//
//
public class Turret extends Subsystem {
	//
	private Encoder yawEncoder, pitchEncoder;
	private SpeedController yawMotor, pitchMotor;
	public DigitalInput yawLeft, yawRight, pitchTop, pitchBottom;

	//Pre-Condition:
	//Post-Condition:
	//Bugs:
	/**
	 * Construct a new turret subsystem
	 */
	public Turret() {
		// Instantiate motors
		yawMotor = new Victor(RobotMap.Turret.PWM_YAW);
		pitchMotor = new Victor(RobotMap.Turret.PWM_PITCH);

		// Limit switches
		yawRight = new DigitalInput(RobotMap.Turret.DIO_LIMIT_RIGHT);
		yawLeft = new DigitalInput(RobotMap.Turret.DIO_LIMIT_LEFT);
		pitchTop = new DigitalInput(RobotMap.Turret.DIO_LIMIT_TOP);
		pitchBottom = new DigitalInput(RobotMap.Turret.DIO_LIMIT_BOTTOM);

		// Encoders
		yawEncoder = new Encoder(RobotMap.Turret.DIO_YAW_ENCODER_A, RobotMap.Turret.DIO_YAW_ENCODER_B);
		//pitchEncoder = new Encoder(RobotMap.Turret.DIO_PITCH_ENCODER_A, RobotMap.Turret.DIO_PITCH_ENCODER_B);
	}

	// Set speed to spin yaw motor
	// positive = clockwise
	public void setYawSpeed(double speed) {
		if (speed > 0 && !yawLeft.get()) {
			speed = 0;
		} else if (speed < 0 && !yawRight.get()) {
			speed = 0;
		}
		yawMotor.set(speed);
	}

	// Set speed to spin pitch motor
	// positive = up
	public void setPitchSpeed(double speed) {
		if (speed > 0 && !pitchTop.get()) {
			speed = 0;
		} else if (speed < 0 && !pitchBottom.get()) {
			speed = 0;
		}
		pitchMotor.set(speed);
	}

	public void stop() {
		setYawSpeed(0);
		setPitchSpeed(0);
	}

	// 
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TurretWithJoystick());
	}
}
