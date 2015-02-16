package org.usfirst.frc4915.MecanumDrive;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
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
	 * Robot Number- Should be ROBOT_Team Number for the robot in use. For G1 use ROBOT_4915, for G2 use ROBOT_9999.
	 */
	
	public static final int ROBOT_9999 = 20;
	public static final int ROBOT_4915 = 10;
	public static final int ROBOT_NUMBER = ROBOT_4915;
	
	/*
	 * CONSTANTS
	 */

	public static final int MOTOR_PORT_LEFT_FRONT = 0;
	public static final int MOTOR_PORT_LEFT_REAR = 1;
	public static final int MOTOR_PORT_RIGHT_FRONT = 2;
	public static final int MOTOR_PORT_RIGHT_REAR = 3;
	
	public static final int MOTOR_PORT_ELEVATOR_WINCH = 4;
	
	/*
	 * The Pneumatic Control Module's CAN Node ID. Use 10 for 4915. Use 20 for 9999
	 */
	public final static int PCM_NODE_ID = 0;	
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
	public static DigitalInput slackLimitSwitch;

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
		mecanumDriveControlsLeftFront = new CANTalon(MOTOR_PORT_LEFT_FRONT+ROBOT_NUMBER);
		mecanumDriveControlsLeftRear = new CANTalon(MOTOR_PORT_LEFT_REAR+ROBOT_NUMBER);
		mecanumDriveControlsRightFront = new CANTalon(MOTOR_PORT_RIGHT_FRONT+ROBOT_NUMBER);
		mecanumDriveControlsRightRear = new CANTalon(MOTOR_PORT_RIGHT_REAR+ROBOT_NUMBER);

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
		gyro = new Gyro(GYRO_PORT);

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
		elevatorWinchMotor = new CANTalon(MOTOR_PORT_ELEVATOR_WINCH+ROBOT_NUMBER);
		elevatorWinchMotor.changeControlMode(ControlMode.Position);
		elevatorWinchMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		elevatorWinchMotor.setPID(15, .01, 0.001, 0.0001, 25, 1, 0); //Values we determined after the gearbox was lubricated
		elevatorWinchMotor.setForwardSoftLimit(FWD_SOFT_LIMIT); // The absolute maximum height that the elevator can be
		elevatorWinchMotor.setReverseSoftLimit(REV_SOFT_LIMIT); // The greater minimum height that the elevator can be
		elevatorWinchMotor.enableForwardSoftLimit(true);
		elevatorWinchMotor.enableReverseSoftLimit(true);
		elevatorWinchMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		elevatorWinchMotor.ConfigRevLimitSwitchNormallyOpen(true);
		elevatorWinchMotor.enableBrakeMode(true);
		slackLimitSwitch = new DigitalInput(1);
		
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
		primarySolenoid = new Solenoid(PCM_NODE_ID+ROBOT_NUMBER, SOLENOID_CHANNEL_PRIMARY);
		secondarySolenoid = new Solenoid(PCM_NODE_ID+ROBOT_NUMBER,SOLENOID_CHANNEL_SECONDARY); 

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
