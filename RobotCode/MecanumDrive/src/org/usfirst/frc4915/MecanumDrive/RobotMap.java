package org.usfirst.frc4915.MecanumDrive;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;

//TODO decide and finalize the input ports for each sensor

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/*
	 * CONSTANTS
	 */
	public static final int MOTOR_PORT_LEFT_FRONT = 10;
	public static final int MOTOR_PORT_LEFT_REAR = 11;
	public static final int MOTOR_PORT_RIGHT_FRONT = 12;
	public static final int MOTOR_PORT_RIGHT_REAR = 13;
	
	public static final int MOTOR_PORT_ELEVATOR_WINCH = 14;
	
	/*
	 * The Pneumatic Control Module's CAN Node ID. Use 10 for 4915. Use 20 for 9999
	 */
	public final static int PCM_NODE_ID = 10;
	
	public final static int GYRO_PORT = 0;
	
	public final static int ULTRASONIC_PORT_FIRST = 0;
	public final static int ULTRASONIC_PORT_SECOND = 1;
	
	public final static int SOLENOID_CHANNEL_PRIMARY = 0;
	public final static int SOLENOID_CHANNEL_SECONDARY = 1;
	
	public final static int SOLENOID_CHANNEL_AUNTIE = 3;
	
	/*
	 * DRIVETRAIN
	 */
	public static CANTalon mecanumDriveControlsLeftFront;
	public static CANTalon mecanumDriveControlsLeftRear;
	public static CANTalon mecanumDriveControlsRightFront;
	public static CANTalon mecanumDriveControlsRightRear;
	public static RobotDrive driveTrainRobotDrive;
	// TODO explain the reason for this number
	public static final double DEFAULT_MAX_OUTPUT = 950;


	/*
	 * Gyroscope
	 */
	public static Gyro gyro;

	/* 
	 * Distance Sensor
	 */
	public static Ultrasonic distanceSensor;

	/*
	 * ELEVATOR
	 */
	public static CANTalon elevatorWinchMotor;
	private static final int FWD_SOFT_LIMIT = 1023;
	private static final int REV_SOFT_LIMIT = 0;

	/*
	 * Limit Switches
	 */

	// public static DigitalInput limitSwitchBottom; // May be used for elevator
	// as a sensor for testing if at the bottom of elevator
	// public static DigitalInput limitSwitchTop; // May be used for elevator as
	// a sensor for testing if at the top of elevator
	// Potentiometer
	// private static final int SCALE = 1; // TODO find correct scale for the
	// potentiometer
	// public static AnalogPotentiometer potentiometer;

	/*
	 * GRABBER
	 */
	public static Solenoid primarySolenoid;
	public static Solenoid secondarySolenoid;

	/*
	 * GENERAL SENSORS
	 */
	public static BuiltInAccelerometer accelerometer;

	public static void init() {

		/*
		 * MECANUM WHEEL START
		 */
		mecanumDriveControlsLeftFront = new CANTalon(MOTOR_PORT_LEFT_FRONT);
		mecanumDriveControlsLeftRear = new CANTalon(MOTOR_PORT_LEFT_REAR);
		mecanumDriveControlsRightFront = new CANTalon(MOTOR_PORT_RIGHT_FRONT);
		mecanumDriveControlsRightRear = new CANTalon(MOTOR_PORT_RIGHT_REAR);

		changeControlMode(ControlMode.Speed);

		driveTrainRobotDrive = new RobotDrive(mecanumDriveControlsLeftFront, mecanumDriveControlsLeftRear, mecanumDriveControlsRightFront, mecanumDriveControlsRightRear);
		// Sets the max output to ???, 10ft per 1 secf -- After testing, we have
		// decided to go with 950.
		driveTrainRobotDrive.setMaxOutput(DEFAULT_MAX_OUTPUT);

		driveTrainRobotDrive.setSafetyEnabled(true);
		driveTrainRobotDrive.setExpiration(0.1);
		driveTrainRobotDrive.setSensitivity(0.5);

		driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

		/*
		 * Gyro instantiation
		 */
		gyro = new Gyro(GYRO_PORT); // TODO decide on ports

		/*
		 * Distance instantiation
		 */
		distanceSensor = new Ultrasonic(ULTRASONIC_PORT_FIRST, ULTRASONIC_PORT_SECOND); // TODO decide on ports

		/*
		 * MECANUM WHEEL END
		 */

		/*
		 * ELEVATOR START
		 */
		// ELEVATOR instantiation
		// TODO set limit switch configuration on the winch motor
		elevatorWinchMotor = new CANTalon(MOTOR_PORT_ELEVATOR_WINCH);
		elevatorWinchMotor.changeControlMode(ControlMode.Position);
		elevatorWinchMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		elevatorWinchMotor.setPID(25, 0, 0, 0.0001, 255, 17, 0); // TODO determine PID values and ramp rate
		// P = 25 I = 0 D = 0 ==> Pretty accurate. Overshoots about one inch, and has a little error afterwards.
		elevatorWinchMotor.setForwardSoftLimit(FWD_SOFT_LIMIT); // The absolute maximum height that the elevator can be
		elevatorWinchMotor.setReverseSoftLimit(REV_SOFT_LIMIT); // The greater minimum height that the elevator can be
		elevatorWinchMotor.enableForwardSoftLimit(true);
		elevatorWinchMotor.enableReverseSoftLimit(true);
		elevatorWinchMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		elevatorWinchMotor.ConfigRevLimitSwitchNormallyOpen(true);
		elevatorWinchMotor.enableBrakeMode(true);
		
		// Potentiometer instantiation
		
		// TODO Limit Switches instantiation goes here
		
		/*
		 * ELEVATOR END
		 */

		/*
		 * GRABBER START
		 */
		// Double Solenoid instantiation. Wiring: 0 --> Forward channel
		// (extended). 1 --> Reverse channel (retracted).
		//mommaSolenoid = new DoubleSolenoid(PCM_NODE_ID, SOLENOID_CHANNEL_MOMMA_FORWARD, SOLENOID_CHANNEL_MOMMA_REVERSE); // Uses 10 as the Node ID for the PCM
		primarySolenoid = new Solenoid(PCM_NODE_ID, SOLENOID_CHANNEL_PRIMARY);
		secondarySolenoid = new Solenoid(PCM_NODE_ID,SOLENOID_CHANNEL_SECONDARY); 

		/*
		 * GRABBER END
		 */

		/*
		 * GENERAL SENSORS START
		 */
		// Built in Accelerometer
		accelerometer = new BuiltInAccelerometer();
		accelerometer.startLiveWindowMode();

		/*
		 * SENSORS END
		 */
	}

	public static void changeControlMode(ControlMode mode) {
		// Set control mode for the CAN Talon motor controllers
		mecanumDriveControlsLeftFront.changeControlMode(mode);
		mecanumDriveControlsLeftRear.changeControlMode(mode);
		mecanumDriveControlsRightFront.changeControlMode(mode);
		mecanumDriveControlsRightRear.changeControlMode(mode);

		// Makes sure the Feedback Device is a Quad Encoder
		mecanumDriveControlsLeftFront.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		mecanumDriveControlsLeftRear.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		mecanumDriveControlsRightFront.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		mecanumDriveControlsRightRear.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		// TODO confirm that these values are what we want
		// Sets PID Values for the Mecanum Drive Train
		if (mode.equals(ControlMode.Speed)) {
			mecanumDriveControlsLeftFront.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
			mecanumDriveControlsLeftRear.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
			mecanumDriveControlsRightFront.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
			mecanumDriveControlsRightRear.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
		}

	}
}
