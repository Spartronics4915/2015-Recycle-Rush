package org.usfirst.frc4915.MecanumDrive.commands.autonomous;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
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

    	System.out.println("Moving Elevator");
    	addSequential(new ElevatorJumpToPosition(1.5));
    	System.out.println("Moving Grabber");
    	addSequential(new CloseGrabber());
    	System.out.println("Driving 15 ft");
    	addSequential(new MoveStraightPositionModeCommand(5, 0.7));
    	System.out.println("Moving Grabber");
    	addSequential(new OpenGrabber());
    	addSequential(new MoveStraightPositionModeCommand(-1, 0.7));
    	System.out.println("Driving 1 ft");
}
}