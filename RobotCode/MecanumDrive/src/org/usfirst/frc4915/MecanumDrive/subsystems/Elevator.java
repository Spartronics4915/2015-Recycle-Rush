package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.ElevatorFineTune;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import java.text.DecimalFormat;

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
	public final double POSITION_ZERO = 0; // Lowest position
	public final double POSITION_ONE = 1;
	public final double POSITION_TWO = 2;
	public final double POSITION_THREE = 3;
	public final double POSITION_FOUR = 4;
	public final double POSITION_FIVE = 5; 
	public final double POSITION_SIX = 6; // Highest position
	
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorFineTune());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public boolean isInPosition(int i) {
		// TODO Auto-generated method stub
		return false;
	}
    
    public void moveToPosition(double position) {
    	
    	if (roundPosition(position) == roundPosition(getPosition())) {
    		System.out.println("You are already in this position.");
    	}
    	else {
    		/* TODO
    		 * double distanceToTravel = roundPosition(getPosition()) - roundPosition(position);
    		 * math to convert distanceToTravel to rotations or time to travel
    		 */
    		System.out.println("Moving elevator.");
    	}
    }
    
    public void moveAtSpeed(Joystick Axis) {
    	// moves elevator at a constant speed
    	// TODO
		System.out.println("Joystick is moving at a constant speed");
    }
    
    public void stopElevator() {
    	// stops any current commands telling the elevator to move.
    	// TODO find out how to stop the elevator 
    	System.out.println("Elevator has stopped.");
    }
    
    // TODO replace TEMP in regards to functionality for getPosition();
 	public final double TEMP = 0;
 	
    public double getPosition() {
    	// Returns the position of the elevator.
    	// TODO find how to get the position
    	double position = TEMP;
    	
    	System.out.println("We got the current position of the elevator.");
    	return position;
    }
    
    private double roundPosition(double position) {
    	System.out.printf("position has been rounded from %f to %f",
    			position, Double.parseDouble(new DecimalFormat("#.#").format(position)));
    	
    	return Double.parseDouble(new DecimalFormat("#.#").format(position));
    }
    
    public boolean isInPosition(double position) {
    	if (roundPosition(position) == roundPosition(getPosition())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}

