package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleSolenoid grabberSolenoid = RobotMap.grabberSolenoid;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * Extend the pneumatics. This will drop totes.
     * 
     * @return state of the pneumatics from magnetic switches.
     */
    public boolean extend() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    	return isGrabberExtended();
    }
    
    /**
     * Retract the pneumatics. This will grab totes.
     * 
     * @return state of the pneumatics from magnetic switches.
     */
    public boolean retract() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    	return isGrabberExtended();
    }
    
    /**
     * Turn off the pneumatics. This will drop totes and make it so you can manually move the arms.
     * 
     * @return state of the pneumatics from magnetic switches.
     */
    public boolean off() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kOff);
    	return isGrabberExtended();
    }
    
    /**public boolean isGrabberExtended() {
    	return ???;
    }
	*/

}

