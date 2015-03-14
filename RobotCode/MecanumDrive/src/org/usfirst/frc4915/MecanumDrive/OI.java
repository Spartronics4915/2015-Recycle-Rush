package org.usfirst.frc4915.MecanumDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandContainerStrategy;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandContainerWithPlatform;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandDoNothing;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandJustDrive;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandStacking;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandToteStrategy;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandToteWithPlatform;
import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandTurn180WithTote;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPositionCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorPositionCalibrationCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorSetSafetyCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.IntermediateOpenGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabberCommand;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.utility.VersionFinder;

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

    public SendableChooser autonomousProgramChooser;

    public OI() {

        /*
         * AUTONOMOUS CHOOSER
         */

        autonomousProgramChooser = new SendableChooser();
        autonomousProgramChooser.addDefault("Autonomous Just Drive", new AutonomousCommandJustDrive());
        autonomousProgramChooser.addObject("Autonomous Container Strategy", new AutonomousCommandContainerStrategy());
        autonomousProgramChooser.addObject("Autonomous Tote Strategy", new AutonomousCommandToteStrategy());
        autonomousProgramChooser.addObject("Autonomous Container WITH Platform", new AutonomousCommandContainerWithPlatform());
        autonomousProgramChooser.addObject("Autonomous Tote WITH Platform", new AutonomousCommandToteWithPlatform());
        autonomousProgramChooser.addObject("Autonomous Stacking Strategy", new AutonomousCommandStacking());
        autonomousProgramChooser.addObject("Autonomous Do Nothing", new AutonomousCommandDoNothing());
        autonomousProgramChooser.addObject("Autonomous Tote 180º", new AutonomousCommandTurn180WithTote());

        SmartDashboard.putData("Autonomous Program", autonomousProgramChooser);

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
         * JOYSTICK BUTTONS (GRABBER)
         */

        grabberOpen = new JoystickButton(elevatorStick, 3);
        grabberOpen.whenPressed(new OpenGrabberCommand());
        grabberClosed = new JoystickButton(elevatorStick, 2);
        grabberClosed.whenPressed(new CloseGrabberCommand());
        grabberIntermediate = new JoystickButton(elevatorStick, 5);
        grabberIntermediate.whenPressed(new IntermediateOpenGrabberCommand());

        /*
         * ELEVATOR
         */

        SmartDashboard.putData("ElevatorPositionCalibrationCommand", new ElevatorPositionCalibrationCommand());
        SmartDashboard.putData("Disable Elevator Safety", new ElevatorSetSafetyCommand(false));
        SmartDashboard.putData("Enable Elevator Safety", new ElevatorSetSafetyCommand(true));

        /*
         * SENSOR OUTPUT
         */

        LiveWindow.addSensor("Other Sensors", "Accelerometer", RobotMap.accelerometer);
        LiveWindow.addSensor("Drive Train", "Distance Sensor", DriveTrain.distanceSensor);
        LiveWindow.addActuator("Grabber Primary Solenoid", "Solenoid", RobotMap.primarySolenoid);
        LiveWindow.addActuator("Grabber Secondary Solenoid", "Solenoid", RobotMap.secondarySolenoid);
    }
}
