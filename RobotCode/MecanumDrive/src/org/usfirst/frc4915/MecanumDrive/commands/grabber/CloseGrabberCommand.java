package org.usfirst.frc4915.MecanumDrive.commands.grabber;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;

import edu.wpi.first.wpilibj.command.Command;

public class CloseGrabberCommand extends Command {

    public CloseGrabberCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.debugger.logError(CustomDebugger.LoggerNames.GRABBER, "Closing");
        Robot.grabber.secondaryOff();
        Robot.grabber.primaryOn();
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
