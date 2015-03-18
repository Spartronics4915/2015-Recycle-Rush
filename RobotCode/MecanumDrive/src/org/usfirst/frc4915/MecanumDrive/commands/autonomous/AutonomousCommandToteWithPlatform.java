package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandToteWithPlatform extends CommandGroup {

	public AutonomousCommandToteWithPlatform() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addParallel(new ElevatorMoveToHeightCommand());
		addParallel(new StopDriveTrainCommand());

		System.out.println("Moving Elevator (level 0)");
		addSequential(new ElevatorJumpToPositionCommand(0));
		// inches
		addSequential(new ElevatorIsBelowPositionNumberCommand(.5), 2);
		System.out.println("Closing Grabber");
		addSequential(new CloseGrabberCommand());
		addSequential(new WaitCommand(.5));
		System.out.println("Moving Elevator (level 1)");
		addSequential(new ElevatorJumpToPositionCommand(1));
		// position
		addSequential(new ElevatorIsAbovePositionNumberCommand(12), 1.2);

		addSequential(new MoveStraightPositionModeCommand(-12, 1));
		System.out.println("Moving Elevator (level 0)"); // puts down tote
		addSequential(new ElevatorJumpToPositionCommand(0));
		// inches
		addSequential(new ElevatorIsBelowPositionNumberCommand(.5), 2);
		addSequential(new OpenGrabberCommand());

	}

}