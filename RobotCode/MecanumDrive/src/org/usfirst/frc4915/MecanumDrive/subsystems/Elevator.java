package org.usfirst.frc4915.MecanumDrive.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorFineTuneCommand;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

public class Elevator extends Subsystem {

    // These positions describe the number of totes you stacking on top of.
    // If you need to stack on top of 3 totes, use position 3.
    // If you need to stack on the ground, use position 0.

    public static final double APPROXIMATE_OFFSET = 430;

    // POTENTIOMTERS : fwd --> top, rev --> bottom
    public static final double RANGE_OF_MOTION = 53; //inches
    public static final double POSITION_OFFSET = 2.5; //inches
    public static final double HEIGHT_OF_TOTE = 12;
    private static final double JOYSTICK_SCALE = -18;
    // Not in inches. Between minimumPotentiometerValue and
    // maximumPotentiometerValue.
    public static double setPoint;
    // Set by ElevatorMax/MinHeightCalibrate commands
    public static double minimumPotentiometerValue = 0;
    public static double maximumPotentiometerValue = 1023;
    public static double slackMinimum = 0;
    public static boolean needToApproximate = true;
    // distance between 53
    // inches
    public static boolean didSaveTopValue = false;
    public static boolean didSaveBottomValue = false;
    public static boolean SAFETY = true;
    private static double previousJoystickY = 0;

    public CANTalon winch = RobotMap.elevatorWinchMotor;
    public DigitalInput bottomLimitSwitch = RobotMap.bottomLimitSwitch;

