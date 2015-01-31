package org.usfirst.frc4915.MecanumDrive.commands;


import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorJumpToPosition5 extends ElevatorJumpToPosition {
	
	public ElevatorJumpToPosition5() {
    	super(Elevator.POSITION_FIVE, 5);
    }
}
