package org.usfirst.frc4915.MecanumDrive.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MecanumDriveCommand;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import java.util.Arrays;
import java.util.List;

public class DriveTrain extends Subsystem {

	RobotDrive robotDrive;
	CustomDebugger debugger = Robot.debugger;

	public static List<CANTalon> motors = Arrays.asList(RobotMap.mecanumDriveControlsLeftFront, RobotMap.mecanumDriveControlsLeftRear, RobotMap.mecanumDriveControlsRightFront, RobotMap.mecanumDriveControlsRightRear);

	public static Gyro gyro = RobotMap.gyro;
	public static Ultrasonic distanceSensor = RobotMap.distanceSensor;

	public double throttle = 0;
	
	public double deltaGyro = 0;
	public double gyroHeading = 0;
	public double startingAngle = 0;
	public boolean fieldMode = false; 

	public static final double SECTOR_15º_RATIO = .27;
	public static final double DEFAULT_BUFFER = .2;
	public static final double DOUBLE = 2;
	
	public static final double WHEEL_DIAMETER = 6;

	private static final int SPEED_BUTTON = 11;
	private static final double DEFAULT_TWIST_SCALE = .5;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initDefaultCommand() {
		setDefaultCommand(new MecanumDriveCommand());

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
		boolean speedButton = joystick.getRawButton(SPEED_BUTTON);
		
		double twistScale = DEFAULT_TWIST_SCALE;
		
		if (speedButton) {
			twistScale = 1;
		}
		
		throttle = 0.40 * (-joystick.getThrottle()) + 0.60;
		debugger.logError(LoggerNames.DRIVETRAIN, "Throttle Value: " + throttle);
		
		if (Math.abs(joystickX) <= .2) {
			joystickX = 0;
		}
		if (Math.abs(joystickY) <= .2) {
			joystickY = 0;
		}
		
		boolean strafeOnly = (Math.abs(joystickY) < SECTOR_15º_RATIO * Math.abs(joystickX));
		boolean forwardOnly = (Math.abs(joystickX) < SECTOR_15º_RATIO * Math.abs(joystickY));
		
		double bufferX = DEFAULT_BUFFER;
		double bufferY = DEFAULT_BUFFER;
		double bufferZ = DEFAULT_BUFFER;

		if (strafeOnly) {
			bufferY *= DOUBLE;
			bufferZ *= DOUBLE;
		}
		if (forwardOnly) {
			bufferX *= DOUBLE;
			bufferZ *= DOUBLE;
		}
		
		boolean deadZoneX = Math.abs(joystickX) < bufferX;
		boolean deadZoneY = Math.abs(joystickY) < bufferY;
		boolean deadZoneTwist = Math.abs(joystickTwist) < bufferZ;
		
		if (deadZoneX) joystickX = 0;
		if (deadZoneY || strafeOnly) joystickY = 0;
		if (deadZoneTwist || strafeOnly) joystickTwist = 0;
		
		double throttleX = throttle*joystickX;
		double throttleY = throttle*joystickY;
		double throttleTwist = throttle*twistScale*joystickTwist;
		
		//Gyro Tracking and debug
		Robot.driveTrain.trackGyro();


		debugger.logError(LoggerNames.DRIVETRAIN, ("Joystick: "+ joystickX + ", " + joystickY + ", " + joystickTwist));
		debugger.logError(LoggerNames.DRIVETRAIN, ("Throttle: "+ throttleX + ", " + throttleY + ", " + throttleTwist));
		if (deadZoneX && deadZoneY && deadZoneTwist) {
			debugger.logError(LoggerNames.DRIVETRAIN, ("Stopping Motor"));
			robotDrive.stopMotor();
		} else if (fieldMode) {
			robotDrive.mecanumDrive_Cartesian(throttleX, throttleY, throttleTwist, gyro.getAngle());
		} else {
			debugger.logError(LoggerNames.DRIVETRAIN,"Gyro Angle: " + gyro.getAngle());
			robotDrive.mecanumDrive_Cartesian(throttleX, throttleY, throttleTwist, 0);
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
	 * @param speed that it drives at.
	 */
	public void driveStraight(double speed) {
		robotDrive.mecanumDrive_Cartesian(0.0, speed, 0.0, 0.0);
	}
	
	/**
	 * Drives using mecanumDrive_Cartesian sideways
	 * @param speed that it drives at
	 */
	public void driveSideways(double speed){
		robotDrive.mecanumDrive_Cartesian(speed, 0.0, 0.0, 0.0);
	}
	
	/**
	 * Turns the robot.
	 * @param left Whether to turn left (true), or right (false)
	 */
	public void turn(boolean left){
		for (int i = 0; i < motors.size(); i++) {
			RobotMap.changeControlMode(ControlMode.Speed);
			if (left) {
				robotDrive.mecanumDrive_Cartesian(0, 0, -.5, 0);
			}
			else {
				robotDrive.mecanumDrive_Cartesian(0, 0, .5, 0);
			}
		}
    }

    /**
     * Calculates the distance traveled using the wheel circumference and the
     * number of wheel rotations.
     *
     * @param motor   Motor with an encoder to determine distance traveled.
     * @param elapsed Time since the last sampling of the motor.
     * @return Distance traveled since the last sampling of the encoder.
     */
    public double getDistanceForMotor(CANTalon motor, long elapsed) {
        int ticksPerRevolution = 1000;
        double circumferenceOfWheel = WHEEL_DIAMETER * Math.PI;
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