    /**
     * Initializes the default command (WPI java default method) Called on
     * initialization of the subsystem
     */
    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorFineTuneCommand());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Changes height based on the input
     *
     * @param heightChange + goes up, - goes down
     */
    public void moveElevator(double heightChange) {
        setPoint += heightChange;
        moveToHeight();
    }

    /**
     * Moves the elevator to the height variable Ensures that height is in range
     * first
     */
    public void moveToHeight() {
        keepHeightInRange();
        winch.set(setPoint);
    }

	/**
	 * Moves the elevator at a speed given by the joystick (y axis).
	 * 
	 * @param joystick Forward on joystick is up, backward is down
	 */
	public void moveWithJoystick(Joystick joystick) {
		double joystickY = joystick.getAxis(Joystick.AxisType.kY);
		//Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator joystick " + joystickY);
		if (Math.abs(joystickY) <= .1) {
			//Robot.debugger.logError(LoggerNames.ELEVATOR, "Joystick value too small");
			joystickY = 0;
			moveElevator(0);
		} if (shouldStopElevator(joystickY)){
			setPoint = getPosition();
			moveElevator(0);
		} else {
			moveElevator(joystickY * JOYSTICK_SCALE);
		}
		previousJoystickY = joystickY;
	}
	
	/**
	 * If you push or pull the joystick from positive to negative/0, it will stop the elevator.
	 * Probably won't work because it is hard to move the joystick in the span of 20 ms.
	 * @param joystickY current joystickY value.
	 * @return true if the change in joystick value is more than 1 (so from + to -, or from - to +)
	 */
	private boolean shouldStopElevator(double joystickY) {
		return ((joystickY >= 0) && (previousJoystickY < 0) || ((joystickY <= 0) && (previousJoystickY > 0)));
	}

	/**
     * Sets the height to where it currently is so that the elevator should not go up or down.
     */
    public void setHieghtToCurrentPosition() {
        setPoint = getPosition();
    }

	/**
	 * Stops the winch from winding. This may still have the elevator fall under
	 * it's own weight.
	 */
	public void stopElevator() {
		winch.disableControl();
		//Robot.debugger.logError(LoggerNames.ELEVATOR, "Winch has stopped");
	}

	/**
	 * @return the position of the elevator in inches (between 0 and 54)
	 */
	public double getPositionInches() {
		double position = (getPosition() - minimumPotentiometerValue) 
						* (RANGE_OF_MOTION / (maximumPotentiometerValue - minimumPotentiometerValue));
		//Robot.debugger.logError(LoggerNames.ELEVATOR, "The elevator is at position " + position + " (inches)");
		return position;
	}
	
	/**
	 * @return the position number of the elevator -- how many totes 
	 * could be below it. Between 0 and 5.
	 */
	public double getPositionNumber() {
		double positionNumber = (getPositionInches() - POSITION_OFFSET) / HEIGHT_OF_TOTE;
		return positionNumber;
	}

	/**
	 * @return the read value from the potentiometer (between 0 and 1023)
	 */
	public double getPosition() {
		//Robot.debugger.logError(LoggerNames.ELEVATOR, "The potentiometer reads " + winch.getPosition());
		return winch.getPosition();
	}

    /**
     * Converts from a position between zero totes to six totes to inches.
     * If you need to stack on top of 3 totes, use position 3.
     * If you need to stack on the ground, use position 0.
     *
     * @param positionNumber the number of totes you are stacking on top of.
     */
    public void setHeightToPosition(double positionNumber) {

        // find the range between the min and max Potentiometer values, divide by 54 to get
        // the change in value per inch and multiply by the number of inches that the totes are stacked
        setPoint = minimumPotentiometerValue + ((maximumPotentiometerValue - minimumPotentiometerValue)
                * HEIGHT_OF_TOTE * (positionNumber + POSITION_OFFSET / HEIGHT_OF_TOTE) / RANGE_OF_MOTION);
		//Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator's height is " + setPoint);
	}

	/**
	 * 
	 * @return if the elevator is at it's max height, return
	 */
	public boolean isAtTopOfElevator() {
		if (winch.isFwdLimitSwitchClosed()) {
			//Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator Top LimitSwitch has been reached");
		}
		return winch.isFwdLimitSwitchClosed();
	}

	/**
	 * @return if the elevator is at it's min height, return true
	 */
	public boolean isAtBottomOfElevator() {
		if (bottomLimitSwitch.get()) {
			//Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator Bottom LimitSwitch has been reached");
		}
		return bottomLimitSwitch.get();
	}

	/**
	 * Makes sure that the height value doesn't increase or decrease beyond the
	 * min/maximum values Also, if the limit switch is pressed (fwd --> top ~700?, rev
	 * --> bottom ~0) it will update maximum/minimum potentiometer values.
	 */
	public void keepHeightInRange() {
		if (isAtTopOfElevator()) {
			winch.enableBrakeMode(true);
			maximumPotentiometerValue = getPosition();
			if (needToApproximate) {
				minimumPotentiometerValue = getPosition() - APPROXIMATE_OFFSET;
				needToApproximate = false;
			}
			if (didSaveTopValue == false) {
				Robot.preferences.putDouble("maximumPotentiometerValue", maximumPotentiometerValue);
				Robot.preferences.putDouble("minimumPotentiometerValue", minimumPotentiometerValue);
				Robot.preferences.save();
				didSaveTopValue = true;
			}
		}
		else if (isAtBottomOfElevator() && SAFETY) {
			winch.enableBrakeMode(true);
			minimumPotentiometerValue = getPosition();
			if (needToApproximate) {
				maximumPotentiometerValue = getPosition() + APPROXIMATE_OFFSET;
				needToApproximate = false;
			}
			if (didSaveBottomValue == false) {
				Robot.preferences.putDouble("minimumPotentiometerValue", minimumPotentiometerValue);
				Robot.preferences.putDouble("maximumPotentiometerValue", maximumPotentiometerValue);
				Robot.preferences.save();
				didSaveBottomValue = true;
			}
		}
		else {
			didSaveTopValue = false;
			didSaveBottomValue = false;
		}
		if (SAFETY) {
			keepWinchTight();
		}
		if ((setPoint > maximumPotentiometerValue)) {
			setPoint = maximumPotentiometerValue - 1;
		}
		if ((setPoint < minimumPotentiometerValue)) {
			setPoint = minimumPotentiometerValue; // I'd like to use the +1 to re-fix the winch cable, 
			// but I'm worried that the cable would automatically get tangled
		}
		//Robot.debugger.logError(LoggerNames.ELEVATOR, "Elevator's height is " + setPoint);
	}

    /**
     * If the slack limit switch is true and it hasn't been triggered before,
     * it will keep the setpoint from unwinding further.
     */
    public void keepWinchTight() {
        if (elevatorIsSlack() && slackMinimum == 0) {
            slackMinimum = getPosition();
        }
        if (setPoint < slackMinimum) {
            setPoint = slackMinimum;
        } else if (!elevatorIsSlack()) {
            slackMinimum = 0;
        }
    }

    /**
     * Is the elevator cable slack and about to unwind?
     *
     * @return true if the elevator is slack, false if not.
     */
    public boolean elevatorIsSlack() {
        return winch.isRevLimitSwitchClosed();
    }

}