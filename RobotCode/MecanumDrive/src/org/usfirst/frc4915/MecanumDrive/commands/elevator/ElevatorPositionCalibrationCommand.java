package org.usfirst.frc4915.MecanumDrive.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4915.MecanumDrive.Robot;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.debuggersystem.CustomDebugger.LoggerNames;

public class ElevatorPositionCalibrationCommand extends CommandGroup {

    Elevator elevator = Robot.elevator;

    public ElevatorPositionCalibrationCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        // addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        // addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        Robot.debugger.logError(LoggerNames.ELEVATOR, "ElevatorPositionCalibrationCommand Running");
        addParallel(new ElevatorMoveToHeightCommand());
        addSequential(new ElevatorMinHeightCalibrateCommand());
        addSequential(new ElevatorMaxHeightCalibrateCommand());
        addSequential(new ElevatorStopCommand());
    }
}
