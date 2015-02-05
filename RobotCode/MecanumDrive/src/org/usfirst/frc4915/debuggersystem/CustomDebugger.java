package org.usfirst.frc4915.debuggersystem;

/*
 * This is a custom system that allows for easy debug logging 
 * and sorting from any system on the robot
 * 
 * TO ADD ON TO THE SYSTEM
 * Simply add or take away parts from the enum LoggerNames
 * at the top of the class file, the initializer will take care of the rest
 * 
 * setFilter(LoggerNames)
 * takes the desired LoggerNames enumerator that the user only wants
 * the debug log to process (ex. if only LoggerNames.DRIVETRAIN was to be
 * seen, the call of object.setFilter(LoggerNames.DRIVETRAIN) would be used)
 *
 * resetFilter()
 * resets the filter to allow all LoggerNames
 *
 * stopParentHandlerLoggerUse()
 * a private utility method that should only ever be called by customDebugger itself
 *
 * setFormatter()
 * a private utility method that the customDebugger uses for its initialization
 *
 * checkFilter()
 * returns an array of booleans that represent whether a LoggerNames at index i
 * is on or off
 *
 * logError(LoggerNames, String)
 * after constructing a customDebugger object, this is how to log errors from any system
 * in the robot code, specify what LoggerNames you would like the system to log it as
 * and then pass it the desired error or debug message, the error will print out in format:
 * Chosen LoggerNames: String
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomDebugger {

    private Map<LoggerNames, Logger> loggerMap = new HashMap<>();

    public CustomDebugger() {
        for (LoggerNames n : LoggerNames.values()) {
            loggerMap.put(n, Logger.getLogger(n.name()));
        }
        this.setFormatter();
        this.stopParentHandlerLoggerUse();
    }

    public void setFilter(LoggerNames a) {
        for (Logger n : loggerMap.values()) {
            if (!n.getName().equalsIgnoreCase(a.name())) {
                n.setLevel(Level.OFF);
            }
        }
    }

    public void resetFilter() {
        for (Logger n : loggerMap.values()) {
            n.setLevel(Level.ALL);
        }
    }

    public void stopParentHandlerLoggerUse() {
        for (Logger n : loggerMap.values()) {
            n.setUseParentHandlers(false);
        }
    }

    public void setFormatter() {
        CustomFormatter customFormatter = new CustomFormatter();
        ConsoleHandler handler = new ConsoleHandler();

        handler.setFormatter(customFormatter);
        for (Logger n : loggerMap.values()) {
            n.addHandler(handler);
        }
    }

    public boolean[] checkFilter() {
        //returns an array of booleans showing if a logger is off or on (false for off true for on)
        boolean[] output = new boolean[loggerMap.size()];
        int i = 0;
        for (Logger n : loggerMap.values()) {
            output[i] = (n.getLevel() == Level.OFF);
            i++;
        }

        return output;
    }

    public void logError(LoggerNames a, String b) {
        loggerMap.get(a).info(b);
    }

    public static enum LoggerNames {
        DRIVETRAIN,
        GRABBER,
        GENERAL,
        AUTONOMOUS,
        ELEVATOR
    }

}

