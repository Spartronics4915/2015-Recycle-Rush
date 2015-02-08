package org.usfirst.frc4915.MecanumDrive.commands;

import org.usfirst.frc4915.MecanumDrive.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Auto1 extends Command {
	int count = 10;
	int i;
	String message;
	double distance;

    public Auto1(String message, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	System.out.println("***CONSTRUCTOR TO MOVE "+distance+" FT***");
    	this.message = message;
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.printf("** Saying \"%s\" %d times.%n", message, count);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.printf("** %s (%d/%d)%n", message, i, count);
    	System.out.println("***TO MOVE "+distance+" FT***");
    	i++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return i == count;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.printf("** Finished saying \"%s\" %d times.%n", message, count);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.printf("** Interrupted whilst saying \"%s\" %d times.%n", message, count);
    }
}

