package Utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Utilities.Log.class.getName());

    public static void main(String[] args)
    {
        //PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("log4j.properties");

        //Log in console in and log file
        Log.debug("Log4j appender configuration is successful !!");
    }

    //We can use it when starting tests
    public static void startLog(String className){
        Log.info("----------------------------------------------------------------");
        Log.info("           Testcase Execution Started For "+ className           );
        Log.info("----------------------------------------------------------------");
    }

    //We can use it when ending tests
    public static void endLog(String className){

        Log.info("----------------------------------------------------------------");
        Log.info("           TestCase Execution Completed For "+ className         );
        Log.info("----------------------------------------------------------------");
    }

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}
