package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandContainerStrategy {
	public AutonomousCommandContainerStrategy() {
		addSequential(new ElevatorMoveToHeight());
    	addSequential(new CloseGrabber());
    	addSequential(new MoveStraightPositionModeCommand(4,0.7));
    	addSequential(new OpenGrabber());
    	addSequential(new MoveStraightPositionModeCommand(1,0.2));

	}

}
