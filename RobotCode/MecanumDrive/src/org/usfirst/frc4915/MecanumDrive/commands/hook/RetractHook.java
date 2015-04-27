package org.usfirst.frc4915.MecanumDrive.commands.hook;

import org.usfirst.frc4915.MecanumDrive.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetractHook extends Command {

    public RetractHook() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.hook);
    	setTimeout(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hook.retractPneumatic();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
