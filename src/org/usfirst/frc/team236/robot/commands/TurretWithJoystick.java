package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.LogitechF310;
import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurretWithJoystick extends Command {

	public TurretWithJoystick() {
		requires(Robot.turret);
	}

	@Override
	protected void initialize() {
		Robot.turret.stop();
	}

	@Override
	protected void execute() {
		Robot.turret.setPitchSpeed(-Robot.oi.controller.getRawAxis(LogitechF310.Axes.LEFT_Y) / 8);
		Robot.turret.setYawSpeed(-Robot.oi.controller.getRawAxis(LogitechF310.Axes.LEFT_X));
	}

	// Run until the command is interrupted
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// Stop turret to avoid post-conditions
		Robot.turret.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
