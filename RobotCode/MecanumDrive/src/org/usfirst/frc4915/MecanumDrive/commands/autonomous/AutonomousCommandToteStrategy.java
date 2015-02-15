package org.usfirst.frc4915.MecanumDrive.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandToteStrategy extends CommandGroup {
    
//    public AutonomousCommandToteStrategy() {
//        // Add Commands here:
//        // e.g. addSequential(new Command1());
//        //      addSequential(new Command2());
//        // these will run in order.
//
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());
//        //      addSequential(new Command2());
//        // Command1 and Command2 will run in parallel.
//
//        // A command group will require all of the subsystems that each member
//        // would require.
//        // e.g. if Command1 requires chassis, and Command2 requires arm,
//        // a CommandGroup containing them would require both the chassis and the
//        // arm.
//    	
//    	
//    	addSequential(new ElevatorMoveToHeight()); //should be parallel
//        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Moving Elevator");
//    	addSequential(new ElevatorMoveToHeight());
//        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Moving Grabber");
//    	addSequential(new CloseGrabber());
//        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Driving 15 ft");
//    	addSequential(new MoveStraightPositionModeCommand(-5));
//        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Moving Grabber");
//    	addSequential(new OpenGrabber());
//    	addSequential(new MoveStraightPositionModeCommand(-1));
//        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Driving 1 ft");
//    	
//    	//Below should use commands to avoid bugs
//    	//Robot.elevator.setHeightToPosition(0);
//    	//Robot.grabber.close();
//    	//Robot.grabber.open();
//    }
}
