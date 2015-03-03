package org.usfirst.frc4915.MecanumDrive.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.MecanumDrive.Robot;

public class ToggleDriveModeCommand extends Command {

    public ToggleDriveModeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //Robot.debugger.logError(LoggerNames.DRIVETRAIN,"Before Toggling Drive Mode=" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE") );
        System.out.println("Before Toggling Drive Mode=" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE"));
        Robot.driveTrain.toggleFieldMode();
        //Robot.debugger.logError(LoggerNames.DRIVETRAIN,"After Toggling Drive Mode" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE") );
        System.out.println("After Toggling Drive Mode" + ((Robot.driveTrain.toggleFieldMode()) ? "TRUE" : "FALSE"));
        SmartDashboard.putBoolean("Field Mode", Robot.driveTrain.fieldMode);
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
