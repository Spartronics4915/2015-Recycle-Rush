package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;


import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */



public class ToggleDriveMode extends Command {

	
	
    public ToggleDriveMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//Robot.debugger.logError(LoggerNames.DRIVETRAIN,"Before Toggling Drive Mode=" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE") );
    	System.out.println("Before Toggling Drive Mode=" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE"));
    	Robot.driveTrain.toggleFieldMode();
    	//Robot.debugger.logError(LoggerNames.DRIVETRAIN,"After Toggling Drive Mode" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE") );
    	System.out.println("After Toggling Drive Mode" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE"));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
