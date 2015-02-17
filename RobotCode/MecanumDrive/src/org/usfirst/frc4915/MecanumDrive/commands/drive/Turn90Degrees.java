package org.usfirst.frc4915.MecanumDrive.commands.drive;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class Turn90Degrees extends Command {
	public static List<CANTalon> motors = DriveTrain.motors;
	private boolean goLeft;
	public Turn90Degrees(boolean left) {
    	requires(Robot.driveTrain);  
    	goLeft = left;
    	}

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.driveTrain.turnLeft(goLeft);
    }
   

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	setTimeout(.7);
    	return isTimedOut();
	}	

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.getRobotDrive().stopMotor();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
