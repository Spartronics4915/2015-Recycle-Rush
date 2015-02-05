package debuggerSystem;

/*
 * This is a custom system that allows for easy debug logging 
 * and sorting from any system on the robot
 * 
 * TO ADD ON TO THE SYSTEM
 * Simply add or take away parts from the enum LoggerNames
 * at the top of the class file, the initializer will take care of the rest
 * 
*setFilter (LogerNames)
*takes the desired LoggerNames enumerator that the user only wants 
*the debug log to process (ex. if only LoggerNames.Drivetrain was to be 
*seen, the call of object.setFilter(LoggerNames.Drivetrain) would be used)
*
*resetFilter ()
*resets the filter to allow all LoggerNames
*
*stopParentHandlerLoggerUse()
*a private utility method that should only ever be called by customDebugger itself
*
*setFormatter()
*a private utility method that the customDebugger uses for its initialization
*
*checkFilter ()
*returns an array of booleans that represent whether a LoggerNames at index i
*is on or off
*
*logError(LoggerNames, String)
*after constructing a customDebugger object, this is how to log errors from any system
*in the robot code, specify what LoggerNames you would like the system to log it as
*and then pass it the desired error or debug message, the error will print out in format:
*Chosen LoggerNames: String
*
*
*
*
*/


import java.util.logging.*;
import java.util.*;
public class CustomDebugger
{
    
	private Map<LoggerNames,Logger> loggerMap = new HashMap<>(); 
/**
 * the collection of desired names for the loggers
 */
	enum LoggerNames
	{
	    	Drivetrain,
	    	Grabber,
	    	General,
	    	Autonomous,
	    	Elevator;
    }

/**
 * constructor for CustomDebugger
 *  fills the hashmap with the loggers defined in enumerator LoggerNames
 *  calls setFormatter and stopParentHandlerLogerUse
 */
    public CustomDebugger()
    {
        for (LoggerNames n: LoggerNames.values())
        {
        	loggerMap.put(n,Logger.getLogger(n.name()));
        }
        this.setFormatter();
        this.stopParentHandlerLoggerUse();
        
    }
/**
 * Sets the internal filter of the debugger, turns all
 * other loggers but this off
 * 
 * @param a one of the LoggerNames in the enumerator LoggerNames
 */
    public void setFilter(LoggerNames a)
    {   
        for (Logger n: loggerMap.values())
        {
            if (!n.getName().equalsIgnoreCase(a.name()))
            {
                n.setLevel(Level.OFF);
            }
        }
    }
/**
 * Sets the internal filter of the debugger, turns all
 * loggers back on, this must be called before another
 * filter is applied
 */
    public void resetFilter()
    {
        for (Logger n : loggerMap.values())
        {
                n.setLevel(Level.ALL);
        }
    }
/**
 *  a private method that sets the setUseParentHandlers option
 *  in java.util.logging to false
 * makes the entire formatter system work
 * it is called during 
 */
    private void stopParentHandlerLoggerUse()
    {
        for (Logger n : loggerMap.values())
        {
                n.setUseParentHandlers(false);
        }
    }
/**
 * a private method called during initialization
 * gives each logger a preconfigured handler
 */
    private void setFormatter()
    {
        CustomFormatter doge = new CustomFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        
        handler.setFormatter(doge);
        for (Logger n : loggerMap.values())
        {
        	n.addHandler(handler);
        }
        
        
    }
/**
 * checks through all loggers to test for activation
 * 
 * @return boolean[] at index i it represents if Logger at
 * index i in the enumerator LoggerNames is on or off
 */
    public boolean[] checkFilter()
    {
       //returns an array of booleans showing if a logger is off or on (false for off true for on)
       boolean[] output = new boolean[loggerMap.size()];
       int i = 0;
       for (Logger n: loggerMap.values())
       {
    	   
            if(n.getLevel() == Level.OFF)
            {
                output[i] = false;
            }
            else
            {
                output[i] = true;
            }
            i++;
       }
       
       return output;
    }
    
/**
 * 
 * @param a the LoggerNames that is desired to handle the message
 * @param b the string that is passed to the logger to log
 */
    public void logError(LoggerNames a, String b)
    {   
        loggerMap.get(a).info(b);
    }
    
 

}

