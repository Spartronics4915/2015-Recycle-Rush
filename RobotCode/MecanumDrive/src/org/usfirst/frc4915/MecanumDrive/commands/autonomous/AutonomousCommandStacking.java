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

public class AutonomousCommandStacking extends CommandGroup {

    public AutonomousCommandStacking() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

        addParallel(new ElevatorMoveToHeightCommand());
        addParallel(new StopDriveTrainCommand());

        System.out.println("Moving Elevator (level 1.2)");
        addSequential(new ElevatorJumpToPositionCommand(1.2));
        addSequential(new ElevatorIsAbovePositionNumberCommand(15), 1.2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.8)");
        addSequential(new ElevatorJumpToPositionCommand(2.8));
        addSequential(new ElevatorIsAbovePositionNumberCommand(34), 1.2);

        System.out.println("Driving forward 2 ft");
        addSequential(new MoveStraightPositionModeCommand(2, 0.7));
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 2.5);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        //inches
        addSequential(new ElevatorJumpToPositionCommand(1));
        addSequential(new ElevatorIsAbovePositionNumberCommand(12), 2);
        addSequential(new Turn90DegreesCommand(false));
        addSequential(new MoveStraightPositionModeCommand(-9.5, 0.7)); //going left 9.5 ft // prefer
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 3);
        addSequential(new OpenGrabberCommand());

    }
}
