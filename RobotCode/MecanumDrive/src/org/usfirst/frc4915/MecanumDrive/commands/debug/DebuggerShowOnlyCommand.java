package org.usfirst.frc4915.MecanumDrive.commands.debug;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

public class DebuggerShowOnlyCommand extends Command {
    private LoggerNames name;

    public DebuggerShowOnlyCommand(LoggerNames name) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.name = name;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.debugger.setFilter(name);
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
