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

    /**
     * Constructor.
     *
     * <p>
     *     This method:
     * </p>
     * <ul>
     *     <li>Fills the <code>loggerMap</code> with the loggers defined in {@link LoggerNames}.</li>
     *     <li>Calls {@link #setFormatter()} and {@link #stopParentHandlerLoggerUse()}</li>
     * </ul>
     */
    public CustomDebugger() {
        for (LoggerNames n : LoggerNames.values()) {
            loggerMap.put(n, Logger.getLogger(n.name()));
        }
        this.setFormatter();
        this.stopParentHandlerLoggerUse();
    }

    /**
     *
     * Sets the internal logger filter. This turns all loggers other than <code>logger</code> off.
     *
     * @param logger Logger to filter the output of
     */
    public void setFilter(LoggerNames logger) {
        for (Logger n : loggerMap.values()) {
            if (!n.getName().equalsIgnoreCase(logger.name())) {
                n.setLevel(Level.OFF);
            }
        }
    }

    /**
     * Clears the filter set by {@link #setFilter}.
     *
     * This must be called before another filter is set.
     */
    public void resetFilter() {
        for (Logger n : loggerMap.values()) {
            n.setLevel(Level.ALL);
        }
    }

    /**
     * Sets the internal Java logger's <code>setUseParentHandlers</code> option to false
     *
     * <p>
     *     This is an important step in getting this system to even work.
     * </p>
     */
    private void stopParentHandlerLoggerUse() {
        for (Logger n : loggerMap.values()) {
            n.setUseParentHandlers(false);
        }
    }

    /**
     * Gives each logger a preconfigured handler during initialization.
     */
    private void setFormatter() {
        CustomFormatter customFormatter = new CustomFormatter();
        ConsoleHandler handler = new ConsoleHandler();

        handler.setFormatter(customFormatter);
        for (Logger n : loggerMap.values()) {
            n.addHandler(handler);
        }
    }

    /**
     *
     * Check the filter state of each logger
     *
     * @return Array representing the filter state of the Logger at index i relative to position in {@link LoggerNames}
     */
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

    /**
     *
     * Outputs a message to a specified logger.
     *
     * @param logger Logger to throw the error with
     * @param message Message to send to <code>logger</code>
     */
    public void logError(LoggerNames logger, String message) {
        loggerMap.get(logger).info(message);
    }

    /**
     * Collection of logger names (categories)
     */
    enum LoggerNames {
        DRIVETRAIN,
        GRABBER,
        GENERAL,
        AUTONOMOUS,
        ELEVATOR
    }

}

