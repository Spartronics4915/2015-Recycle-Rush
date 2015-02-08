package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import edu.wpi.first.wpilibj.command.Command;


public class ElevatorFineTune extends Command {

	Elevator elevator = Robot.elevator;
    public ElevatorFineTune() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.debugger.logError(LoggerNames.ELEVATOR, "ElevatorFineTune initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Constantly makes the motor move to whatever height is, and changes hieght based on the joystick
    	elevator.moveWithJoystick(Robot.oi.elevatorStick);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.debugger.logError(LoggerNames.ELEVATOR, "ElevatorFineTune deactivated");
    	elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
