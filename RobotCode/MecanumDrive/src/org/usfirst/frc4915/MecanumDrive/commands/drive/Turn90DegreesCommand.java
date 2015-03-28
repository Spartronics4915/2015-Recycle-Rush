package org.usfirst.frc4915.MecanumDrive.commands.drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;

import java.util.List;

public class Turn90DegreesCommand extends Command {
    public static List<CANTalon> motors = DriveTrain.motors;
    private boolean goLeft;

    public Turn90DegreesCommand(boolean left) {
        System.out.println("**** Turn 90 construction ****");
        requires(Robot.driveTrain);
        goLeft = left;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.calibrateGyro();
        System.out.println("******************In Turn 90 command initialize ******  " + Robot.driveTrain.trackGyro());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrain.turn(goLeft);
        System.out.println("******************In Turn 90 command execute ******  " + Robot.driveTrain.trackGyro());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (DriveTrain.gyro != null) {
            System.out.println("Turn 90: track Gyro " + Robot.driveTrain.trackGyro());
            return (Math.abs(Robot.driveTrain.trackGyro()) >= 90);
        } else {
            setTimeout(.7);
            return isTimedOut();
        }
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
