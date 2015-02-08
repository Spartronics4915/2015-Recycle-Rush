package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.ElevatorFineTune;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// These positions describe the number of totes you stacking on top of. 
	// If you need to stack on top of 3 totes, use position 3.
	// If you need to stack on the ground, use position 0.
	
	// TODO find exact height's of positions for number of totes in inches
	public static final int POSITION_ZERO = 0; // Lowest position
	public static final int POSITION_ONE = 1;
	public static final int POSITION_TWO = 2;
	public static final int POSITION_THREE = 3;
	public static final int POSITION_FOUR = 4;
	public static final int POSITION_FIVE = 5; 
	public static final int POSITION_SIX = 6; // Highest position
	
	public static final double FAST_SPEED = .5; // TODO find correct speed
	public static final double SLOW_SPEED = .1; // TODO find correct value for constant speed
	
	public static final double CHASIS_HEIGHT = 5; // These two measurements are in inches
	public static final double HEIGHT_OF_TOTE = 12;
	
	public CANTalon winch = RobotMap.elevatorWinchMotor14;
	
	/**
	 * Initializes the default command (WPI java default method)
	 * Called on initialization of the subsystem
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorFineTune());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // TODO Make sure that the winch does not begin winding the wrong way -- We may use a limit switch to tell if the cable is tight or not.
    // Discuss this with Elevator Subteam and Riyadth
    /**
     * Moves the elevator at a speed given by the joystick (y axis).
     * @param joystick Forward on joystick is up, backward is down
     */
    public void moveWithJoystick(Joystick joystick) {
        double joystickY = joystick.getAxis(Joystick.AxisType.kY);
        System.out.println("Elevator joystick " + joystickY);
        if (Math.abs(joystickY) <= .2) {
            System.out.println("Stopping Motor");	
        	holdPosition();
        }
        else {
        	moveElevator(joystickY);
        }
    }
    
    /**
     * Moves the elevator using the speed control mode.
     * 
     * @param speed that the elevator moves + goes up, - goes down
     */
    public void moveElevator(double speed) {
    	// TODO make sure we can calibrate our potentiometer so that these two points are 0 and 54 inches.
    	// The elevator's minimum height is 0 inches
    	if (getPosition() <= 0) {
    		if (speed < 0) {
    			speed = 0;
    		}
    	}
    	// The elevator's maximum height is 54 inches TODO Confirm
    	if (getPosition() >= 54) {
    		if (speed > 0) {
    			speed = 0;
    		}
    	}
    	changeControlModeWinch(ControlMode.Speed);
    	winch.set(speed);
    }
    
    /**
     * Changes the control mode so that you can use it in both speed and position modes.
     * 
     * @param mode the ControlMode for the winch - either ControlMode.Speed or ControlMode.Position
     */
    public void changeControlModeWinch(ControlMode mode) {
    	winch.changeControlMode(mode);
    }
    
    /**
     * Stops the elevator from moving. Used at the end of commands.
     */
    public void stopElevator() {
    	winch.disableControl();
    	System.out.println("Elevator has stopped.");
    }
    
    /**
     * TODO Make this actually work - It will drift and continually use a new point to hold the position
     * Holds the elevator in it's current position
     */
    public void holdPosition() {
    	changeControlModeWinch(ControlMode.Position);
    	winch.set(getPosition());
    }
 	
    /**
     * @return the position of the elevator in inches (between 0 and 54)
     */
    public double getPosition() {
    	Robot.debugger.logError(LoggerNames.ELEVATOR, "The elevator is at position" + getPosition());
    	// TODO figure out scaling
    	return winch.getPosition();
    }
    
    /** 
     * Moves based on a position value
     * @param position The position (between 0 and 54 inches that you want your elevator
     */
    public void setPosition(double position) {
    	if (position <= 0) {
    		position = 0;
    	}
    	// The elevator's maximum height is 54 inches TODO Confirm
    	if (position >= 54) {
    		position = 54;
    	}
    	changeControlModeWinch(ControlMode.Position);
    	winch.set(position);
    }

    /**
     * 
     * @param positionNumber
     */
	public double convertPositionToHeight(int positionNumber) {
		switch (positionNumber) {
		case 0:
			positionNumber = POSITION_ZERO;
			break;
		case 1:
			positionNumber = POSITION_ONE;
			break;
		case 2:
			positionNumber = POSITION_TWO;
			break;
		case 3:
			positionNumber = POSITION_THREE;
			break;
		case 4:
			positionNumber = POSITION_FOUR;
			break;
		case 5:
			positionNumber = POSITION_FIVE;
			break;
		case 6:
			positionNumber = POSITION_SIX;
			break;
		}
		return positionNumber;
	}
}