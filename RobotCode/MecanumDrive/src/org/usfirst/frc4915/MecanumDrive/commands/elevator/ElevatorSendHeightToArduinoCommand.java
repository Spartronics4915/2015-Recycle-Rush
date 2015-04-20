package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import java.math.BigInteger;

public class ElevatorSendHeightToArduinoCommand extends Command {

    //I2C wire = RobotMap.wire;
    Elevator elevator = Robot.elevator;

    public ElevatorSendHeightToArduinoCommand() {
        requires(Robot.elevator);
    }

    @Override
    protected void end() {
    }

    @Override
    protected void execute() {
//        byte[] height = intToByteArray((int) elevator.getPositionInches());
//        wire.transaction(height, height.length, null, 0);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void interrupted() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

//    private byte[] intToByteArray(int num) {
//        BigInteger number = BigInteger.valueOf(num);
//        return number.toByteArray();
//    }

}
