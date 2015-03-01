package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import java.math.BigInteger;

public class ElevatorSendHeightToArduino extends Command {

    I2C wire = RobotMap.wire;
    Elevator elevator = Robot.elevator;

    public ElevatorSendHeightToArduino() {
        requires(Robot.elevator);
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub
        byte[] height = intToByteArray((int) elevator.getPositionInches());
        wire.transaction(height, height.length, null, 0);
    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    private byte[] intToByteArray(int num) {
        BigInteger Number = BigInteger.valueOf(num);
        return Number.toByteArray();
    }

}
