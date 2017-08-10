package com.mar.framework.core.exception;

public class ReturnUtils {

    /**
     * Returns a new MarException with the specified message.
     * @param pClass
     * @param pMessage
     * @return
     */
    public static MarException createException(final Object pClass, final String pMessage) {
        final StringBuilder endMessage = new StringBuilder();
        endMessage.append("Exception in class [" + getClass(pClass) + "] - message: [" + pMessage + "]");

        System.out.println(endMessage.toString());

        return new MarException(endMessage.toString());
    }

    /**
     * Returns a new MarException built from the specified throwable.
     * @param pClass
     * @param pTrhowable
     * @return
     */
    public static MarException createException(final Object pClass, Throwable pTrhowable) {
        final StringBuilder endMessage = new StringBuilder();
        endMessage.append("Exception in class [" + getClass(pClass) + "] - message: [" + pTrhowable.getMessage() + "]");

        System.out.println(endMessage.toString());

        return new MarException(endMessage.toString());
    }

    /**
     * Returns the class.
     * @param pClass
     * @return
     */
    private static Class getClass(final Object pClass) {
        if (pClass instanceof Class) {
            return (Class) pClass;
        }
        else {
            return pClass.getClass();
        }
    }

}