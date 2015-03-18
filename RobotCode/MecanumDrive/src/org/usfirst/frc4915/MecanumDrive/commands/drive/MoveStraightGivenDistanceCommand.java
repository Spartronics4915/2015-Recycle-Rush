package org.usfirst.frc4915.MecanumDrive.commands.drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;

import java.util.List;

public class MoveStraightGivenDistanceCommand extends Command {
    public static List<CANTalon> motors = DriveTrain.motors;
    public double inputDistance;
    public double movementSpeed;
    long elapsed;
    long startTime;
    long endTime;
    double distance;
    double distanceSinceElapsed;
    private DriveTrain driveTrain = Robot.driveTrain;

    /**
     * 
     * @param inputDistance Distance in feet
     * @param speed between 0 and 1. Positive or negative will have no effect on the result.
 * 					The default value is 1.
     */
    public MoveStraightGivenDistanceCommand(double inputDistance, double speed) {
        requires(driveTrain);
        this.inputDistance = inputDistance;
        movementSpeed = Math.abs(speed);
        // Distance in feet
    }

    // Called just before this Command runs the first time

    /**
     * This initializes the variables for the distance calculator.
     */
    protected void initialize() {

        // Creates variables for use in determining the time so we can compute
        // the distance traveled
        elapsed = 0;
        startTime = System.currentTimeMillis();
        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Start time recorded: " + startTime);
        endTime = 0;
        // creates a variable to keep track of the time.
        distance = 0;
    }

    /**
     * This uses the wheel circumference and the number of rotations to compute
     * the distance traveled.
     */

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // This loop allows for a negative input that will have the robot run
        // backwards.
        if (inputDistance < 0) {
            Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Driving backwards...");
            driveTrain.driveStraight(-movementSpeed);
        } else {
            Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Driving forwards...");
            driveTrain.driveStraight(movementSpeed);
        }
        // creates a loop to track the distance traveled
        // uses the time to determine the distance traveled since the last time
        // the robot was sampled.
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        distanceSinceElapsed = 0;
        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Recorded end time: " + endTime + " (difference of " + elapsed + " seconds)s");
        // maybe a method???
        for (CANTalon motor : motors) {
            double distanceForMotor = driveTrain.getDistanceForMotor(motor, elapsed);
            Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Distance for motor " + motor + ": " + distanceForMotor);
            distanceSinceElapsed = Math.max(distanceForMotor, distanceSinceElapsed);
            Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Distance since elapsed: " + distanceSinceElapsed);
        }

        distance += distanceSinceElapsed;
        startTime = endTime;
        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Completed movement of " + distance + " ft.");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean finished = (Math.abs(distance) > Math.abs(inputDistance));
        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Testing if finished: " + finished);
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Stopping motor");
        driveTrain.getRobotDrive().stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.debugger.logError(CustomDebugger.LoggerNames.DRIVETRAIN, "Command interrupted!");
        end();
    }
}
