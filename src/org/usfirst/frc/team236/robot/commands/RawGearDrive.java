package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RawGearDrive extends Command {
	@Override
	protected void initialize() {
		Robot.tank.stop();
		Robot.tank.zeroEncoders();
	}
	
	@Override
	protected void execute() {
		Robot.tank.setSpeeds(.25, .25);
	}

	@Override
	protected boolean isFinished() {
		return Robot.tank.left.getDistance() > 96;
	}
	
	@Override
	protected void end() {
		Robot.tank.stop();
	}
}
