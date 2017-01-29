
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.LeftAuto;
import org.usfirst.frc.team236.robot.commands.RightAuto;
import org.usfirst.frc.team236.robot.commands.TestAuto;
import org.usfirst.frc.team236.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ticktank.ControllerType;
import ticktank.Settings;
import ticktank.TickTank;
import ticktank.motionProfile.Profile;

public class Robot extends IterativeRobot {

	public static OI oi;

	// Declare profiles
	public static Profile straightGearDelivery;
	public static Profile rightGearLeg1;
	public static Profile rightGearLeg2;

	public static Profile leftGearLeg1;
	public static Profile leftGearLeg2;

	// Subsystems
	public static TickTank tank;
	public static Turret turret;

	Command autonomousCommand;
	SendableChooser chooser;

	@Override
	public void robotInit() {
		Settings config = new Settings();
		config.controllerType = ControllerType.SPARK;
		config.hasEncoders = true;
		config.motorCount = 2;
		config.leftStick = new Joystick(0);
		config.rightStick = new Joystick(1);
		config.hasGears = false;
		config.hasGyro = true;

		// Encoder ports
		config.leftEncoderA = RobotMap.Drive.DIO_LEFT_ENCODER_A;
		config.leftEncoderB = RobotMap.Drive.DIO_LEFT_ENCODER_B;
		config.rightEncoderA = RobotMap.Drive.DIO_RIGHT_ENCODER_A;
		config.rightEncoderB = RobotMap.Drive.DIO_RIGHT_ENCODER_B;
		config.rightInvEncoder = true;
		config.rightInv = true;
		config.dpp = RobotMap.Drive.DISTANCE_PER_PULSE;
		config.leftParams = AutoMap.leftParams;
		config.rightParams = AutoMap.rightParams;
		config.turnParams = RobotMap.Drive.turnParams;

		tank = new TickTank(config);
		turret = new Turret();
		oi = new OI();

		// Create profiles
		straightGearDelivery = new Profile(AutoMap.straightGear);
		rightGearLeg1 = new Profile(AutoMap.rightGearLeg1);
		rightGearLeg2 = new Profile(AutoMap.rightGearLeg2);

		leftGearLeg1 = new Profile(AutoMap.leftGearLeg1);
		leftGearLeg2 = new Profile(AutoMap.leftGearLeg2);

		chooser = new SendableChooser();
		//chooser.addDefault("Front", new TestAuto(tank, straightGearDelivery));
		//chooser.addDefault("Right", new RightAuto());
		chooser.addDefault("Left", new LeftAuto());

		SmartDashboard.putData("Auto Mode", chooser);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		tank.left.zeroEncoder();
		tank.right.zeroEncoder();
		autonomousCommand = ((Command) chooser.getSelected());
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Left Encoder", tank.left.getDistance());
		SmartDashboard.putNumber("Right Encoders", tank.right.getDistance());

		SmartDashboard.putNumber("Left Velocity", tank.left.getEncoder().getRate());
		SmartDashboard.putNumber("Right Velocity", tank.right.getEncoder().getRate());

	}

	public void teleopInit() {
		autonomousCommand = ((Command) chooser.getSelected());
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		tank.left.zeroEncoder();
		tank.right.zeroEncoder();
		tank.navx.reset();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", tank.left.getDistance());
		SmartDashboard.putNumber("Right Encoders", tank.right.getDistance());
		
		SmartDashboard.putNumber("Left Velocity", tank.left.getEncoder().getRate());
		SmartDashboard.putNumber("Right Velocity", tank.right.getEncoder().getRate());
		SmartDashboard.putNumber("Left kV", tank.left.getSpeed()/tank.left.getEncoder().getRate());
		SmartDashboard.putNumber("Right kV", tank.right.getSpeed()/tank.right.getEncoder().getRate());
		SmartDashboard.putNumber("Gyro", tank.navx.getAngle());
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
