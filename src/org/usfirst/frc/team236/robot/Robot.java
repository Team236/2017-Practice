
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.RawGear;
import org.usfirst.frc.team236.robot.commands.TestAuto;
import org.usfirst.frc.team236.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.NamedSendable;
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
	
	public static Profile testProfile;

	// Subsystems
	public static TickTank tank;
	public static Turret turret;

	Command autonomousCommand;
	SendableChooser chooser;

	@Override
	public void robotInit() {
		oi = new OI();

		Settings config = new Settings();
		config.controllerType = ControllerType.VICTOR;
		config.hasEncoders = true;
		config.motorCount = 2;
		config.leftStick = oi.leftStick;
		config.rightStick = oi.rightStick;
		config.hasGears = false;
		config.hasGyro = false;
		
		// Encoder ports
		config.leftEncoderA = RobotMap.Drive.DIO_LEFT_ENCODER_A;
		config.leftEncoderB = RobotMap.Drive.DIO_LEFT_ENCODER_B;
		config.rightEncoderA = RobotMap.Drive.DIO_RIGHT_ENCODER_A;
		config.rightEncoderB = RobotMap.Drive.DIO_RIGHT_ENCODER_B;
		config.rightInvEncoder = true;
		config.dpp = RobotMap.Drive.DISTANCE_PER_PULSE;
		config.params = AutoMap.params;

		tank = new TickTank(config);
		turret = new Turret();
		
		// Create profiles
		testProfile = new Profile(AutoMap.test);
		System.out.println(testProfile.length());
		
		// Create commands
		TestAuto testAuto = new TestAuto(tank, testProfile);
		
		chooser = new SendableChooser();
		chooser.addDefault("96 inches", new TestAuto(tank, testProfile));
		chooser.addObject("Gear delivery", new RawGear());
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
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
