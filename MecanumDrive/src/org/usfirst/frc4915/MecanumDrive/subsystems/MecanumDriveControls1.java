// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class MecanumDriveControls1 extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController leftFront1 = RobotMap.mecanumDriveControls1LeftFront1;
    SpeedController leftRear2 = RobotMap.mecanumDriveControls1LeftRear2;
    SpeedController rightFront3 = RobotMap.mecanumDriveControls1RightFront3;
    SpeedController rightRear4 = RobotMap.mecanumDriveControls1RightRear4;
    RobotDrive robotDrive41 = RobotMap.mecanumDriveControls1RobotDrive41;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new MecanumCommand1());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public RobotDrive getRobotDrive() {
    	return robotDrive41;
    }
    
    public void mecanumDrive() {
    	Joystick joystickDrive = Robot.oi.getDriveStick1();
        double joystickX = joystickDrive.getAxis(Joystick.AxisType.kX);
        double joystickY = joystickDrive.getAxis(Joystick.AxisType.kY);
        double joystickTwist = joystickDrive.getAxis(Joystick.AxisType.kTwist);
        if ((Math.abs(joystickX) < 0.2) && (Math.abs(joystickY) < 0.2)) {
            if (Math.abs(joystickTwist) < 0.2)
            	robotDrive41.stopMotor();
        } else {
        	robotDrive41.mecanumDrive_Cartesian(joystickX, joystickY, joystickTwist, 0.0);
        }
    }
}

