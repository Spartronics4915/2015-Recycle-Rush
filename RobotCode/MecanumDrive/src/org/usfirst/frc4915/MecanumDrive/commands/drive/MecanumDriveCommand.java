package org.usfirst.frc4915.MecanumDrive.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

public class MecanumDriveCommand extends Command {
    CustomDebugger debugger;

    public MecanumDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.debugger.logError(LoggerNames.DRIVETRAIN, "Starting MecanumDrive Command");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Joystick joystickDrive = Robot.oi.driveStick;
        Robot.driveTrain.mecanumDrive(joystickDrive);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.getRobotDrive().stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
