package org.usfirst.frc4915.MecanumDrive.commands.drive;

import java.util.List;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightGivenDistanceCommand extends Command {
	public static List<CANTalon> motors = DriveTrain.motors;
	public double inputDistance;
	public double speed;
	private DriveTrain driveTrain = Robot.driveTrain;

	public MoveStraightGivenDistanceCommand(double inputDistance, double speed) {
		requires(driveTrain);
		this.inputDistance = inputDistance;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	long elapsed;
	long startTime;
	long endTime;
	double distance;
	double distanceSinceElapsed;

	// Called just before this Command runs the first time
	/**
	 * This initializes the variables for the distance calculator.
	 */
	protected void initialize() {

		// Creates variables for use in determining the time so we can compute
		// the distance traveled
		elapsed = 0;
		startTime = System.currentTimeMillis();
		System.out.println("Start time recorded: " + startTime);
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
			System.out.println("Driving backwards...");
			driveTrain.driveStraight(-speed);
		} else {
			System.out.println("Driving forwards...");
			driveTrain.driveStraight(speed);
		}
		// creates a loop to track the distance traveled
		// uses the time to determine the distance traveled since the last time
		// the robot was sampled.
		endTime = System.currentTimeMillis();
		elapsed = endTime - startTime;
		distanceSinceElapsed = 0;
		System.out.println("Recorded end time: " + endTime + " (difference of " + elapsed + " seconds)s");
		// maybe a method???
		for (CANTalon motor : motors) {
			double distanceForMotor = driveTrain.getDistanceForMotor(motor, elapsed);
			System.out.println("Distance for motor " + motor + ": " + distanceForMotor);
			distanceSinceElapsed = Math.max(distanceForMotor, distanceSinceElapsed);
			System.out.println("Distance since elapsed: " + distanceSinceElapsed);
		}

		distance += distanceSinceElapsed;
		startTime = endTime;
		System.out.println("Completed movement of " + distance + " ft.");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean finished = (Math.abs(distance) > Math.abs(inputDistance));
		System.out.println("Testing if finished: " + finished);
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Stopping motor");
		driveTrain.getRobotDrive().stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("Command interrupted!");
		end();
	}
}
