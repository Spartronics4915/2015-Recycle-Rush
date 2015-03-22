package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorResetSettingsCommand extends Command {
	
    private static final double MINIMUM_POTENTIOMETER_VALUE = 0;
	private static final double MAXIMUM_POTENTIOMETER_VALUE = 1023;

	public ElevatorResetSettingsCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Elevator.minimumPotentiometerValue = MINIMUM_POTENTIOMETER_VALUE;
    	Elevator.maximumPotentiometerValue = MAXIMUM_POTENTIOMETER_VALUE;
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
