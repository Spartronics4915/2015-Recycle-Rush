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
    	System.out.println("++++++RUNNING AUTONOMOUS COMMAND TOTE STRATEGY");
    	addParallel(new ElevatorMoveToHeight());
    	addParallel(new StopDriveTrain());
    	
    	System.out.println("Moving Elevator (level 0)");
    	addSequential(new ElevatorJumpToPosition(0));
    	//inches
    	addSequential(new ElevatorIsBelowPositionNumber(.5), 2);
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
    	addSequential(new Wait(.5));
    	System.out.println("Moving Elevator (level 1)");
    	addSequential(new ElevatorJumpToPosition(1));
    	//position
    	addSequential(new ElevatorIsBelowPositionNumber(1), 1.2);
    	System.out.println("Driving back 12 ft");
    	addSequential(new MoveStraightPositionModeCommand(-2, 0.7));
    	
    	System.out.println("Moving Elevator (level 0)");
    	addSequential(new ElevatorJumpToPosition(0));
    	//inches
    	addSequential(new ElevatorIsBelowPositionNumber(.5), 2);
    	addSequential(new OpenGrabber());
    	


   
    }
}
