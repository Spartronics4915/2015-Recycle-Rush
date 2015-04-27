package org.usfirst.frc4915.MecanumDrive;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.I2C.Port;

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

    private static final int HOOK_SOLENOID_CHANNEL = 7;
    //private static final int HOOK_FORWARD_CHANNEL = 6;
    //private static final int HOOK_REVERSE_CHANNEL = 7;
    
    //public final static int SOLENOID_CHANNEL_AUNTIE = 3;
    // TODO explain the reason for this number
    public static final double DEFAULT_MAX_OUTPUT = 950;
    private static final int FWD_SOFT_LIMIT = 1023;
    private static final int REV_SOFT_LIMIT = 0;
	
    /*
     * DRIVETRAIN
     */
    public static CANTalon mecanumDriveControlsLeftFront;
    public static CANTalon mecanumDriveControlsLeftRear;
    public static CANTalon mecanumDriveControlsRightFront;
    public static CANTalon mecanumDriveControlsRightRear;
    public static RobotDrive driveTrainRobotDrive;
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
    public static DigitalInput slackLimitSwitch;
    public static DigitalInput bottomLimitSwitch;

    /*
     * GRABBER
     */
    public static Solenoid primarySolenoid;
    public static Solenoid secondarySolenoid;

    /*
     * GENERAL SENSORS
     */
    public static BuiltInAccelerometer accelerometer;

    /*
     * ARDUINO
     */
    //public static I2C wire;
    
    /*
     * CONTAINER HOOK
     */
    public static Solenoid containerHookPneumatic;
    
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
        gyro = new Gyro(GYRO_PORT);

		/*
         * Distance instantiation -- UNUSED
		 */
        distanceSensor = new Ultrasonic(ULTRASONIC_PORT_FIRST, ULTRASONIC_PORT_SECOND);

		/*
		 * MECANUM WHEEL END
		 */

		/*
		 * ELEVATOR START
		 */
        // ELEVATOR instantiation
        elevatorWinchMotor = new CANTalon(MOTOR_PORT_ELEVATOR_WINCH);
        elevatorWinchMotor.changeControlMode(ControlMode.Position);
        elevatorWinchMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
        elevatorWinchMotor.setPID(20, .03, 0.6, 0.0001, 20, 0, 0); //Values we determined after the gearbox was lubricated
        elevatorWinchMotor.setForwardSoftLimit(FWD_SOFT_LIMIT); // The absolute maximum height that the elevator can be
        elevatorWinchMotor.setReverseSoftLimit(REV_SOFT_LIMIT); // The greater minimum height that the elevator can be
        elevatorWinchMotor.enableForwardSoftLimit(true);
        elevatorWinchMotor.enableReverseSoftLimit(true);
        elevatorWinchMotor.ConfigFwdLimitSwitchNormallyOpen(true);
        elevatorWinchMotor.ConfigRevLimitSwitchNormallyOpen(true);
        elevatorWinchMotor.enableBrakeMode(true);
        //slackLimitSwitch = new DigitalInput(1);
        //bottomLimitSwitch = new DigitalInput(5);

        // Potentiometer instantiation

        // TODO Limit Switches instantiation goes here
        bottomLimitSwitch = new DigitalInput(5);
		
		/*
		 * ELEVATOR END
		 */

		/*
		 * GRABBER START
		 */
        // Double Solenoid instantiation. Wiring: 0 --> Forward channel
        // (extended). 1 --> Reverse channel (retracted).

        primarySolenoid = new Solenoid(PCM_NODE_ID, SOLENOID_CHANNEL_PRIMARY);
        secondarySolenoid = new Solenoid(PCM_NODE_ID, SOLENOID_CHANNEL_SECONDARY);
		

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
        
        /*
         * CONTAINER HOOK START
         */
        //containerHookPneumatic = new DoubleSolenoid(HOOK_FORWARD_CHANNEL, HOOK_REVERSE_CHANNEL);
        containerHookPneumatic = new Solenoid(PCM_NODE_ID, HOOK_SOLENOID_CHANNEL);
        /*
         * ARDUINO START
         */
        //wire = new I2C(Port.kOnboard, 4); // TODO confirm port and device address
        /*
         * ARDUIN0 END
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

        // Sets PID Values for the Mecanum Drive Train
        if (mode.equals(ControlMode.Speed)) {
            mecanumDriveControlsLeftFront.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
            mecanumDriveControlsLeftRear.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
            mecanumDriveControlsRightFront.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
            mecanumDriveControlsRightRear.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
        }

    }
}
