package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightGivenDistanceCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandToteAndAlign extends CommandGroup {
    
    public  AutonomousCommandToteAndAlign() {
    	addParallel(new ElevatorMoveToHeightCommand());
        addParallel(new StopDriveTrainCommand());
        
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        addSequential(new ElevatorJumpToPositionCommand(.5));
        addSequential(new ElevatorIsAbovePositionNumberCommand(3));
        
        addSequential(new MoveStraightGivenDistanceCommand(3, .5));
    }
}
