package com.mar.framework.core.exception;

import com.mar.framework.core.logging.LogUtils;

public class ReturnUtils {

    /**
     * Returns a new MarException with the specified message.
     *
     * @param pClass
     * @param pMessage
     * @return
     */
    public static MarException createException(final Object pClass, final String pMessage) {
        final StringBuilder endMessage = new StringBuilder();
        endMessage.append(pMessage);

        LogUtils.logError(pClass, endMessage.toString());

        return new MarException(endMessage.toString());
    }

    /**
     * Returns a new MarException built from the specified throwable.
     *
     * @param pClass
     * @param pTrhowable
     * @return
     */
    public static MarException createException(final Object pClass, Throwable pTrhowable) {
        final StringBuilder endMessage = new StringBuilder();
        endMessage.append(pTrhowable.getMessage());

        LogUtils.logError(pClass, endMessage.toString());

        return new MarException(endMessage.toString());
    }

}