package org.usfirst.frc.team236.robot.commands;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import ticktank.Direction;
import ticktank.commands.FollowProfile;
import ticktank.commands.Turn;

/**
 *
 */
public class LeftAuto extends CommandGroup {

    public LeftAuto() {
    	addSequential(new FollowProfile(Robot.tank, Robot.leftGearLeg1, false));
    	addSequential(new Turn(Robot.tank, AutoMap.leftTurnDegreees, Direction.CW));
    	addSequential(new Wait(0));
    	addSequential(new FollowProfile(Robot.tank, Robot.leftGearLeg2, false));
    }
}
