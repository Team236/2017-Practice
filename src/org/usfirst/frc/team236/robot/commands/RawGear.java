package org.usfirst.frc.team236.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RawGear extends CommandGroup {

	public RawGear() {
		addSequential(new RawGearDrive());
	}
}
