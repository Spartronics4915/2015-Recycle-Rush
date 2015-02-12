package org.usfirst.frc4915.MecanumDrive;

import org.usfirst.frc4915.MecanumDrive.commands.autonomous.AutonomousCommandToteStrategy;
import org.usfirst.frc4915.MecanumDrive.commands.debug.GenericTestCommand;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.MecanumDrive.subsystems.Grabber;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	Preferences preferences;
	double testPreferencesItemOne;
	double testPreferencesItemTwo;

	SendableChooser autonomousProgramChooser;

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static CustomDebugger debugger = new CustomDebugger();
	
    //vars for camera code
    Image frame;
    int session;



	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		preferences = Preferences.getInstance();
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		grabber = new Grabber();
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		if (RobotMap.gyro != null){
			RobotMap.gyro.initGyro();
		}

		testPreferencesItemOne = preferences.getDouble("TestOne", 123.4);
		testPreferencesItemOne = preferences.getDouble("TestTwo", 456.7);
	    preferences.getString("DesiredDistance", "9.0");

		autonomousProgramChooser = new SendableChooser();
		autonomousProgramChooser.addDefault("Autonomous Program One", new GenericTestCommand(10, "Running program one!"));
		autonomousProgramChooser.addObject("Autonomous Program Two", new GenericTestCommand(20, "Running program two!"));

		SmartDashboard.putData("Autonomous Program", autonomousProgramChooser);

		// Test for sending messages to smart dashboard
		SendUserMessage.displayMessage();
		
        //Init camera
       // frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        //session = NIVision.IMAQdxOpenCamera("cam1",
       //         NIVision.IMAQdxCameraControlMode.CameraControlModeController);
      //  NIVision.IMAQdxConfigureGrab(session);
		
        // frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
		
        // session = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        // NIVision.IMAQdxConfigureGrab(session);

	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// Use the selected autonomous command
		// autonomousCommand = (Command) autonomousProgramChooser.getSelected();
		//double desiredDistrance = preferences.getDouble("DesiredDistance", 9.0);
		autonomousCommand = new AutonomousCommandToteStrategy();

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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// long now = Instant.now().toEpochMilli();
		// System.out.println("LeftFront Position: encVelocity | setPoint," +
		// now +","+ RobotMap.mecanumDriveControls1LeftFront10.getEncVelocity()
		// +"," + RobotMap.mecanumDriveControls1LeftFront10.getSetpoint() );
		// System.out.println("LeftRear Position: encVelocity | setPoint," + now
		// +","+ RobotMap.mecanumDriveControls1LeftRear11.getEncVelocity() +","
		// + RobotMap.mecanumDriveControls1LeftRear11.getSetpoint() );
		// System.out.println("RightFront Position: encVelocity | setPoint," +
		// now +","+ RobotMap.mecanumDriveControls1RightFront12.getEncVelocity()
		// +"," + RobotMap.mecanumDriveControls1RightFront12.getSetpoint() );
		// System.out.println("RightRear Position: encVelocity | setPoint," +
		// now +","+ RobotMap.mecanumDriveControls1RightRear13.getEncVelocity()
		// +"," + RobotMap.mecanumDriveControls1RightRear13.getSetpoint() );

		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Elevator Height", Elevator.height);
		SmartDashboard.putBoolean("Elevator At Top", elevator.isAtTopOfElevator());
		SmartDashboard.putBoolean("Elevator At Bottom", elevator.isAtBottomOfElevator());
		SmartDashboard.putNumber("Elevator Potentiometer Value", elevator.getPosition());
		SmartDashboard.putNumber("Elevator P", elevator.winch.getP());
		SmartDashboard.putNumber("Elevator I", elevator.winch.getI());
		SmartDashboard.putNumber("Elevator D", elevator.winch.getD());
		
    	/**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */

		//NIVision.IMAQdxStartAcquisition(session);

         //   NIVision.IMAQdxGrab(session, frame, 1);
          //  CameraServer.getInstance().setImage(frame);
		// NIVision.IMAQdxStartAcquisition(session);

        // NIVision.IMAQdxGrab(session, frame, 1);
        // CameraServer.getInstance().setImage(frame);

        /** robot code here! **/
        Timer.delay(0.005);		// wait for a motor update time

       // NIVision.IMAQdxStopAcquisition(session);
        // NIVision.IMAQdxStopAcquisition(session);

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
