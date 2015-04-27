package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ContainerHook extends Subsystem {
    //TODO We need to make sure that this subsystem always starts retracted

	Solenoid actuator = RobotMap.containerHookPneumatic;
	
	boolean wasExtended = false;
	
    public void initDefaultCommand() {
    	//No default command
    }
    
    public void extendPneumatic() {
    	actuator.set(true);
    }
    
    public void retractPneumatic() {
    	actuator.set(false);
    }
    
    public void togglePneumaticState() {
    	if (wasExtended) {
    		retractPneumatic();
    		wasExtended = false;
    	}
    	else {
    		extendPneumatic();
    		wasExtended = true;
    	}
    }
}

