package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

public class ElevatorMoveToHeightCommand extends Command {

    Elevator elevator = Robot.elevator;

    public ElevatorMoveToHeightCommand() {
        requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        elevator.winch.enableControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elevator.moveToHeight();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
