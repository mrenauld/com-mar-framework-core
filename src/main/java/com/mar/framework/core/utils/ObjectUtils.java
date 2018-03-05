package com.mar.framework.core.utils;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    /**
     * Check if the specified Boolean is empty or not.
     *
     * @param pIn
     * @return
     */
    public static boolean isObjectEmpty(Boolean pIn) {
        return pIn == null;
    }

    /**
     * Check if the specified Collection is empty or not.
     *
     * @param pIn
     * @return
     */
    public static boolean isObjectEmpty(Collection<?> pIn) {
        return (pIn == null) || (pIn.size() == 0);
    }

    /**
     * Check if the specified Map is empty or not.
     *
     * @param pIn
     * @return
     */
    public static boolean isObjectEmpty(Map<?, ?> pIn) {
        return (pIn == null) || (pIn.size() == 0);
    }

    /**
     * Check if the specified Number is empty or not.
     *
     * @param pIn
     * @return
     */
    public static boolean isObjectEmpty(Number pIn) {
        return pIn == null;
    }

    /**
     * Check if the specified Object is empty or not.
     *
     * @param pIn
     *            The Object to validate.
     * @return True or False.
     */
    public static boolean isObjectEmpty(Object pIn) {
        boolean res = false;

        if (pIn == null) {
            res = true;
        } else {
            if (pIn instanceof String) {
                res = isObjectEmpty((String) pIn);
            } else if (pIn instanceof Map) {
                res = isObjectEmpty((Map<?, ?>) pIn);
            } else if (pIn instanceof Collection) {
                res = isObjectEmpty((Collection<?>) pIn);
            } else if (pIn.getClass().isArray()) {
                res = isObjectEmpty((Object[]) pIn);
            }
        }

        return res;
    }

    /**
     * Check if the specified array of Object is empty or not.
     *
     * @param pIn
     * @return
     */
    public static boolean isObjectEmpty(Object[] pIn) {
        return (pIn == null) || (pIn.length == 0);
    }

    /**
     * Check if the specified String is empty or not.
     *
     * @param pIn
     * @return
     */
    public static boolean isObjectEmpty(String pIn) {
        int strLen;
        if ((pIn == null) || ((strLen = pIn.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(pIn.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the specified Boolean is not null and is equal to
     * Boolean.TRUE.
     *
     * @param pIn
     * @return
     */
    public static boolean isTrue(Boolean pIn) {
        return pIn != null && pIn;
    }
}
