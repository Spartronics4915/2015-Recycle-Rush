package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMinHeightCalibrate extends Command {

	Elevator elevator = Robot.elevator;

    public ElevatorMinHeightCalibrate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevator.moveElevator(-.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevator.isAtBottomOfElevator();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Elevator.minimumPotentiometerValue = elevator.getPositionInches();
    	Robot.debugger.logError(LoggerNames.ELEVATOR, "Minimum position value " + Elevator.minimumPotentiometerValue);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
