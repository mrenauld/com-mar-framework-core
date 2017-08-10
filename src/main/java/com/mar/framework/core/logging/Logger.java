package com.mar.framework.core.logging;

public class Logger {

    public static String ERROR = "ERROR";
    public static String WARNING = "WARNING";
    public static String INFO = "INFO";
    public static String DEBUG = "DEBUG";

    public static int LEVEL_ERROR = 0;
    public static int LEVEL_WARNING = 1;
    public static int LEVEL_INFO = 2;
    public static int LEVEL_DEBUG = 3;

    private static int level = LEVEL_DEBUG;

    /**
     * Returns the log level.
     * @return
     */
    public static int getLevel() {
        return level;
    }

    /**
     * Logs the specified message as debug.
     * @param c
     * @param message
     */
    public static void logDebug(Object pClass, final String message) {
        if (level >= LEVEL_DEBUG) {
            Class c = getClass(pClass);
            final StringBuilder sb = getLineHeader();
            sb.append(DEBUG + " [");
            sb.append(c.getSimpleName() + "] ");
            sb.append(message);
            System.out.println(sb.toString());
        }
    }

    /**
     * Logs the specified message as error.
     * @param c
     * @param message
     */
    public static void logError(Object pClass, String message) {
        if (level >= LEVEL_ERROR) {
            Class c = getClass(pClass);
            final StringBuilder sb = getLineHeader();
            sb.append(ERROR + " [");
            sb.append(c.getSimpleName() + "] ");
            sb.append(message);
            System.out.println(sb.toString());
        }
    }

    /**
     * Logs the specified message as info.
     * @param c
     * @param message
     */
    public static void logInfo(Object pClass, final String message) {
        if (level >= LEVEL_INFO) {
            Class c = getClass(pClass);
            final StringBuilder sb = getLineHeader();
            sb.append(INFO + " [");
            sb.append(c.getSimpleName() + "] ");
            sb.append(message);
            System.out.println(sb.toString());
        }
    }

    /**
     * Logs the specified message as info.
     * @param c
     * @param message
     */
    public static void logWarning(Object pClass, final String message) {
        if (level >= LEVEL_WARNING) {
            Class c = getClass(pClass);
            final StringBuilder sb = getLineHeader();
            sb.append(WARNING + " [");
            sb.append(c.getSimpleName() + "] ");
            sb.append(message);
            System.out.println(sb.toString());
        }
    }

    /**
     * Sets the log level.
     * @param pLevel
     */
    public static void setLevel(int pLevel) {
        level = pLevel;
    }

    /**
     * Returns the class.
     * @param pClass
     * @return
     */
    private static Class getClass(Object pClass) {
        if (pClass instanceof Class) {
            return (Class) pClass;
        }
        else {
            return pClass.getClass();
        }
    }

    /**
     * Returns the header to display before each message.
     * @return
     */
    private static StringBuilder getLineHeader() {
        final StringBuilder sb = new StringBuilder();
        sb.append(LoggerUtils.getDateString() + " ");
        sb.append(LoggerUtils.getTimeString() + " ");
        return sb;
    }
}