package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Turn90DegreesCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandTurn180WithTote extends CommandGroup {
    
    public  AutonomousCommandTurn180WithTote() {
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
    	addParallel(new StopDriveTrainCommand());
    	
    	addSequential(new ElevatorJumpToPositionCommand(0));
    	addSequential(new ElevatorIsBelowPositionNumberCommand(.5), 1.2);
    	
    	addSequential(new WaitCommand(2));
    	
    	addSequential(new CloseGrabberCommand());
    	
    	addSequential(new ElevatorJumpToPositionCommand(1));
    	addSequential(new ElevatorIsAbovePositionNumberCommand(11), 1.2);
    	
    	// Right turn
    	addSequential(new Turn90DegreesCommand(false));
    	addSequential(new Turn90DegreesCommand(false));
    	
    	addSequential(new ElevatorJumpToPositionCommand(0));
    	addSequential(new ElevatorIsBelowPositionNumberCommand(.5), 1.2);
    	
    }
}
