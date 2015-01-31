package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.ElevatorFineTune;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    
	AnalogPotentiometer potentiometer = RobotMap.potentiometer;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// These positions describe the number of totes you stacking on top of. 
	// If you need to stack on top of 3 totes, use position 3.
	// If you need to stack on the ground, use position 0.
	
	// TODO find exact height's of positions for number of totes in inches
	public static final double POSITION_ZERO = 0; // Lowest position
	public static final double POSITION_ONE = 1;
	public static final double POSITION_TWO = 2;
	public static final double POSITION_THREE = 3;
	public static final double POSITION_FOUR = 4;
	public static final double POSITION_FIVE = 5; 
	public static final double POSITION_SIX = 6; // Highest position
	
	public static final double MOTOR_SPEED = .5; // TODO find correct speed
	public static final double CONSTANT_SPEED = .1; // TODO find correct value for constant speed
	
	public CANTalon winch = RobotMap.elevatorWinchMotor14;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorFineTune());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public boolean isInPosition(double position) {
		// tells if the elevator is in a specific preset position
		
		if (Math.abs(position - getPosition()) <= 1) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public void moveToPosition(double position) {
    	/*
    	 * moves the elevator to a preset position based on the number of totes
    	 * driver wishes to stack on.
    	 */
    	if (isInPosition(position)) {
    		holdPosition();
    		System.out.println("You are already in this position.");
    	}
    	else {
    		winch.changeControlMode(ControlMode.Speed);
    		double speed = MOTOR_SPEED;
    		if (getPosition() > position) { // Let positive be up and negative be down
    			speed = speed * -1;
    		}
    		winch.set(speed);
    	}
    	System.out.println("Moving elevator.");
    	
    }
    
    public void moveAtSpeed(Joystick joystick) {
    	/*
    	 * Moves elevator at a constant speed
    	 * speed is currently set, user cannot change speed to be moved at
    	 */
        double joystickY = joystick.getAxis(Joystick.AxisType.kY);
        System.out.println("Elevator joystick " + joystickY);
        if (Math.abs(joystickY) <= .2) {
            System.out.println("Stopping Motor");	
        	holdPosition();
        }
        else {
        	winch.changeControlMode(ControlMode.Speed);
        	System.out.println("Moving Elevator at constant speed");
        	if (joystickY > 0) {
        		winch.set(CONSTANT_SPEED);
        	}
        	else {
        		winch.set(CONSTANT_SPEED * -1);
        	}
        }
    }
    
    public void stopElevator() {
    	// stops any current commands telling the elevator to move.
    	
    	winch.disableControl();
    	System.out.println("Elevator has stopped.");
    }
    
    public void holdPosition() {
    	/*
    	 * Keeps the elevator in a constant position
    	 */
    	winch.changeControlMode(ControlMode.Position);
    	winch.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
    	winch.set(winch.getPosition());
    }
 	
    public double getPosition() {
    	// Returns the position of the elevator
    	
    	double position = potentiometer.get();
    	System.out.println("We got the current position of the elevator.");
    	return position;
    }
}

