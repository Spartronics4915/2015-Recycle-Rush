package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Solenoid primarySolenoid = RobotMap.primarySolenoid;
	Solenoid secondarySolenoid = RobotMap.secondarySolenoid;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Extend the large piston. This will move arms to position for picking up
	 * wide totes.
	 */
	public void open() {
		primarySolenoid.set(true);
	}

	/**
	 * Retract the large piston. This will go the position for grabbing narrow
	 * totes.
	 */
	public void close() {
		primarySolenoid.set(false);
	}
	
	/**
	 * extends the piston a small amount to relax the grip on the totes
	 */
	public void smallOpen(){
		secondarySolenoid.set(false);
		primarySolenoid.set(true);
	}

	/**
	 * vents the system
	 */
	public void vent() {
		secondarySolenoid.set(false);
	}

	/**
	 * closes the secondary solenoid to block air outflow
	 */
	public void block() {
		secondarySolenoid.set(true);
	}

}
