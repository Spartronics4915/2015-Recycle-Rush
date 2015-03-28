package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandJustGrab extends CommandGroup {
    
    public  AutonomousCommandJustGrab() {
		addParallel(new ElevatorMoveToHeightCommand());
		addParallel(new StopDriveTrainCommand());
		
		addSequential(new CloseGrabberCommand());
    }
}
