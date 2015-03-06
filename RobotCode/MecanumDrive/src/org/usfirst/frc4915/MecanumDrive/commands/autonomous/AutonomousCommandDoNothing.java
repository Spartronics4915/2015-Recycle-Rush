package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandDoNothing extends CommandGroup {

	public AutonomousCommandDoNothing() {
		addParallel(new ElevatorMoveToHeightCommand());
		addParallel(new StopDriveTrainCommand());
	}
}