package debuggerSystem;

import java.util.logging.*;
public class CustomFormatter extends Formatter
{
    
    public CustomFormatter()
    {
        super();
    }
    
    @Override
    public String format(LogRecord record)
    {
        String output = "";
        
        output += record.getLoggerName();
        output += ": ";
        output += record.getMessage();
        //System.out.println("Custom format was called");
        output += "\n";
        return output;
    }
    
    @Override
    public String formatMessage(LogRecord record)
    {
        String output = "";
        
        output += record.getLoggerName();
        output += ": ";
        output += record.getMessage();
        System.out.println("Custom formatMessage was called");
        output += "\n";
        return output;
    }
    
    @Override
    public String getTail(Handler h)
    {
    	return "";
    }
    
    @Override
    public String getHead(Handler h)
    {
    	return "";
    }
   
}

