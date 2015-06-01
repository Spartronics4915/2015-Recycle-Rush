package org.usfirst.frc4915.MecanumDrive;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc4915.MecanumDrive.subsystems.ContainerHook;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.MecanumDrive.subsystems.Grabber;
import org.usfirst.frc4915.MecanumDrive.utility.VersionFinder;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

//import org.usfirst.frc4915.MecanumDriveCommand.commands.debug.DebuggerFilter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static Preferences preferences;
	double testPreferencesItemOne;
	double testPreferencesItemTwo;
	double testPreferencesItemThree;

	SendableChooser autonomousProgramChooser;
	SendableChooser Debugger;

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static ContainerHook hook;
	public static CustomDebugger debugger = new CustomDebugger();
	
	// vars for camera code
	private Image frame;
	private int session0;
	private int session1;
	private boolean cam1available = false;
	private boolean cam0available = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		debugger = new CustomDebugger();
		preferences = Preferences.getInstance();
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		grabber = new Grabber();
		hook = new ContainerHook();
		
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		
		if (RobotMap.gyro != null){
			RobotMap.gyro.initGyro();
			RobotMap.gyro.setSensitivity(0.001655);
			RobotMap.gyro.reset();
			System.out.println("Initialized Gyro: " + RobotMap.gyro.getAngle());
		}

		testPreferencesItemOne = preferences.getDouble("TestOne", 123.4);
		testPreferencesItemTwo = preferences.getDouble("TestTwo", 456.7);
		preferences.putDouble("TestThree", 987.65);
		testPreferencesItemThree = preferences.getDouble("TestThree", 1.11);
	    preferences.getString("DesiredDistance", "9.0");
	    debugger.logError(LoggerNames.GENERAL, "TestOne = "	+ testPreferencesItemOne);
	    debugger.logError(LoggerNames.GENERAL, "TestThree = " + testPreferencesItemThree);
	    debugger.logError(LoggerNames.GENERAL, preferences.getString("DesiredDistance", "9.0"));
	    
		//SmartDashboard.putData(Scheduler.getInstance());
		
	//	Debugger = new SendableChooser();
	//	Debugger.addDefault("General", new ShowOnly(LoggerNames.GENERAL));
	//	Debugger.addObject("Grabber", new ShowOnly(LoggerNames.GRABBER));
	//	Debugger.addObject("Drivetrain", new ShowOnly(LoggerNames.DRIVETRAIN));
	//	Debugger.addObject("Autonomous", new ShowOnly(LoggerNames.AUTONOMOUS));
	//	Debugger.addObject("Elevator", new ShowOnly(LoggerNames.ELEVATOR));
		
	//	SmartDashboard.putData("Debugger Filter", Debugger);
	/*	
		Debugger = new SendableChooser();
		Debugger.addDefault("General", new DebuggerFilter(LoggerNames.GENERAL));
		Debugger.addObject("Grabber", new DebuggerFilter(LoggerNames.GRABBER));
		Debugger.addObject("Drivetrain", new DebuggerFilter(LoggerNames.DRIVETRAIN));
		Debugger.addObject("Autonomous", new DebuggerFilter(LoggerNames.AUTONOMOUS));
		Debugger.addObject("Elevator", new DebuggerFilter(LoggerNames.ELEVATOR));
		
		*/

		//SmartDashboard.putData("Debugger Filter ", Debugger);
		displayVersioningOnSmartDashboard();

		if (elevator != null) {
			elevator.setHieghtToCurrentPosition();
			Elevator.minimumPotentiometerValue = preferences.getDouble("minimumPotentiometerValue", 0);
			Elevator.maximumPotentiometerValue = preferences.getDouble("maximumPotentiometerValue", 1023);
			debugger.logError(LoggerNames.ELEVATOR, "MaximumPotentiometerValue = " + Elevator.maximumPotentiometerValue);
			debugger.logError(LoggerNames.ELEVATOR, "MinimumPotentiometerValue = " + Elevator.minimumPotentiometerValue);
		}
	}

	private void displayVersioningOnSmartDashboard() {
		String parsedVersion = VersionFinder.getAttribute(this, VersionFinder.VERSION_ATTRIBUTE);
		DriverStation.reportError("Code Version " + parsedVersion + "\n", false);

		String parsedBuilder = VersionFinder.getAttribute(this, VersionFinder.BUILT_BY_ATTRIBUTE);
		DriverStation.reportError("Code Built By " + parsedBuilder + "\n", false);

		String parsedBuildDate = VersionFinder.getAttribute(this, VersionFinder.BUILT_AT_ATTRIBUTE);
		DriverStation.reportError("Code Built At " + parsedBuildDate + "\n", false);		
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		/*
		if (cam1available)NIVision.IMAQdxStopAcquisition(session1);	
		if (cam0available)NIVision.IMAQdxStopAcquisition(session0);	
		*/
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// Use the selected autonomous command

		autonomousCommand = (Command) oi.autonomousProgramChooser.getSelected();
		//double desiredDistrance = preferences.getDouble("DesiredDistance", 9.0);
		//autonomousCommand = new AutonomousCommandToteStrategy();
		//autonomousCommand = new StrafeCommand(3, 0.7);
		//double desiredDistance = preferences.getDouble("DesiredDistance", 9.0);
		//autonomousCommand = new AutonomousCommandToteStrategy();

		// Sets the setPoint to where-ever it is to prevent the elevator
		// wanting to go to a random position (default zero)
		elevator.setHieghtToCurrentPosition();
		// Tells the elevator to approximate the other maximum when it hits a limit switch
		Elevator.SAFETY = true;
		Elevator.needToApproximate = true;
		Elevator.didSaveTopValue = false;
		Elevator.didSaveBottomValue = false;
		driveTrain.fieldMode = false;
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		// Sets the setPoint to where-ever it is to prevent the elevator
		// wanting to go to a random position (default zero)
		elevator.setHieghtToCurrentPosition();
		// Tells the elevator to approximate the other maximum when it hits a limit switch
		Elevator.SAFETY = true;
		Elevator.needToApproximate = true;
		Elevator.didSaveTopValue = false;
		Elevator.didSaveBottomValue = false;
		driveTrain.fieldMode = false;
		/*
		// Init camera
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		// the camera name (ex "cam0") can be found through the roborio web interface
		try {
			session1 = NIVision
					.IMAQdxOpenCamera(
							"cam1",
							NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(session1);
			cam1available = true; 
		} catch (VisionException ve) {
			cam1available = false;
			debugger.logError(LoggerNames.GENERAL,"Camera 1 has failed to initialized");
		}
		try {
			session0 = NIVision
					.IMAQdxOpenCamera(
							"cam0",
							NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(session0);
			cam0available = true; 
		} catch (VisionException ve) {
			cam0available = false;
			debugger.logError(LoggerNames.GENERAL,"Camera 0 has failed to initialized");
		}
		if (session1 > 0 && cam1available){
			NIVision.IMAQdxStartAcquisition(session1);
		}
		if (session0 > 0 && cam0available){
			NIVision.IMAQdxStartAcquisition(session0);
		}	
		*/
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Elevator debug information
		SmartDashboard.putNumber("Elevator SetPoint", Elevator.setPoint); // We may not need this one
		SmartDashboard.putBoolean("Elevator At Top", elevator.isAtTopOfElevator());
		SmartDashboard.putBoolean("Elevator At Bottom", elevator.isAtBottomOfElevator());
		SmartDashboard.putBoolean("Elevator is Slack", elevator.elevatorIsSlack());
		SmartDashboard.putNumber("Elevator Potentiometer Value", elevator.getPosition());
		SmartDashboard.putNumber("Maximum height value: ", Elevator.maximumPotentiometerValue);
		SmartDashboard.putNumber("Minimum height value: ", Elevator.minimumPotentiometerValue);
		SmartDashboard.putNumber("Position Number of Elevator: ", Robot.elevator.getPositionNumber());
		SmartDashboard.putBoolean("Safety Enabled", Elevator.SAFETY);
		/*
		if (cam1available)
		{
			cameragrab(session1);
		} else if (cam0available) {
			cameragrab(session0);
		}
		 */		
        /** robot code here! **/
        //Timer.delay(0.005);		// wait for a motor update time

        // NIVision.IMAQdxStopAcquisition(session);
        // NIVision.IMAQdxStopAcquisition(session);

    }

    public void testInit() {
        // Init camera
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        try {
            session1 = NIVision
                    .IMAQdxOpenCamera(
                            "cam1",
                            NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(session1);
            cam1available = true;
        } catch (VisionException ve) {
            cam1available = false;
            debugger.logError(LoggerNames.GENERAL, "Camera 1 has failed to initialized");
        }
        try {
            session0 = NIVision
                    .IMAQdxOpenCamera(
                            "cam0",
                            NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(session0);
            cam0available = true;
        } catch (VisionException ve) {
            cam0available = false;
            debugger.logError(LoggerNames.GENERAL, "Camera 0 has failed to initialized");
        }
        if (session1 > 0 && cam1available) {
            NIVision.IMAQdxStartAcquisition(session1);
        }
        if (session0 > 0 && cam0available) {
            NIVision.IMAQdxStartAcquisition(session0);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        if (cam1available) {
            cameragrab(session1);
        } else if (cam0available) {
            cameragrab(session0);
        }

    }

    public void cameragrab(int sessionid) {
        System.out.println("camera session: " + sessionid);
        NIVision.IMAQdxGrab(sessionid, frame, 1);
        CameraServer.getInstance().setImage(frame);

    }
}
