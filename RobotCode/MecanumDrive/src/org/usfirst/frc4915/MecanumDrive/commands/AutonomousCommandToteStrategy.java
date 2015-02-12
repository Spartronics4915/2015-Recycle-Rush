package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandToteStrategy extends CommandGroup {
    
    public AutonomousCommandToteStrategy() {
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
    	addSequential(new ElevatorMoveToHeight());
    	System.out.println("Moving Grabber");
    	addSequential(new CloseGrabber());
    	System.out.println("Driving 15 ft");
    	addSequential(new MoveStraightGivenDistanceCommand(-15));
    	System.out.println("Moving Grabber");
    	addSequential(new OpenGrabber());
    	addSequential(new MoveStraightGivenDistanceCommand(-1));
    	System.out.println("Driving 1 ft");
    	
    	Robot.elevator.setHeightToPosition(0);
    	Robot.grabber.close();
    	Robot.grabber.open();
    }
}
