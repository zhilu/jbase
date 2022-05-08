package utiltools;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 日志通用类
 * @author MyEclipse Persistence Tools
 */
public class LogUtil
{

    private static final Logger loggerError;
    private static final Logger loggerInfo;

    static
    {
        loggerError = Logger.getLogger("Error");
        loggerInfo = Logger.getLogger("Info");
    }

    public static void log(String info, java.util.logging.Level level, Throwable ex)
    {
        Level aLevel = getLog4jLevel(level);
        if (aLevel.equals(Level.ERROR))
        {
            logErr(info, getLog4jLevel(level), ex);
        }
        else
        {
            logInfo(info, getLog4jLevel(level), ex);
        }
    }

    public static void logErr(String info, Level level, Throwable ex)
    {
        loggerError.log(level, info, ex);
    }

    public static void logInfo(String info, Level level, Throwable ex)
    {
        loggerInfo.log(level, info, ex);
    }

    public static Logger getLogger()
    {
        return loggerError;
    }

    private static Level getLog4jLevel(java.util.logging.Level level)
    {
        Level returnLevel = Level.ERROR;
        if (java.util.logging.Level.ALL.equals(level))
        {
            returnLevel = Level.ALL;
        }
        else if (java.util.logging.Level.CONFIG.equals(level))
        {
            returnLevel = Level.FATAL;
        }
        else if (java.util.logging.Level.FINE.equals(level) || java.util.logging.Level.FINER.equals(level) || java.util.logging.Level.FINEST.equals(level))
        {
            returnLevel = Level.DEBUG;
        }
        else if (java.util.logging.Level.INFO.equals(level))
        {
            returnLevel = Level.INFO;
        }
        else if (java.util.logging.Level.OFF.equals(level))
        {
            returnLevel = Level.OFF;
        }
        else if (java.util.logging.Level.CONFIG.equals(level))
        {
            returnLevel = Level.INFO;
        }
        else if (java.util.logging.Level.SEVERE.equals(level))
        {
            returnLevel = Level.ERROR;
        }
        else if (java.util.logging.Level.WARNING.equals(level))
        {
            returnLevel = Level.WARN;
        }
        else
        {
            returnLevel = Level.INFO;
        }
        return returnLevel;
    }

}
