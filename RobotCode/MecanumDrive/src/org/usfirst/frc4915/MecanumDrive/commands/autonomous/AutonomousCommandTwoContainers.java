package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandTwoContainers extends CommandGroup {

    public AutonomousCommandTwoContainers() {
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

        System.out.println("Moving Elevator (level 1.3)");
        addSequential(new ElevatorJumpToPositionCommand(1.3));
        addSequential(new ElevatorIsAbovePositionNumberCommand(16), 1.2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.5)");
        addSequential(new ElevatorJumpToPositionCommand(2.5));
        addSequential(new ElevatorIsAbovePositionNumberCommand(30), 1.2);
        System.out.println("Driving forward 7.5 ft");
        addSequential(new MoveStraightPositionModeCommand(7.5, 0.7)); // //

        System.out.println("Moving Elevator (level 1.5)"); //puts down container
        addSequential(new ElevatorJumpToPositionCommand(1.5));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(18), 3);
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.5)");
        addSequential(new ElevatorJumpToPositionCommand(2.5));
        addSequential(new ElevatorIsAbovePositionNumberCommand(30), 1.2);
        System.out.println("Driving backward 7.5 ft");
        addSequential(new MoveStraightPositionModeCommand(-7.5, 0.7)); // //

        addSequential(new StrafeCommand(10, 0.7));   // right inaccurate distance //
        System.out.println("Moving Elevator (level 1.3)");
        addSequential(new ElevatorJumpToPositionCommand(1.3));
        addSequential(new ElevatorIsBelowPositionNumberCommand(16), 1.2);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.5)");
        addSequential(new ElevatorJumpToPositionCommand(2.5));
        addSequential(new ElevatorIsAbovePositionNumberCommand(30), 1.2);
        System.out.println("Driving forward 11 ft");
        addSequential(new MoveStraightPositionModeCommand(2, 0.7)); // //

        System.out.println("Moving Elevator (level 1.5)"); //puts down container
        addSequential(new ElevatorJumpToPositionCommand(1.5));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(18), 3);
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(.5));

    }
}