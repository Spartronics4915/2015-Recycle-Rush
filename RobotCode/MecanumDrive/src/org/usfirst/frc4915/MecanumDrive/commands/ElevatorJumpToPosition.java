package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorJumpToPosition extends Command {
	Elevator elevator = Robot.elevator;
	// TODO use only the position (what level the elevator should go to) as an input
	// Position number -- number of totes that you would need to stack on top of.
	
	private int positionNumber;
	private double height;
	
    public ElevatorJumpToPosition(int position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	positionNumber = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	height = elevator.convertPositionToHeight(positionNumber);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevator.setPosition(height);
    }

    // Make this return true when this Command no longer needs to run execute()
    // TODO This command doesn't end
    protected boolean isFinished() {
        return elevator.getPosition() == height;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator is in position " + positionNumber);
    	elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
