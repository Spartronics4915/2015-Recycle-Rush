package org.usfirst.frc4915.MecanumDrive.commands.hook;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.ContainerHook;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleContainerHookStateCommand extends Command {

	ContainerHook hook = Robot.hook;
	
    public ToggleContainerHookStateCommand() {
    	requires(hook);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hook.togglePneumaticState();
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
    	end();
    }
}
