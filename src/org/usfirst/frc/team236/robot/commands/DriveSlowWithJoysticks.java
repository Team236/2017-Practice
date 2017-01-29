package org.usfirst.frc.team236.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import ticktank.TickTank;

public class DriveSlowWithJoysticks extends Command {

	private TickTank tank;
	private double scaleFactor;

	public DriveSlowWithJoysticks(TickTank _tank, double _scaleFactor) {
		this.tank = _tank;
		requires(_tank);
		this.scaleFactor = _scaleFactor;
	}

	protected void initialize() {

	}

	protected void execute() {
		tank.setLeftSpeed(scaleFactor * -tank.leftStick.getY());
		tank.setRightSpeed(scaleFactor * -tank.rightStick.getY());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		tank.stop();
	}

	protected void interrupted() {
		tank.stop();
	}
}
