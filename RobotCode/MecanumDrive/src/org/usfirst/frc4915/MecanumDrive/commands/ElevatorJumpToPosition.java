package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorJumpToPosition extends Command {
	Elevator elevator = Robot.elevator;
	// TODO use only the position (what level the elevator should go to) as an input
	// Position number -- number of totes that you would need to stack on top of.
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
    	elevator.setPosition(heightOfPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    // TODO This command doesn't end
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
