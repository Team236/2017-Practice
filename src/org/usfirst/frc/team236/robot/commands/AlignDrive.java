package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.LogitechF310;
import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class AlignDrive extends Command {
	public static double speedFactor = -0.25;

    public AlignDrive() {
        requires(Robot.tank);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.tank.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftSpeed = speedFactor * Robot.oi.controller.getRawAxis(LogitechF310.Axes.LEFT_Y);
    	double rightSpeed = speedFactor * Robot.oi.controller.getRawAxis(LogitechF310.Axes.LEFT_Y);;
    	leftSpeed += -speedFactor * Robot.oi.controller.getRawAxis(LogitechF310.Axes.LEFT_X);
    	rightSpeed -= -speedFactor * Robot.oi.controller.getRawAxis(LogitechF310.Axes.LEFT_X);
    	
    	Robot.tank.setLeftSpeed(leftSpeed);
    	Robot.tank.setRightSpeed(rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.tank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
