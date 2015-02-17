package org.usfirst.frc4915.MecanumDrive.commands.autonomous;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Wait;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

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
    	
    	addParallel(new ElevatorMoveToHeight());

    	System.out.println("Moving Elevator (level 1.5)");
    	addSequential(new ElevatorJumpToPosition(1.5));
    	addSequential(new ElevatorIsAbovePositionNumber(18), 1.2);
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
    	addSequential(new Wait(.5));
    	System.out.println("Moving Elevator (level 2.5)");
    	addSequential(new ElevatorJumpToPosition(2.5));
    	addSequential(new ElevatorIsAbovePositionNumber(30), 1.2);
    	System.out.println("Driving forward 7.5 ft");
    	addSequential(new MoveStraightPositionModeCommand(2, 0.7)); // //
    	
    	System.out.println("Moving Elevator (level 1.5)"); //puts down container
    	addSequential(new ElevatorJumpToPosition(1.5));
    	//inches
    	addSequential(new ElevatorIsBelowPositionNumber(18), 3);
    	addSequential(new OpenGrabber());
    	addSequential(new Wait(.5));
    	System.out.println("Moving Elevator (level 2.5)"); 
    	addSequential(new ElevatorJumpToPosition(2.5));
    	addSequential(new ElevatorIsAbovePositionNumber(30), 1.2);
    	System.out.println("Driving backward 7.5 ft");
    	addSequential(new MoveStraightPositionModeCommand(-2, 0.7)); // //
    	
    	addSequential(new StrafeCommand(6.75, 0.7));   // right //
    	System.out.println("Moving Elevator (level 1.5)"); 
    	addSequential(new ElevatorJumpToPosition(1.5));
    	addSequential(new ElevatorIsBelowPositionNumber(18), 1.2);
    	addSequential(new CloseGrabber());
    	addSequential(new Wait(.5));
    	System.out.println("Moving Elevator (level 2.5)");
    	addSequential(new ElevatorJumpToPosition(2.5));
    	addSequential(new ElevatorIsAbovePositionNumber(30), 1.2);
    	System.out.println("Driving forward 11 ft");
    	addSequential(new MoveStraightPositionModeCommand(2, 0.7)); // //
    	
    	System.out.println("Moving Elevator (level 1.5)"); //puts down container
    	addSequential(new ElevatorJumpToPosition(1.5));
    	//inches
    	addSequential(new ElevatorIsBelowPositionNumber(18), 3);
    	addSequential(new OpenGrabber());
    	addSequential(new Wait(.5));

    }
}