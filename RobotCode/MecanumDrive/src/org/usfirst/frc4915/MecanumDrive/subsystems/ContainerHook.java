package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ContainerHook extends Subsystem {
    //TODO We need to make sure that this subsystem always starts retracted

	DoubleSolenoid actuator = RobotMap.containerHookPneumatic;
	
	boolean wasExtended = false;
	
    public void initDefaultCommand() {
    	//No default command
    }
    
    public void extendPneumatic() {
    	actuator.set(Value.kForward);
    }
    
    public void retractPneumatic() {
    	actuator.set(Value.kReverse);
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

