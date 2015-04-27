package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightGivenDistanceCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.hook.ToggleContainerHookStateCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandHookAndDrive extends CommandGroup {
    
    public  AutonomousCommandHookAndDrive() {
		addParallel(new ElevatorMoveToHeightCommand());
		addParallel(new StopDriveTrainCommand());
		
		addSequential(new ToggleContainerHookStateCommand());
		addSequential(new WaitCommand(2));
		addSequential(new MoveStraightPositionModeCommand(5,1));
		
    }
}
