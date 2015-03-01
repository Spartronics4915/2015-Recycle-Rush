package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Wait;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

public class AutonomousCommandTwoTotesOneContainer extends CommandGroup {

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

        addParallel(new ElevatorMoveToHeight());

        System.out.println("Moving Elevator (level 1.2)");
        addSequential(new ElevatorJumpToPosition(1.2));
        addSequential(new ElevatorIsAbovePositionNumber(15), 1.2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabber());
        addSequential(new Wait(.5));
        System.out.println("Moving Elevator (level 2.8)");
        addSequential(new ElevatorJumpToPosition(2.8));
        addSequential(new ElevatorIsAbovePositionNumber(34), 1.2);
        System.out.println("Driving forward 1.1 ft");
        addSequential(new MoveStraightPositionModeCommand(1.1, 0.7));
        addSequential(new OpenGrabber()); //drops container
        addSequential(new Wait(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPosition(0));
        addSequential(new ElevatorIsBelowPositionNumber(0.5), 3);
        addSequential(new CloseGrabber());
        addSequential(new Wait(.5));
        //inches
        System.out.println("Moving Elevator (level 1.3)");
        addSequential(new ElevatorJumpToPosition(1.3));
        addSequential(new ElevatorIsAbovePositionNumber(16), 2);
        addSequential(new MoveStraightPositionModeCommand(6.75, 0.7));
        System.out.println("Driving forward 1.1 ft");
        addSequential(new OpenGrabber()); //drops stack
        addSequential(new Wait(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPosition(0));
        addSequential(new ElevatorIsBelowPositionNumber(0.5), 3);
        addSequential(new CloseGrabber());
        addSequential(new Wait(.5));

        System.out.println("Moving Elevator (level 1)");
        addSequential(new ElevatorJumpToPosition(1));
        addSequential(new ElevatorIsAbovePositionNumber(12), 2);
        addSequential(new StrafeCommand(-9, 0.7)); //going left 9 ft
        addSequential(new ElevatorJumpToPosition(0));
        addSequential(new ElevatorIsBelowPositionNumber(0.5), 3);
        addSequential(new OpenGrabber());
    }
}