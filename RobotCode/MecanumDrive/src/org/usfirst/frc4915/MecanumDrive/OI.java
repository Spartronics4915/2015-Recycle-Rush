package org.usfirst.frc4915.MecanumDrive;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandContainerStrategy;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandJustDrive;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandStacking;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandToteStrategy;
import org.usfirst.frc4915.MecanumDrive.commands.debug.ShowOnly;
import org.usfirst.frc4915.MecanumDrive.commands.debug.DebuggerFilterReset;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.ToggleDriveMode;
import org.usfirst.frc4915.MecanumDrive.commands.drive.Turn90Degrees;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorPositionCalibration;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorSetSafety;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorStop;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.IntermediateOpen;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.utility.VersionFinder;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// CREATING BUTTONS
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
	 * JOYSTICK BUTTONS (DRIVER)
	 */
	public JoystickButton toggleFieldDrive;
	
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
		grabberIntermediate = new JoystickButton(elevatorStick, 5);
		grabberIntermediate.whenPressed(new IntermediateOpen());
		
		/*
		 /*
		 * AUTONOMOUS COMMAND
		 */
		//SmartDashboard.putData("Autonomous Command ", new AutonomousCommand());
		//SmartDashboard.putData("Autonomous Command Drive", new AutonomousCommandJustDrive());
		//SmartDashboard.putData("Autonomous Command Container", new AutonomousCommandContainerStrategy());
		/*
		 * DRIVE STRAIGHT
		 */
		// SmartDashboard.putData("Turn 90 Degrees left", new Turn90Degrees(true));
//
//		SmartDashboard.putData("Move Straight 5 feet", new MoveStraightPositionModeCommand(5,0.7));
//		SmartDashboard.putData("Strafe 1.9 feet", new StrafeCommand(1.9, 0.7));
		//SmartDashboard.putData("Move Backwards 3 feet", new MoveStraightPositionModeCommand(-3, 0.7));
		//SmartDashboard.putData("DriveStraight 1 second", new DriveStraight());
		
		SendableChooser autonomousProgramChooser;

		autonomousProgramChooser = new SendableChooser();
		autonomousProgramChooser.addDefault("Autonomous Just Drive", new AutonomousCommandJustDrive());
		autonomousProgramChooser.addObject("Autonomous Container Strategy", new AutonomousCommandContainerStrategy());
		autonomousProgramChooser.addObject("Autonomous Tote Strategy", new AutonomousCommandToteStrategy());
		autonomousProgramChooser.addObject("Autonomous Stacking Strategy", new AutonomousCommandStacking());
		
		SmartDashboard.putData("Autonomous Program", autonomousProgramChooser);
		
		/*
		 * TOGGLE FIELD ORIENTED DRIVE
		 */
		toggleFieldDrive = new JoystickButton(driveStick, 11);
		toggleFieldDrive.whenPressed(new ToggleDriveMode());
		//SmartDashboard.putData("Toggle Field Drive", new ToggleDriveMode());
		SmartDashboard.putBoolean("Field Mode", Robot.driveTrain.fieldMode);
		
		/*
		 * ELEVATOR
		 */		
		SmartDashboard.putData("ElevatorPositionCalibration", new ElevatorPositionCalibration());
		SmartDashboard.putData("Disable Elevator Safety", new ElevatorSetSafety(false));
		SmartDashboard.putData("Enable Elevator Safety", new ElevatorSetSafety(true));
				
		/*
		 * DEBUGGER BUTTONS
		 */
		 
		
//		SmartDashboard.putData("Set debugger to drivetrain", new ShowOnly(LoggerNames.DRIVETRAIN));
//		SmartDashboard.putData("Set debugger to grabber", new ShowOnly(LoggerNames.GRABBER));
//		SmartDashboard.putData("Set debugger to general", new ShowOnly(LoggerNames.GENERAL));
//		SmartDashboard.putData("Set debugger to autonomous", new ShowOnly(LoggerNames.AUTONOMOUS));
//		SmartDashboard.putData("Set debugger to elevator", new ShowOnly(LoggerNames.ELEVATOR));
//		SmartDashboard.putData("Reset debugger filter", new DebuggerFilterReset());
		

		/*
		 * SENSOR OUTPUT
		 */

		LiveWindow.addSensor("Other Sensors", "Accelerometer", RobotMap.accelerometer);
		LiveWindow.addSensor("Drive Train", "Distance Sensor", DriveTrain.distanceSensor);
		LiveWindow.addActuator("Grabber Primary Solenoid", "Solenoid", RobotMap.primarySolenoid);
		LiveWindow.addActuator("Grabber Secondary Solenoid", "Solenoid", RobotMap.secondarySolenoid);
		
		/*
		 * CODE VERSION OUTPUT
		 */

/*
		SmartDashboard.putString("Code Version", VersionFinder.getAttribute(this, VersionFinder.VERSION_ATTRIBUTE));
		SmartDashboard.putString("Code Built By", VersionFinder.getAttribute(this, VersionFinder.BUILT_BY_ATTRIBUTE));
		SmartDashboard.putString("Code Built At", VersionFinder.getAttribute(this, VersionFinder.BUILT_AT_ATTRIBUTE));
*/
	}
}
