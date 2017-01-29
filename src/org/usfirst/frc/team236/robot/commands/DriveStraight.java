package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	
	double speed;
	
	public DriveStraight(double speed) {
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.tank.stop();
	}
	
	@Override
	protected void execute() {
		Robot.tank.setSpeeds(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.tank.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
}
