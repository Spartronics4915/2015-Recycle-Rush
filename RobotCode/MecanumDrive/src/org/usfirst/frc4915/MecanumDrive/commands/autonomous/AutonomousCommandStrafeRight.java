package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandStrafeRight extends CommandGroup {
    
    public  AutonomousCommandStrafeRight() {
    	addParallel(new ElevatorMoveToHeightCommand());
        addParallel(new StopDriveTrainCommand());
        
        addSequential(new StrafeCommand(6)); //Strafes right 6 feet
    }
}
