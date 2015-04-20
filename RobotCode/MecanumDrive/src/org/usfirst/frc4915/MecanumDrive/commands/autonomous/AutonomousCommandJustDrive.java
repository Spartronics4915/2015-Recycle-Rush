package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;

public class AutonomousCommandJustDrive extends CommandGroup {

    public AutonomousCommandJustDrive() {
		addParallel(new ElevatorMoveToHeightCommand());
		addParallel(new StopDriveTrainCommand());
        System.out.println("***Running Just Drive Command***");
        // Change to 4 ft from 5 ft
        addSequential(new MoveStraightPositionModeCommand(5, 1));
    }
}
