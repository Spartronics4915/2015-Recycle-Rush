package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

public class ElevatorMaxHeightCalibrateCommand extends Command {

    Elevator elevator = Robot.elevator;

    public ElevatorMaxHeightCalibrateCommand() {
        // DON'T require Robot.elevator
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Elevator.setPoint = 1023; // Through experimentation, the maximum is closer to 973
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevator.isAtTopOfElevator();
    }

    // Called once after isFinished returns true
    protected void end() {
        Elevator.maximumPotentiometerValue = elevator.getPositionInches();
        Robot.debugger.logError(LoggerNames.ELEVATOR, "Maximum position value " + Elevator.maximumPotentiometerValue);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
