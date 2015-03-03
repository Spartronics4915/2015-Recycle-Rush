package org.usfirst.frc4915.MecanumDrive.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;

public class SetStartingAngleCommand extends Command {
    private double angle;

    public SetStartingAngleCommand(double startingAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        angle = startingAngle;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.startingAngle = angle;
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
