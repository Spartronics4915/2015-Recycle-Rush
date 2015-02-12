package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import org.usfirst.frc4915.MecanumDrive.Robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

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
<<<<<<< HEAD
    	//System.out.println("Moving Elevator");
    	//addSequential(new ElevatorMoveToHeight());
    	//System.out.println("Moving Grabber");
    	//addSequential(new CloseGrabber());
    	//System.out.println("Driving 15 ft");
    	addSequential(new MoveStraightPositionModeCommand(5, 0.7));
    	//System.out.println("Moving Grabber");
    	//addSequential(new OpenGrabber());
    	//addSequential(new MoveStraightPositionModeCommand(-1, 0.7));
    	//System.out.println("Driving 1 ft");

=======
    	
    	
    	addSequential(new ElevatorMoveToHeight()); //should be parallel
    	System.out.println("Moving Elevator");
    	addSequential(new ElevatorMoveToHeight());
    	System.out.println("Moving Grabber");
    	addSequential(new CloseGrabber());
    	System.out.println("Driving 15 ft");
    	addSequential(new MoveStraightPositionModeCommand(-5));
    	System.out.println("Moving Grabber");
    	addSequential(new OpenGrabber());
    	addSequential(new MoveStraightPositionModeCommand(-1));
    	System.out.println("Driving 1 ft");
    	
    	//Below should use commands to avoid bugs
    	//Robot.elevator.setHeightToPosition(0);
    	//Robot.grabber.close();
    	//Robot.grabber.open();
>>>>>>> 13f2656a8edac18ed4f15b5259a7a8169a5ea824
    }
}
