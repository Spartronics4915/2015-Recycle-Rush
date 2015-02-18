package org.usfirst.frc4915.MecanumDrive.commands.autonomous;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrain;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Wait;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandContainerStrategy extends CommandGroup {
    
    public AutonomousCommandContainerStrategy() {
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
    	addParallel(new StopDriveTrain());

    	System.out.println("Moving Elevator (level 1.3)");
    	addSequential(new ElevatorJumpToPosition(1.3));
    	addSequential(new ElevatorIsAbovePositionNumber(16), 1.2);
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
    	addSequential(new Wait(.5));
    	System.out.println("Moving Elevator (level 2.5)");

    	addSequential(new ElevatorJumpToPosition(2.5), 0.7);
    	System.out.println("Driving back 12 ft");
    	// FIXME change this drive value to -12
    	addSequential(new MoveStraightPositionModeCommand(-2, 0.7));
    	addSequential(new ElevatorJumpToPosition(2.5));
    	addSequential(new ElevatorIsAbovePositionNumber(30), 1.2);
    	System.out.println("Driving back 9.5 ft"); // // // // 
    	addSequential(new MoveStraightPositionModeCommand(-9.5, 0.7));
    	
    	System.out.println("Moving Elevator (level 1.5)"); //puts down container
    	addSequential(new ElevatorJumpToPosition(1.5));
    	//inches
    	addSequential(new ElevatorIsBelowPositionNumber(18), 3);
    	addSequential(new OpenGrabber());
    }
}