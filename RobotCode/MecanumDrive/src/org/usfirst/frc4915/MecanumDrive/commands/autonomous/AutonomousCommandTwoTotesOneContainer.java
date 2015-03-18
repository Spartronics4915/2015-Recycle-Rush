package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

public class AutonomousCommandTwoTotesOneContainer extends CommandGroup {

	// DEPRECATED -- DO NOT USE!!!
    public AutonomousCommandTwoTotesOneContainer() {
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

        System.out.println("Moving Elevator (level 1.2)");
        addSequential(new ElevatorJumpToPositionCommand(1.2));
        addSequential(new ElevatorIsAbovePositionNumberCommand(15), 1.2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.8)");
        addSequential(new ElevatorJumpToPositionCommand(2.8));
        addSequential(new ElevatorIsAbovePositionNumberCommand(34), 1.2);
        System.out.println("Driving forward 1.1 ft");
        addSequential(new MoveStraightPositionModeCommand(1.1, 0.7));
        addSequential(new OpenGrabberCommand()); //drops container
        addSequential(new WaitCommand(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 3);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        //inches
        System.out.println("Moving Elevator (level 1.3)");
        addSequential(new ElevatorJumpToPositionCommand(1.3));
        addSequential(new ElevatorIsAbovePositionNumberCommand(16), 2);
        addSequential(new MoveStraightPositionModeCommand(6.75, 0.7));
        System.out.println("Driving forward 1.1 ft");
        addSequential(new OpenGrabberCommand()); //drops stack
        addSequential(new WaitCommand(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 3);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));

        System.out.println("Moving Elevator (level 1)");
        addSequential(new ElevatorJumpToPositionCommand(1));
        addSequential(new ElevatorIsAbovePositionNumberCommand(12), 2);
        addSequential(new StrafeCommand(-9)); //going left 9 ft
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 3);
        addSequential(new OpenGrabberCommand());
    }
}