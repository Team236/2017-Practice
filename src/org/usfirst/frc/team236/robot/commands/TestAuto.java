package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import ticktank.Direction;
import ticktank.TickTank;
import ticktank.commands.FollowProfile;
import ticktank.commands.Turn;
import ticktank.motionProfile.Profile;

/**
 *
 */
public class TestAuto extends CommandGroup {

	public TestAuto(TickTank _tank, Profile p) {
		addSequential(new FollowProfile(_tank, p, false));
		addSequential(new Turn(Robot.tank, AutoMap.rightGearDegrees, Direction.CCW));
	}
}
