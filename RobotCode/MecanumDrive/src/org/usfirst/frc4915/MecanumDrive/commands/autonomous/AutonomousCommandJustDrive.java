package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
<<<<<<< HEAD
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

=======
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;
>>>>>>> e234c23db517a6dcc0fcac4a2edd59942de06aa6
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandJustDrive extends CommandGroup {
    
    public  AutonomousCommandJustDrive() {
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
    	addSequential(new MoveStraightPositionModeCommand(5,0.7));
=======
    	addSequential( new MoveStraightPositionModeCommand(5, 0.7));
    	addSequential(new ElevatorMoveToHeight());
    	addSequential(new CloseGrabber());
    	addSequential(new MoveStraightPositionModeCommand(4,0.7));
    	addSequential(new OpenGrabber());
    	addSequential(new MoveStraightPositionModeCommand(1,0.2));
    	addSequential( new StrafeCommand(3, 0.7));
>>>>>>> e234c23db517a6dcc0fcac4a2edd59942de06aa6
    }
}
