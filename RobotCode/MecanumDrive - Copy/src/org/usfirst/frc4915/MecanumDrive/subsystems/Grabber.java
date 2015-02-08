package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

	DoubleSolenoid mommaSolenoid = RobotMap.mommaSolenoid;
    Solenoid babySolenoid = RobotMap.babySolenoid;



    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Extend the large piston. This will move arms to position for picking up wide totes.
     */
    public void open() {
    	mommaSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Retract the large piston. This will go the position for grabbing narrow totes.
     */
    public void close() {
    	mommaSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Extend the small piston. This will tighten the grip on the totes so they can be picked up. // TODO this may be changed later
     */
    public void vent() {
    	
    	babySolenoid.set(true);
    }
    
    /**
     * Turn off the pneumatics for the small piston. This will relax the grip on totes.
     */
    public void block() {
    	babySolenoid.set(false);
    }
    
}


