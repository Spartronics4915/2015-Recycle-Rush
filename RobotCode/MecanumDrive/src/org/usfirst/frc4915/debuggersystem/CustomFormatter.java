package org.usfirst.frc4915.debuggersystem;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        String output = "";

        output += record.getLoggerName();
        output += ": ";
        output += record.getMessage();

        output += "\n";
        return output;
    }

    @Override
    public String formatMessage(LogRecord record) {
        String output = "";

        output += record.getLoggerName();
        output += ": ";
        output += record.getMessage();

        output += "\n";
        return output;
    }

    @Override
    public String getTail(Handler h) {
        return "";
    }

    @Override
    public String getHead(Handler h) {
        return "";
    }

}
