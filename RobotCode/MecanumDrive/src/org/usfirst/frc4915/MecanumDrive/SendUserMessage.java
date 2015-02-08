package org.usfirst.frc4915.MecanumDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SendUserMessage {
	public static void displayMessage() {
		SmartDashboard.putString("DB/String 0", "Message to user");
	}
}
