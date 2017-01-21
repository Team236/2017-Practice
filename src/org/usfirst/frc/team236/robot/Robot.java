
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import ticktank.ControllerType;
import ticktank.Settings;
import ticktank.TickTank;

public class Robot extends IterativeRobot {

	public static OI oi;

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
		config.hasEncoders = false;
		config.motorCount = 2;
		config.leftStick = oi.leftStick;
		config.rightStick = oi.rightStick;
		config.hasGears = false;
		config.hasGyro = false;

		tank = new TickTank(config);
		turret = new Turret();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
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
