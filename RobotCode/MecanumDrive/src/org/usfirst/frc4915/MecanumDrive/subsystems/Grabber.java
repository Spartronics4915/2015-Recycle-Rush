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

    DoubleSolenoid grabberSolenoid = RobotMap.largeCylinder;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Extend the pneumatics. This will drop totes.
     * 
     * @return state of the pneumatics from magnetic switches.
     */
    public void extend() {
        grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * Retract the pneumatics. This will grab totes.
     * 
     * @return state of the pneumatics from magnetic switches.
     */
    public void retract() {
        grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * Turn off the pneumatics. This will drop totes and make it so you can manually move the arms.
     * 
     * @return state of the pneumatics from magnetic switches.
     */
    public void off() {
        grabberSolenoid.set(DoubleSolenoid.Value.kOff);
    }

}
