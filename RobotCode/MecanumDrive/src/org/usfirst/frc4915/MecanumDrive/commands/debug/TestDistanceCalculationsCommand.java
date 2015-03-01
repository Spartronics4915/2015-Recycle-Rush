package org.usfirst.frc4915.MecanumDrive.commands.debug;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;

/*
 * A test using the accelerometer to determine the distance traveled
 * provides constant feedback of distance traveled very time execute is called
 */
public class TestDistanceCalculationsCommand extends Command {

    double previousAccelerationX;
    double currentAccelerationX;
    double changeInAccelerationX;
    double previousAccelerationY;
    double currentAccelerationY;
    double changeInAccelerationY;
    double previousVelocityX;
    double currentVelocityX;
    double changeInVelocityX;
    double previousVelocityY;
    double currentVelocityY;
    double changeInVelocityY;
    double distanceX;
    double distanceY;
    double changeInDistanceX;
    double changeInDistanceY;

    long previousTime;
    long currentTime;
    long changeInTime;

    BuiltInAccelerometer accelerometer = RobotMap.accelerometer;

    public TestDistanceCalculationsCommand() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void end() {
        System.out.printf("The robot traveled %f meters in the x direction and %f meters in the y direction", distanceX, distanceY);
    }

    @Override
    protected void execute() {
        previousTime = currentTime;
        currentTime = System.currentTimeMillis();
        changeInTime = currentTime - previousTime;

        previousAccelerationX = currentAccelerationX;
        currentAccelerationX = accelerometer.getX();
        previousAccelerationY = currentAccelerationY;
        currentAccelerationY = accelerometer.getY();
        changeInAccelerationX = currentAccelerationX - previousAccelerationX;
        changeInAccelerationY = currentAccelerationY - previousAccelerationY;

        previousVelocityX = currentVelocityX;
        previousVelocityY = currentVelocityY;
        currentVelocityX = gForceToSSI(changeInAccelerationX) * milliSecondsToSSI(changeInTime);
        currentVelocityY = gForceToSSI(changeInAccelerationY) * milliSecondsToSSI(changeInTime);

        changeInDistanceX = currentVelocityX * milliSecondsToSSI(changeInTime);
        changeInDistanceY = currentVelocityY * milliSecondsToSSI(changeInTime);

        distanceX += changeInDistanceX;
        distanceY += changeInDistanceY;

        System.out.printf("The robot traveled %f meters in the x direction and %f meters in the y direction", distanceX, distanceY);
    }

    private double gForceToSSI(double gForce) {
        return gForce * 9.8;
    }

    private double milliSecondsToSSI(long milliseconds) {
        return milliseconds / 1000d;
    }

    @Override
    protected void initialize() {
        currentTime = System.currentTimeMillis();
        currentAccelerationX = accelerometer.getX();
        currentAccelerationY = accelerometer.getY();
        currentVelocityX = 0;
        currentVelocityY = 0;
        distanceX = 0;
        distanceY = 0;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
