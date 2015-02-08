package org.usfirst.frc4915.MecanumDrive.commands;
import org.usfirst.frc4915.MecanumDrive.Robot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto1 extends CommandGroup {
	
	Preferences preferences;

    public Auto1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	preferences = Preferences.getInstance();
    	double distance = preferences.getDouble("DesiredDistance", 9.0);
    	
    	for (int i=0; i<10; i++) {
    		System.out.println("***TO MOVE "+distance+" FT*** (AUTO 1)");
    	}
    	
    	addSequential(new MoveStraightGivenDistanceCommand(distance));
    	
    }  
}

