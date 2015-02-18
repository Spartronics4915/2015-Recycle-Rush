package org.usfirst.frc4915.MecanumDrive.commands.autonomous;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrain;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Turn90Degrees;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Wait;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumber;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

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

    	addParallel(new ElevatorMoveToHeight());
    	addParallel(new StopDriveTrain());
       	System.out.println("Moving Elevator (level 1.5)");
    	addSequential(new ElevatorJumpToPosition(1.5), 0.7);
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
    	System.out.println("Moving Elevator (level 2.5)");
    	addSequential(new ElevatorJumpToPosition(2.5), 0.7);
    	System.out.println("Strafing");
    	//strafe left 1.9ft
    	addSequential(new StrafeCommand(-1.9,.5));
    	addSequential(new OpenGrabber());
    	addSequential(new ElevatorJumpToPosition(0), 1.0);
    	addSequential(new CloseGrabber());
    	addSequential(new ElevatorJumpToPosition(1), 0.7);
    	System.out.println("Driving back 12 ft");
    	// FIXME change the value to -12
    	addSequential(new MoveStraightPositionModeCommand(-2, 0.7));
       	System.out.println("Moving Elevator (level 1.2)");
    	addSequential(new ElevatorJumpToPosition(1.2));
    	addSequential(new ElevatorIsAbovePositionNumber(15), 1.2);
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
    	addSequential(new Wait(.5));
    	System.out.println("Moving Elevator (level 2.8)");
    	addSequential(new ElevatorJumpToPosition(2.8));
    	addSequential(new ElevatorIsAbovePositionNumber(34), 1.2);
    	System.out.println("Driving back 9.5 ft");
    	addSequential(new MoveStraightPositionModeCommand(9.5, 0.7));
    	addSequential(new OpenGrabber());
    	addSequential(new Wait(1));
    	System.out.println("Moving Elevator (level 0)");
    	addSequential(new ElevatorJumpToPosition(0));
    	addSequential(new ElevatorIsBelowPositionNumber(0.5), 2.5);
    	addSequential(new CloseGrabber());
    	addSequential(new Wait(.5));
    	//inches
    	addSequential(new ElevatorJumpToPosition(1));
    	addSequential(new ElevatorIsAbovePositionNumber(12), 2);
    	addSequential(new Turn90Degrees(false));
    	addSequential(new MoveStraightPositionModeCommand(-9.5,0.7)); //going left 9.5 ft // prefer
    	addSequential(new ElevatorJumpToPosition(0));
    	addSequential(new ElevatorIsBelowPositionNumber(0.5), 3);
    	addSequential(new OpenGrabber());
    }
}
