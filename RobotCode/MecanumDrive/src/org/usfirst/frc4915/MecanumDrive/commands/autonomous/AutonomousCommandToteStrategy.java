package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StopDriveTrainCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.WaitCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

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
        addParallel(new ElevatorMoveToHeightCommand());
        addParallel(new StopDriveTrainCommand());
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPositionCommand(0));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(.5), 2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 1)");
        addSequential(new ElevatorJumpToPositionCommand(1));
        //position
        addSequential(new ElevatorIsBelowPositionNumberCommand(12), 1.2);
        System.out.println("Driving back 12 ft");
        // FIXME Change the drive value to -12
        addSequential(new MoveStraightPositionModeCommand(-2, 0.7));
        addSequential(new MoveStraightPositionModeCommand(-2, 0.7));
        System.out.println("Driving back 9.5 ft");
        addSequential(new MoveStraightPositionModeCommand(-9.5, 0.7));
        System.out.println("Moving Elevator (level 0)"); //puts down tote
        addSequential(new ElevatorJumpToPositionCommand(0));
        //inches
        addSequential(new ElevatorIsBelowPositionNumberCommand(.5), 2);
        addSequential(new OpenGrabberCommand());

    }
}
