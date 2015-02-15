package org.usfirst.frc4915.MecanumDrive;

import org.usfirst.frc4915.MecanumDrive.commands.debug.DebuggerFilter;
import org.usfirst.frc4915.MecanumDrive.commands.debug.DebuggerFilterReset;
import org.usfirst.frc4915.MecanumDrive.commands.drive.DriveStraight;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.ToggleDriveMode;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorPositionCalibration;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorSetHeight;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorSetSafety;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorStop;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.IntermediateOpen;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.MecanumDrive.utility.VersionFinder;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	/*
	 * JOYSTICKS
	 */
	public Joystick driveStick;
	public Joystick elevatorStick;
	
	/*
	 * JOYSTICK BUTTONS (ELEVATOR)
	 */
	public JoystickButton elevatorJumpToPositionZero;
	public JoystickButton elevatorJumpToPositionOne;
	public JoystickButton elevatorJumpToPositionTwo;
	public JoystickButton elevatorJumpToPositionThree;
	public JoystickButton elevatorJumpToPositionFour;
	public JoystickButton elevatorJumpToPositionFive;
	public JoystickButton elevatorJumpToPositionSix;
	
	/*
	 * JOYSTICK BUTTONS (GRABBER)
	 */
	public JoystickButton grabberOpen;
	public JoystickButton grabberClosed;
	public JoystickButton grabberIntermediate;

	public OI() {

		/*
		 * JOYSTICKS
		 */
		driveStick = new Joystick(0);
		elevatorStick = new Joystick(1);

		/*
		 * JOYSTICK BUTTONS (ELEVATOR)
		 */
		elevatorJumpToPositionZero = new JoystickButton(elevatorStick, 6);
		elevatorJumpToPositionZero.whenPressed(new ElevatorJumpToPosition(0));
		elevatorJumpToPositionOne = new JoystickButton(elevatorStick, 7);
		elevatorJumpToPositionOne.whenPressed(new ElevatorJumpToPosition(1));
		elevatorJumpToPositionTwo = new JoystickButton(elevatorStick, 8);
		elevatorJumpToPositionTwo.whenPressed(new ElevatorJumpToPosition(2));
		elevatorJumpToPositionThree = new JoystickButton(elevatorStick, 9);
		elevatorJumpToPositionThree.whenPressed(new ElevatorJumpToPosition(3));
		elevatorJumpToPositionFour = new JoystickButton(elevatorStick, 10);
		elevatorJumpToPositionFour.whenPressed(new ElevatorJumpToPosition(4));
		
		
		/*
		 * Grabber Buttons
		 */
		
		grabberOpen = new JoystickButton(elevatorStick, 3);
		grabberOpen.whenPressed(new OpenGrabber());
		grabberClosed = new JoystickButton(elevatorStick, 2);
		grabberClosed.whenPressed(new CloseGrabber());
		grabberIntermediate = new JoystickButton(elevatorStick, 4);
		grabberIntermediate.whenPressed(new IntermediateOpen());
		
		
		/*
		 * AUTONOMOUS COMMAND
		 */
		//SmartDashboard.putData("Autonomous Command", new AutonomousCommandToteStrategy());
		
		/*
		 * DRIVE STRAIGHT
		 */
		SmartDashboard.putData("Move Straight 3 feet", new MoveStraightPositionModeCommand(3));
		SmartDashboard.putData("Move Backwards 3 feet", new MoveStraightPositionModeCommand(-3));
		SmartDashboard.putData("DriveStraight 1 second", new DriveStraight());
		
		/*
		 * TOGGLE FIELD ORIENTED DRIVE
		 */
		SmartDashboard.putData("Toggle Field Drive", new ToggleDriveMode());
		SmartDashboard.putBoolean("Field Mode", Robot.driveTrain.fieldMode);
		
		/*
		 * GRABBER
		 */

		SmartDashboard.putData("Close Grabber", new CloseGrabber());
		SmartDashboard.putData("Intermediate Open", new IntermediateOpen());
		SmartDashboard.putData("Open Grabber", new OpenGrabber());
		
		/*
		 * ELEVATOR
		 */
		SmartDashboard.putData("Jump to Elevator Position 0", new ElevatorJumpToPosition(0));
		SmartDashboard.putData("Jump to Elevator Position 1", new ElevatorJumpToPosition(1));
		SmartDashboard.putData("Jump to Elevator Position 2", new ElevatorJumpToPosition(2));
		SmartDashboard.putData("Jump to Elevator Position 3", new ElevatorJumpToPosition(3));
		SmartDashboard.putData("Jump to Elevator Position 4", new ElevatorJumpToPosition(4));
		SmartDashboard.putData("ElevatorPositionCalibration", new ElevatorPositionCalibration());
		SmartDashboard.putData("ElevatorStop", new ElevatorStop());
		SmartDashboard.putBoolean("Elevator At Top", Robot.elevator.isAtTopOfElevator());
		SmartDashboard.putBoolean("Elevator At Bottom", Robot.elevator.isAtBottomOfElevator());
		SmartDashboard.putNumber("Elevator Potentiometer Value", Robot.elevator.getPosition());
		SmartDashboard.putData("Set height to 700", new ElevatorSetHeight(700));
		SmartDashboard.putData("Set height to 850", new ElevatorSetHeight(850));
		SmartDashboard.putData("Disable Elevator Safety", new ElevatorSetSafety(false));
		SmartDashboard.putData("Enable Elevator Safety", new ElevatorSetSafety(true));		
		/*
		 * Debugger buttons
		 */
		
		SmartDashboard.putData("Set debugger to drivetrain", new DebuggerFilter(LoggerNames.DRIVETRAIN));
		SmartDashboard.putData("Set debugger to grabber", new DebuggerFilter(LoggerNames.GRABBER));
		SmartDashboard.putData("Set debugger to general", new DebuggerFilter(LoggerNames.GENERAL));
		SmartDashboard.putData("Set debugger to autonomous", new DebuggerFilter(LoggerNames.AUTONOMOUS));
		SmartDashboard.putData("Set debugger to elevator", new DebuggerFilter(LoggerNames.ELEVATOR));
		SmartDashboard.putData("Reset debugger filter", new DebuggerFilterReset());
		
		/*
		 * SENSOR OUTPUT
		 */
		LiveWindow.addSensor("Other Sensors", "Accelerometer", RobotMap.accelerometer);
		LiveWindow.addSensor("Drive Train", "Distance Sensor", DriveTrain.distanceSensor);
		LiveWindow.addActuator("Grabber Primary Solenoid", "Solenoid", RobotMap.primarySolenoid);
		LiveWindow.addActuator("Grabber Secondary Solenoid", "Solenoid", RobotMap.secondarySolenoid);

		/*
		 * MOTOR SPEED OUTPUT
		 */
		SmartDashboard.putNumber("LeftFront Speed", RobotMap.mecanumDriveControlsLeftFront.getSpeed());
		SmartDashboard.putNumber("LeftRear Speed", RobotMap.mecanumDriveControlsLeftRear.getSpeed());
		SmartDashboard.putNumber("RightFront Speed", RobotMap.mecanumDriveControlsRightFront.getSpeed());
		SmartDashboard.putNumber("RightRear Speed", RobotMap.mecanumDriveControlsRightRear.getSpeed());

		/*
		 * MOTOR POSITION OUTPUT
		 */
		SmartDashboard.putNumber("LeftFront Position", RobotMap.mecanumDriveControlsLeftFront.getEncPosition());
		SmartDashboard.putNumber("LeftRear Position", RobotMap.mecanumDriveControlsLeftRear.getEncPosition());
		SmartDashboard.putNumber("RightFront Position", RobotMap.mecanumDriveControlsRightFront.getEncPosition());
		SmartDashboard.putNumber("RightRear Position", RobotMap.mecanumDriveControlsRightRear.getEncPosition());

		/*
		 * ELEVATOR SPEED OUTPUT
		 */
		SmartDashboard.putNumber("Elevator Speed", RobotMap.elevatorWinchMotor.getSpeed());

		// SmartDashboard.putNumber("Linear Potentiometer height",
		// RobotMap.potentiometer.get());

		/*
		 * CODE VERSION OUTPUT
		 */
		SmartDashboard.putString("Code Version", VersionFinder.getAttribute(this, VersionFinder.VERSION_ATTRIBUTE));
		SmartDashboard.putString("Code Built By", VersionFinder.getAttribute(this, VersionFinder.BUILT_BY_ATTRIBUTE));
		SmartDashboard.putString("Code Built At", VersionFinder.getAttribute(this, VersionFinder.BUILT_AT_ATTRIBUTE));
	}
}
