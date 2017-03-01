
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.subsystems.Climber;
import org.usfirst.frc.team236.robot.subsystems.Turret;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import ticktank.ControllerType;
import ticktank.Settings;
import ticktank.TickTank;
import ticktank.motionProfile.Profile;

public class Robot extends IterativeRobot {

	public static OI oi;
	
	public UsbCamera camera;

	// Declare profiles
	public static Profile straightGearDelivery;
	public static Profile rightGearLeg1;
	public static Profile rightGearLeg2;

	public static Profile leftGearLeg1;
	public static Profile leftGearLeg2;

	// Subsystems
	public static TickTank tank;
	public static Turret turret;
	public static Climber climber;

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
		climber = new Climber();
		oi = new OI();

		try {
			camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			camera.setFPS(30);
		} catch (Exception e) {
			System.out.println("Camera capture failed");
			System.out.println(e.getStackTrace());
		}

		// Create profiles
		straightGearDelivery = new Profile(AutoMap.straightGear);
		rightGearLeg1 = new Profile(AutoMap.rightGearLeg1);
		rightGearLeg2 = new Profile(AutoMap.rightGearLeg2);

		leftGearLeg1 = new Profile(AutoMap.leftGearLeg1);
		leftGearLeg2 = new Profile(AutoMap.leftGearLeg2);
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
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
