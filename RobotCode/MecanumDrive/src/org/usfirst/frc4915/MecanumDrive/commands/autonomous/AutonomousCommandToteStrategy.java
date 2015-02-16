package org.usfirst.frc4915.MecanumDrive.commands.autonomous;
<<<<<<< HEAD

=======
>>>>>>> e234c23db517a6dcc0fcac4a2edd59942de06aa6
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

import edu.wpi.first.wpilibj.command.CommandGroup;

<<<<<<< HEAD
public class AutonomousCommandToteStrategy extends CommandGroup {
    
=======
public class AutonomousCommandToteStrategy extends CommandGroup {    
>>>>>>> e234c23db517a6dcc0fcac4a2edd59942de06aa6
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

<<<<<<< HEAD
    	addParallel(new ElevatorMoveToHeight());
    	
=======
        addParallel(new ElevatorMoveToHeight());
    	System.out.println("Moving Elevator");
>>>>>>> e234c23db517a6dcc0fcac4a2edd59942de06aa6
    	System.out.println("Moving Elevator (level 0)");
    	addSequential(new ElevatorJumpToPosition(0));
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
<<<<<<< HEAD
=======
    	System.out.println("Moving Elevator");
    	addSequential(new ElevatorJumpToPosition(1));
    	System.out.println("Driving backwards 12 ft");
    	addSequential(new MoveStraightPositionModeCommand(-12, 0.7));
>>>>>>> e234c23db517a6dcc0fcac4a2edd59942de06aa6
    	System.out.println("Moving Elevator (level 1)");
    	addSequential(new ElevatorJumpToPosition(1));
    	System.out.println("Driving back 12 ft");
    	addSequential(new MoveStraightPositionModeCommand(-12, 0.7));

    }
}
