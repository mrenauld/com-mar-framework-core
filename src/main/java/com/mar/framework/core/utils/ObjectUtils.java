package com.mar.framework.core.utils;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
    /**
     * Check if the specified Boolean is empty or not.
     * @param in
     * @return
     */
    public static final boolean isObjectEmpty(final Boolean in) {
        return in == null;
    }

    /**
     * Check if the specified Collection is empty or not.
     * @param in
     * @return
     */
    public static final boolean isObjectEmpty(final Collection in) {
        return (in == null) || (in.size() == 0);
    }

    /**
     * Check if the specified Map is empty or not.
     * @param in
     * @return
     */
    public static final boolean isObjectEmpty(final Map in) {
        return (in == null) || (in.size() == 0);
    }

    /**
     * Check if the specified Number is empty or not.
     * @param in
     * @return
     */
    public static final boolean isObjectEmpty(final Number in) {
        return in == null;
    }

    /**
     * Check if the specified Object is empty or not.
     * @param in
     *            The Object to validate.
     * @return True or False.
     */
    public static final boolean isObjectEmpty(final Object in) {
        boolean res = false;

        if (in == null) {
            res = true;
        }
        else {
            if (in instanceof String) {
                res = isObjectEmpty((String) in);
            }
            else if (in instanceof Map) {
                res = isObjectEmpty((Map) in);
            }
            else if (in instanceof Collection) {
                res = isObjectEmpty((Collection) in);
            }
            else if (in.getClass().isArray()) {
                res = isObjectEmpty((Object[]) in);
            }
        }

        return res;
    }

    /**
     * Check if the specified array of Object is empty or not.
     * @param in
     * @return
     */
    public static final boolean isObjectEmpty(final Object[] in) {
        return (in == null) || (in.length == 0);
    }

    /**
     * Check if the specified String is empty or not.
     * @param str
     * @return
     */
    public static final boolean isObjectEmpty(final String str) {
        final int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTrue(Boolean b) {
        return b != null && b;
    }
}
