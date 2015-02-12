package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorStop extends Command {

	Elevator elevator = Robot.elevator;

	public ElevatorStop() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		elevator.stopElevator();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
