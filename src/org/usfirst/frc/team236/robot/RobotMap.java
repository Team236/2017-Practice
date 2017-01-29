package org.usfirst.frc.team236.robot;

import pid.PIDParameters;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Turret {
		public static final int PWM_YAW = 4;
		public static final int PWM_PITCH = 5;

		public static final int DIO_YAW_ENCODER_A = 0;
		public static final int DIO_YAW_ENCODER_B = 1;
		public static final int DIO_LIMIT_LEFT = 4;
		public static final int DIO_LIMIT_RIGHT = 5;
		public static final int DIO_LIMIT_TOP = 6;
		public static final int DIO_LIMIT_BOTTOM = 7;
	}
	
	public static class Drive {
		public static final int DIO_LEFT_ENCODER_A = 8;
		public static final int DIO_LEFT_ENCODER_B = 9;
		
		public static final int DIO_RIGHT_ENCODER_A = 2;
		public static final int DIO_RIGHT_ENCODER_B = 3;
		
		public static final double WHEEL_DIAMETER = 4;
		public static final double CIRCUMFERENCE = 4 * Math.PI;
		
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / (128);
		
		public static final double kV_left = 0.037;
		public static final double kV_right = 0.033;
		
		public static final double kA = 0.010;
		public static final double kP = 0.020;
		
		public static final PIDParameters turnParams = new PIDParameters(0.010, 0.03, 0.001, .01);
	}
}
