package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Turn90DegreesCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandContainerAndReturn extends CommandGroup {
    
    public  AutonomousCommandContainerAndReturn() {
        addParallel(new ElevatorMoveToHeightCommand());
        addParallel(new StopDriveTrainCommand());

        System.out.println("Moving Elevator (level 1.3)");
        addSequential(new ElevatorJumpToPositionCommand(1));
        addSequential(new ElevatorIsAbovePositionNumberCommand(11), 1.2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.5)");
        addSequential(new ElevatorJumpToPositionCommand(2.5), 0.7);
        addSequential(new ElevatorIsAbovePositionNumberCommand(29), 1.2);
        System.out.println("Driving back 8.5 ft");
        addSequential(new MoveStraightPositionModeCommand(-8.5, 1));

        //Turn Left
        addSequential(new Turn90DegreesCommand(true));
        
        System.out.println("Moving Elevator (level 1)"); //puts down container
        addSequential(new ElevatorJumpToPositionCommand(1));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(13), 3);
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(.5));
        
        // Raise the elevator to level 3 (above the container
        addSequential(new ElevatorJumpToPositionCommand(3));
        addSequential(new ElevatorIsAbovePositionNumberCommand(30), 3);
        
        // Should go right 10 feet - needs testing
        addSequential(new StrafeCommand(10), 5);
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(2), 1.2);
        
        addSequential(new WaitCommand(.5));
        
    }
}
