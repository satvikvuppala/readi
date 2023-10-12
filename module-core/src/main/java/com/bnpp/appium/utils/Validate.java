package com.bnpp.appium.utils;

import java.lang.reflect.Array;

public class Validate {
    /**
     * Validate that the specified argument is not {@code null};otherwise throwing an exception.
     *
     * @param value   the object to check
     * @param message Message for exception in case the value is null
     */
    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }

    /**
     * Validate that the specified stringis not empty ;otherwise throwing an exception.
     *
     * @param value   the object to check
     * @param message Message for exception in case the value is null
     */
    public static void notEmpty(String value, String message) {
        if (value == null || "".equals(value.trim())) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Validate that the specified string is not empty ;otherwise throwing an exception.
     *
     * @param value   the object to check
     * @param message Message for exception in case the value is null
     */
    public static void notEmpty(Object[] value, String message) {

        if (value == null || !(Array.getLength(value) > 0)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Check whether the boolean is true;otherwise throwing an exception.
     *
     * @param value   the object to check
     * @param message Message for exception in case the value is null
     */
    public static void notFalse(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }


}
