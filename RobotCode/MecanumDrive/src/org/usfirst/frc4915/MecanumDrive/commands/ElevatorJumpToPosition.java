package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorJumpToPosition extends Command {
	Elevator elevator = Robot.elevator;
	int numberOfTotes;
	private double heightOfPosition;
	
    public ElevatorJumpToPosition(double heightOfPosition, int numberOfTotes) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	this.heightOfPosition = heightOfPosition;
    	this.numberOfTotes = numberOfTotes;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Moving elevator to position" + numberOfTotes);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(elevator.isOverMaxHeight()) {
    		elevator.moveToPosition(elevator.POSITION_SIX);
    	}
    	else if(elevator.isBelowMinHeight()) {
    		elevator.moveToPosition(elevator.POSITION_ZERO);
    	}
    	else {
    		elevator.moveToPosition(heightOfPosition);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Elevator is in position " + numberOfTotes);
    	elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
