package org.usfirst.frc4915.MecanumDrive.commands;


public class Auto3 {
	int count = 10;
	int i;
	String message;

    public Auto3(String message) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.message = message;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.printf("** Saying \"%s\" %d times.%n", message, count);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.printf("** %s (%d/%d)%n", message, i, count);
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

