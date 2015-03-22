package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Turn90DegreesCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandContainerWithPlatform extends CommandGroup {

	public AutonomousCommandContainerWithPlatform() {
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

		System.out.println("Moving Elevator (level 1.3)");
		addSequential(new ElevatorJumpToPositionCommand(1));
		addSequential(new ElevatorIsAbovePositionNumberCommand(11), 1.2);
		System.out.println("Closing Grabber");
		addSequential(new CloseGrabberCommand());
		addSequential(new WaitCommand(.5));
		System.out.println("Moving Elevator (level 2.5)");
		addSequential(new ElevatorJumpToPositionCommand(2.5), 0.7);
		addSequential(new ElevatorIsAbovePositionNumberCommand(29), 1.2);
		System.out.println("Driving back 10 ft");
		addSequential(new MoveStraightPositionModeCommand(-12, 1));
        
		addSequential(new Turn90DegreesCommand(true));
        
        System.out.println("Moving Elevator (level 1)"); //puts down container
        addSequential(new ElevatorJumpToPositionCommand(1));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(13), 2);
        addSequential(new OpenGrabberCommand());
        
        addSequential(new ElevatorJumpToPositionCommand(2.5), 0.7);
        addSequential(new ElevatorIsAbovePositionNumberCommand(30), 1);

        addSequential(new Turn90DegreesCommand(false));
	}
}