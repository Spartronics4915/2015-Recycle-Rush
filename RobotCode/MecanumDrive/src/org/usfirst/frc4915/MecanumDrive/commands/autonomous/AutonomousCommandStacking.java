package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4915.MecanumDrive.commands.drive.*;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsAbovePositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorIsBelowPositionNumberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;

public class AutonomousCommandStacking extends CommandGroup {

    public AutonomousCommandStacking() {
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
        
        System.out.println("Moving Elevator (level 1.2)");
        addSequential(new ElevatorJumpToPositionCommand(1.2));
        addSequential(new ElevatorIsAbovePositionNumberCommand(15), 1.2);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        System.out.println("Moving Elevator (level 2.8)");
        addSequential(new ElevatorJumpToPositionCommand(2.8));
        addSequential(new ElevatorIsAbovePositionNumberCommand(34), 1.2);
        
        System.out.println("Driving forward 2 ft");
        addSequential(new MoveStraightPositionModeCommand(2, 1));
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 2.5);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        //inches
        addSequential(new ElevatorJumpToPositionCommand(1));
        addSequential(new ElevatorIsAbovePositionNumberCommand(12), 2);
        addSequential(new Turn90DegreesCommand(false));
        addSequential(new MoveStraightPositionModeCommand(-9.5, 1)); //going left 9.5 ft // prefer
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 3);
        addSequential(new OpenGrabberCommand());
        
        /*
        System.out.println("Moving Elevator (level 1.2)");
        addSequential(new ElevatorJumpToPositionCommand(1.2), 0.7);
        System.out.println("Closing Grabber");
        addSequential(new CloseGrabberCommand());
        System.out.println("Moving Elevator (level 2.5)");
        addSequential(new ElevatorJumpToPositionCommand(2.5), 0.7);
        System.out.println("Strafing");
        //strafe left 1.9ft
        addSequential(new StrafeCommand(-1.9, .5));
        addSequential(new OpenGrabberCommand());
        addSequential(new ElevatorJumpToPositionCommand(0), 1.0);
        addSequential(new CloseGrabberCommand());
        addSequential(new ElevatorJumpToPositionCommand(1), 0.7);
        System.out.println("Driving back 12 ft");
        // FIXME change the value to -12
        addSequential(new MoveStraightPositionModeCommand(-2, 0.7));
        
        
        
        System.out.println("Driving back 9.5 ft");
        addSequential(new MoveStraightPositionModeCommand(9.5, 0.7));
        addSequential(new OpenGrabberCommand());
        addSequential(new WaitCommand(1));
        System.out.println("Moving Elevator (level 0)");
        addSequential(new ElevatorJumpToPositionCommand(0));
        addSequential(new ElevatorIsBelowPositionNumberCommand(0.5), 2.5);
        addSequential(new CloseGrabberCommand());
        addSequential(new WaitCommand(.5));
        //inches
        */
    }
}
