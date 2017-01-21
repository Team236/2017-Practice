package org.usfirst.frc.team236.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public class Turret {
		public static final int PWM_YAW = 4;
		public static final int PWM_PITCH = 5;

		public static final int DIO_YAW_ENCODER_A = 0;
		public static final int DIO_YAW_ENCODER_B = 1;
		public static final int DIO_LIMIT_LEFT = 4;
		public static final int DIO_LIMIT_RIGHT = 5;
		public static final int DIO_LIMIT_TOP = 6;
		public static final int DIO_LIMIT_BOTTOM = 7;
	}
}
