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

public class AutonomousCommandTwoContainers extends CommandGroup {

	// DEPRECATED --- DO NOT USE!!!
    public AutonomousCommandTwoContainers() {
   

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
        addSequential(new MoveStraightPositionModeCommand(-7.5, 0.7));

        addSequential(new StrafeCommand(10));   // right inaccurate distance //
        System.out.println("Moving Elevator (level 1.3)");
        addSequential(new ElevatorJumpToPositionCommand(1.3));
        addSequential(new ElevatorIsBelowPositionNumberCommand(16), 1.2);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.5)");
        addSequential(new ElevatorJumpToPositionCommand(2.5));
        addSequential(new ElevatorIsAbovePositionNumberCommand(30), 1.2);
        System.out.println("Driving forward 11 ft");
        addSequential(new MoveStraightPositionModeCommand(2, 0.7));

        System.out.println("Moving Elevator (level 1.5)"); //puts down container
        addSequential(new ElevatorJumpToPositionCommand(1.5));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(18), 3);
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(.5));

    }
}