package org.usfirst.frc4915.MecanumDrive;

import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandContainerStrategy;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandJustDrive;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandStacking;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandToteStrategy;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.MecanumDrive.commands.drive.ToggleDriveModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorPositionCalibrationCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorSetSafetyCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.IntermediateOpenGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;

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
    //public JoystickButton toggleFieldDrive;

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
        elevatorJumpToPositionZero.whenPressed(new ElevatorJumpToPositionCommand(0));
        elevatorJumpToPositionOne = new JoystickButton(elevatorStick, 7);
        elevatorJumpToPositionOne.whenPressed(new ElevatorJumpToPositionCommand(1));
        elevatorJumpToPositionTwo = new JoystickButton(elevatorStick, 8);
        elevatorJumpToPositionTwo.whenPressed(new ElevatorJumpToPositionCommand(2));
        elevatorJumpToPositionThree = new JoystickButton(elevatorStick, 9);
        elevatorJumpToPositionThree.whenPressed(new ElevatorJumpToPositionCommand(3));
        elevatorJumpToPositionFour = new JoystickButton(elevatorStick, 10);
        elevatorJumpToPositionFour.whenPressed(new ElevatorJumpToPositionCommand(4));

		
		/*
         * Grabber Buttons
		 */

        grabberOpen = new JoystickButton(elevatorStick, 3);
        grabberOpen.whenPressed(new OpenGrabberCommand());
        grabberClosed = new JoystickButton(elevatorStick, 2);
        grabberClosed.whenPressed(new CloseGrabberCommand());
        grabberIntermediate = new JoystickButton(elevatorStick, 5);
        grabberIntermediate.whenPressed(new IntermediateOpenGrabberCommand());
		
		/*
		 /*
		 * AUTONOMOUS COMMAND
		 */
 
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
        //toggleFieldDrive = new JoystickButton(driveStick, 11);
        //toggleFieldDrive.whenPressed(new ToggleDriveModeCommand());
        //SmartDashboard.putData("Toggle Field Drive", new ToggleDriveModeCommand());
        //SmartDashboard.putBoolean("Field Mode", Robot.driveTrain.fieldMode);
		
		/*
		 * ELEVATOR
		 */
        SmartDashboard.putData("ElevatorPositionCalibrationCommand", new ElevatorPositionCalibrationCommand());
        SmartDashboard.putData("Disable Elevator Safety", new ElevatorSetSafetyCommand(false));
        SmartDashboard.putData("Enable Elevator Safety", new ElevatorSetSafetyCommand(true));
				
		/*
		 * DEBUGGER BUTTONS
		 */

//		SmartDashboard.putData("Set debugger to drivetrain", new DebuggerShowOnlyCommand(LoggerNames.DRIVETRAIN));
//		SmartDashboard.putData("Set debugger to grabber", new DebuggerShowOnlyCommand(LoggerNames.GRABBER));
//		SmartDashboard.putData("Set debugger to general", new DebuggerShowOnlyCommand(LoggerNames.GENERAL));
//		SmartDashboard.putData("Set debugger to autonomous", new DebuggerShowOnlyCommand(LoggerNames.AUTONOMOUS));
//		SmartDashboard.putData("Set debugger to elevator", new DebuggerShowOnlyCommand(LoggerNames.ELEVATOR));
//		SmartDashboard.putData("Reset debugger filter", new DebuggerFilterResetCommand());
		

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
