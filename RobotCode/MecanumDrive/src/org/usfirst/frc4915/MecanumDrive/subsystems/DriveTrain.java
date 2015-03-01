package org.usfirst.frc4915.MecanumDrive.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MecanumDriveCommand;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import java.util.Arrays;
import java.util.List;

public class DriveTrain extends Subsystem {

    public static List<CANTalon> motors = Arrays.asList(RobotMap.mecanumDriveControlsLeftFront, RobotMap.mecanumDriveControlsLeftRear, RobotMap.mecanumDriveControlsRightFront, RobotMap.mecanumDriveControlsRightRear);
    public static Gyro gyro = RobotMap.gyro;
    public static Ultrasonic distanceSensor = RobotMap.distanceSensor;
    public double throttle = 0;
    public double deltaGyro = 0;
    public double gyroHeading = 0;
    public double startingAngle = 0;
    public boolean fieldMode = false;
    RobotDrive robotDrive;
    CustomDebugger debugger = Robot.debugger;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MecanumDriveCommand());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        robotDrive = RobotMap.driveTrainRobotDrive;
    }

    public RobotDrive getRobotDrive() {
        return robotDrive;
    }

    /**
     * Drives a mecanum drivetrain in the direction of the joystick pointed
     * Since the motors are enabled to use their encoders in RobotMap, each
     * motor should go at the speeds that they need to more accurately. Because
     * of this, we do not want to use setMaxOutput, because the value set in
     * RobotMap.java is needed to be the same.
     *
     * @param joystick Joystick controlling the robot movement
     */
    public void mecanumDrive(Joystick joystick) {

        double joystickX = joystick.getAxis(Joystick.AxisType.kX);
        double joystickY = joystick.getAxis(Joystick.AxisType.kY);
        double joystickTwist = joystick.getAxis(Joystick.AxisType.kTwist);

        throttle = 0.40 * (-joystick.getThrottle()) + 0.60;
        debugger.logError(LoggerNames.DRIVETRAIN, "Throttle Value: " + throttle);

        boolean deadZoneX = Math.abs(joystickX) < 0.2;
        boolean deadZoneY = Math.abs(joystickY) < 0.2;
        boolean deadZoneTwist = Math.abs(joystickTwist) < 0.2;

        if (deadZoneX) joystickX = 0;
        if (deadZoneY) joystickY = 0;
        if (deadZoneTwist) joystickTwist = 0;

        double throttleX = throttle * joystickX;
        double throttleY = throttle * joystickY;
        double throttleTwist = throttle * joystickTwist;

        //Gyro Tracking and debug
        Robot.driveTrain.trackGyro();
        SmartDashboard.putNumber("Gyro Angle", Robot.driveTrain.gyroHeading);


        debugger.logError(LoggerNames.DRIVETRAIN, ("Joystick: " + joystickX + ", " + joystickY + ", " + joystickTwist));
        debugger.logError(LoggerNames.DRIVETRAIN, ("Throttle: " + throttleX + ", " + throttleY + ", " + throttleTwist));
        if (deadZoneX && deadZoneY && deadZoneTwist) {
            debugger.logError(LoggerNames.DRIVETRAIN, ("Stopping Motor"));
            robotDrive.stopMotor();
        } else {
            debugger.logError(LoggerNames.DRIVETRAIN, "Gyro Angle: " + gyro.getAngle());
            robotDrive.mecanumDrive_Cartesian(throttleX, throttleY, throttleTwist, 0);
            //, fieldMode ? gyro.getAngle() : 0);

            robotDrive.mecanumDrive_Cartesian(throttleX, throttleY, throttleTwist, fieldMode == true ? gyroHeading : 0);
        }


    }

    /**
     * Calibrates the gyro using gyro.reset()
     */
    public void calibrateGyro() {
        gyro.reset();
    }

    /**
     * Drives using mecanumDrive_Cartesian forward
     *
     * @param speed that it drives at.
     */
    public void driveStraight(double speed) {
        robotDrive.mecanumDrive_Cartesian(0.0, speed, 0.0, 0.0);
    }

    /**
     * Drives using mecanumDrive_Cartesian sideways
     *
     * @param speed that it drives at
     */
    public void driveSideways(double speed) {
        robotDrive.mecanumDrive_Cartesian(speed, 0.0, 0.0, 0.0);
    }

    public void turnLeft(boolean left) {
        // left is true, right is false
        for (int i = 0; i < motors.size(); i++) {
            CANTalon motor = motors.get(i);
            RobotMap.changeControlMode(ControlMode.Speed);
            if (left) {
                robotDrive.mecanumDrive_Cartesian(0, 0, -.5, 0);
            } else {
                robotDrive.mecanumDrive_Cartesian(0, 0, .5, 0);
            }
        }
//			if ( i == 0 || i == 1) {
//				if (left) {
//					motor.set(-.7);
//				}
//				else {
//					motor.set(.7);
//				}
//			}
//			else if ( i == 2 || i== 3) {
//				if (left) {
//					motor.set(-.7);
//				}
//				else {
//					motor.set(.7);
//				}
//			}
    }


    /**
     * calculates the distance traveled using the wheel circumference and the
     * number of wheel rotations.
     *
     * @param motor   Motor with an encoder to determine distance traveled.
     * @param elapsed Time since the last sampling of the motor.
     * @return Distance traveled since the last sampling of the encoder.
     */
    // calculates the distance traveled using the wheel circumference and the
    // number of wheel rotations.
    public double getDistanceForMotor(CANTalon motor, long elapsed) {
        int ticksPerRevolution = 1000;
        double circumferenceOfWheel = 6 * Math.PI;
        int inchesPerFoot = 12;
        debugger.logError(LoggerNames.DRIVETRAIN, ("Speed" + motor.getSpeed()));
        debugger.setFilter(LoggerNames.DRIVETRAIN);
        debugger.resetFilter();
        return motor.getSpeed() * elapsed / ticksPerRevolution * circumferenceOfWheel / inchesPerFoot;
    }

    /**
     * Drives like ATLaS. Forward/Back for straight and backwards, and left right to spin in place.
     *
     * @param stick used to control the DriveTrain.
     */
    public void arcadeDrive(Joystick stick) {
        debugger.logError(LoggerNames.DRIVETRAIN, "Arcade Drive");
        debugger.setFilter(LoggerNames.DRIVETRAIN);
        debugger.resetFilter();
        robotDrive.arcadeDrive(stick);
    }

    /**
     * Swaps from field mode to the opposite mode.
     *
     * @return what mode it is in - true for field mode.
     */
    public boolean toggleFieldMode() {
        fieldMode = !fieldMode;
        return fieldMode;
    }

    public double trackGyro() {
        gyroHeading = -gyro.getAngle() + startingAngle;

        debugger.logError(LoggerNames.DRIVETRAIN, "Change in angle: " + deltaGyro);
        debugger.logError(LoggerNames.DRIVETRAIN, "Robot angle: " + gyroHeading);
        return gyroHeading;
    }
}
