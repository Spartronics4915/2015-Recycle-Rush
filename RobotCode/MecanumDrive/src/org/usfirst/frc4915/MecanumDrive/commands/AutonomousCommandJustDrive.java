package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandJustDrive {
	public AutonomousCommandJustDrive() {
		addSequential(new ElevatorMoveToHeight());
    	addSequential(new CloseGrabber());
    	addSequential(new MoveStraightPositionModeCommand(10,0.7));
		
	}

}
