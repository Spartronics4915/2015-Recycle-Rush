package org.usfirst.frc4915.debuggersystem;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a custom system that allows for easy debug logging
 * and sorting from any system on the robot
 * <p/>
 * <p>To add other categories to the system simply add or take away parts from {@link LoggerNames}
 * at the top of the class file, the initializer will take care of the rest</p>
 */
public class CustomDebugger {

    private Map<LoggerNames, Logger> loggerMap = new HashMap<>();

    /**
     * Constructor.
     * <p/>
     * <p>
     * This method:
     * </p>
     * <ul>
     * <li>Fills the <code>loggerMap</code> with the loggers defined in
     * {@link LoggerNames}.</li>
     * <li>Calls {@link #setFormatter()} and
     * {@link #stopParentHandlerLoggerUse()}</li>
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
     * Sets the internal logger filter. This turns all loggers other than <code>logger</code> off.
     * <p/>
     * <p>You <strong>must</strong> call {@link #resetFilter} before setting another filter.</p>
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
     * <p/>
     * <p>This <strong>must</strong> be called before another filter is set.</p>
     */
    public void resetFilter() {
        for (Logger n : loggerMap.values()) {
            n.setLevel(Level.ALL);
        }
    }

    /**
     * Sets the internal Java logger's <code>setUseParentHandlers</code> option
     * to false.
     * <p/>
     * <p>
     * This is an important step in getting this system to even work.
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
     * Check the filter state of each logger
     *
     * @return Array representing the filter state of the Logger at index i relative to position in {@link LoggerNames}
     */
    public boolean[] checkFilter() {
        // returns an array of booleans showing if a logger is off or on (false
        // for off true for on)
        boolean[] output = new boolean[loggerMap.size()];
        int i = 0;
        for (Logger n : loggerMap.values()) {
            output[i] = (n.getLevel() == Level.OFF);
            i++;
        }

        return output;
    }

    /**
     * Outputs a message to a specified logger.
     *
     * @param logger  Logger to throw the error with
     * @param message Message to send to <code>logger</code>
     */
    public void logError(LoggerNames logger, String message) {
        loggerMap.get(logger).info(message);
    }

    /**
     * Collection of logger names (categories)
     */
    public static enum LoggerNames {
        DRIVETRAIN, GRABBER, GENERAL, AUTONOMOUS, ELEVATOR
    }

}
