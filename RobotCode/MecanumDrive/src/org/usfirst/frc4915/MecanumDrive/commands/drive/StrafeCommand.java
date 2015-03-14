package org.usfirst.frc4915.MecanumDrive.commands.drive;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class StrafeCommand extends Command {
    public static List<CANTalon> motors = DriveTrain.motors;
    public double inputDistance;
    public double inputSpeed;
    private DriveTrain driveTrain = Robot.driveTrain;
    private List<Double> desiredTicksValue;

    public StrafeCommand(double inputDistance, double inputSpeed) {
        requires(driveTrain);
        System.out.println("***StrafeCommand inputDistance: " + inputDistance + "*******");
        System.out.println("***StrafeCommand inputSpeed: " + inputSpeed + "*******");
        this.inputDistance = inputDistance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time

    /**
     * This initializes the variables for the distance calculator.
     */
    protected void initialize() {
        desiredTicksValue = new ArrayList<Double>();

        double ticksToMove = inputDistance * 12 * 1000 / (6 * Math.PI);

        System.out.println("Input distance: " + inputDistance + " ft, ticks to move: " + ticksToMove);
        System.out.println("*************READY TO DRIVE**************");
        for (int i = 0; i < motors.size(); i++) {
            CANTalon motor = motors.get(i);

            double startingTickValue = motor.getPosition();
            double endValue = startingTickValue + ticksToMove;
            if (i == 1 || i == 3) {
                // right motors are inverted and we are reversing the back motors
                endValue = startingTickValue - ticksToMove;
            }

            System.out.println("Motor " + i + ": starting position " + startingTickValue + ", desired position " + endValue);
            desiredTicksValue.add(endValue);
        }
    }

    /**
     * This uses the wheel circumference and the number of rotations to compute
     * the distance traveled.
     */

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (inputDistance < 0)
            driveTrain.driveSideways(-.7);
        else
            driveTrain.driveSideways(.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // checking to see if the front motors have finished (regardless of what direction the robot is driving)
        if (inputDistance > 0)
            return isMotorFinished(0) || isMotorFinished(2);
        else
            return isMotorFinished(1) || isMotorFinished(3);
    }

    private boolean isMotorFinished(int i) {
        boolean finished = false;
        double currentPosition = motors.get(i).getPosition();
        double desiredPosition = desiredTicksValue.get(i);
        System.out.println("**____******____****Motor " + i + ": current position: " + currentPosition + ", desired position " + desiredPosition);

        if (i == 1 || i == 3) {
            // flipping back motors... right motors are inverted
            if (inputDistance < 0) {
                finished = currentPosition >= desiredPosition;
            } else {
                finished = currentPosition <= desiredPosition;
            }
        } else {
            if (inputDistance < 0) {
                finished = currentPosition <= desiredPosition;
            } else {
                finished = currentPosition >= desiredPosition;
            }
        }
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
