package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;

public class ElevatorSetSafetyCommand extends Command {

    private boolean safety;

    public ElevatorSetSafetyCommand(boolean value) {
        // DON'T require Robot.elevator
        safety = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.elevator.SAFETY = safety;
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
        Robot.elevator.SAFETY = safety;
        Robot.elevator.minimumPotentiometerValue = 0;
        Robot.elevator.maximumPotentiometerValue = 1023;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
