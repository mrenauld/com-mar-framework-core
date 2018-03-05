package com.mar.framework.core.logging;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class LogUtils {

    /** Error level. */
    public static final int LEVEL_ERROR = 0;

    /** Warning level. */
    public static final int LEVEL_WARNING = 1;

    /** Info level. */
    public static final int LEVEL_INFO = 2;

    /** Debug level. */
    public static final int LEVEL_DEBUG = 3;

    /** Level display names. */
    public static final String[] LEVEL_NAME = { "ERROR  ", "WARNING", "INFO   ", "DEBUG  " };

    /** Length of the displayed class name. */
    public static final int CLASS_NAME_LENGTH = 32;

    /** Date format. */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Current level. */
    private static int level = LEVEL_DEBUG;

    /**
     * Returns the log level.
     *
     * @return
     */
    public static int getLevel() {
        return level;
    }

    /**
     * Logs the specified message from the specified class at the specified
     * level.
     *
     * @param pClass
     * @param pMessage
     * @param pLevel
     */
    public static void log(Object pClass, String pMessage, int pLevel) {
        if (level >= pLevel) {
            String className = getClassName(pClass);

            StringBuilder sb = new StringBuilder();
            sb.append(getLineHeader());
            sb.append(" ");
            sb.append(LEVEL_NAME[pLevel]);
            sb.append(" [" + className + "] ");
            sb.append(pMessage);

            System.out.println(sb.toString());
        }
    }

    /**
     * Logs the specified message from the specified class as debug.
     *
     * @param c
     * @param message
     */
    public static void logDebug(Object pClass, String pMessage) {
        log(pClass, pMessage, LEVEL_DEBUG);
    }

    /**
     * Logs the specified message from the specified class as error.
     *
     * @param c
     * @param message
     */
    public static void logError(Object pClass, String pMessage) {
        log(pClass, pMessage, LEVEL_ERROR);
    }

    /**
     * Logs the specified message + throwable message for the specified class as
     * error.
     *
     * @param pClass
     * @param pMessage
     * @param pThrowable
     */
    public static void logError(Object pClass, String pMessage, Throwable pThrowable) {
        log(pClass, pMessage + " - Exception message [" + pThrowable.getMessage() + "]", LEVEL_ERROR);
    }

    /**
     * Logs the specified message from the specified class as info.
     *
     * @param c
     * @param message
     */
    public static void logInfo(Object pClass, String pMessage) {
        log(pClass, pMessage, LEVEL_INFO);
    }

    /**
     * Logs the specified message from the specified class as info.
     *
     * @param c
     * @param message
     */
    public static void logWarning(Object pClass, String pMessage) {
        log(pClass, pMessage, LEVEL_WARNING);
    }

    /**
     * Sets the log level.
     *
     * @param pLevel
     */
    public static void setLevel(int pLevel) {
        level = pLevel;
    }

    /**
     * Returns an instance of the calendar.
     *
     * @return
     */
    private static GregorianCalendar getCalendar() {
        final GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        return calendar;
    }

    /**
     * Returns the class.
     *
     * @param pClass
     * @return
     */
    private static Class<?> getClass(Object pClass) {
        if (pClass instanceof Class) {
            return (Class<?>) pClass;
        } else {
            return pClass.getClass();
        }
    }

    /**
     * Returns a nicely formatted class name.
     *
     * @param pClass
     * @return
     */
    private static String getClassName(Object pClass) {
        Class<?> c = getClass(pClass);
        String fullName = c.getName();
        String[] fullNameSplit = fullName.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fullNameSplit.length - 1; ++i) {
            sb.append(fullNameSplit[i].charAt(0));
            sb.append(".");
        }
        sb.append(fullNameSplit[fullNameSplit.length - 1]);

        /* Set uniform length. */
        String name = "";
        if (sb.length() < CLASS_NAME_LENGTH) {
            StringBuilder sbPad = new StringBuilder();
            for (int i = 0; i < CLASS_NAME_LENGTH - sb.length(); ++i) {
                sbPad.append(" ");
            }
            name = sbPad.append(sb).toString();
        } else if (sb.length() > CLASS_NAME_LENGTH) {
            name = sb.subSequence(sb.length() - CLASS_NAME_LENGTH, sb.length()).toString();
        } else {
            name = sb.toString();
        }

        return name;

    }

    /**
     * Returns the header to display before each message.
     *
     * @return
     */
    private static String getLineHeader() {
        return getTimeString();
    }

    /**
     * Returns a string representation of the current time.
     *
     * @return
     */
    private static String getTimeString() {
        final GregorianCalendar calendar = getCalendar();
        return dateFormat.format(calendar.getTime());
    }
}