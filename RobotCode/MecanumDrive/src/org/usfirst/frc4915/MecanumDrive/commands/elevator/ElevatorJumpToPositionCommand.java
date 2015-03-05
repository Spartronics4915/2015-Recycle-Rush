package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

public class ElevatorJumpToPositionCommand extends Command {
    Elevator elevator = Robot.elevator;

    // Position number -- number of totes that you would need to stack on top
    // of.
    private double positionNumber;
    
    public ElevatorJumpToPositionCommand(double d) {
        positionNumber = d;
        // DON'T require Robot.elevator
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator Initialized");
        elevator.setHeightToPosition(positionNumber);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Changes height
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.debugger.logError(LoggerNames.ELEVATOR, "Jumped to position " + positionNumber);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
