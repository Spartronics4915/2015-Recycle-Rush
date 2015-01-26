package org.usfirst.frc4915.MecanumDrive.subsystems;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	public final double POSITION_ZERO; // Lowest position
	public final double POSITION_ONE;
	public final double POSITION_TWO;
	public final double POSITION_THREE;
	public final double POSITION_FOUR;
	public final double POSITION_FIVE; // Highest position
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    
    public double getPosition() {
    	// Returns the position of the elevator.
    	// TODO find how to get the position
    	double position;
    	
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

