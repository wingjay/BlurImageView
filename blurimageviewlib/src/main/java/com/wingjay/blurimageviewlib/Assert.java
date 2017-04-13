package com.wingjay.blurimageviewlib;

/**
 * Created by seanzhou on 9/18/16.
 * <p>
 * Provides methods for asserting the truth of expressions and properties.
 */
public final class Assert {
    private static final boolean DEBUG_MODE = true;

    private Assert() {

    }

    /**
     * Ensures the truth of an expression involving one or more arguments passed to the calling
     * method.
     *
     * @param expression   A boolean expression.
     * @param errorMessage The exception message to use if the check fails. The message is converted
     *                     to a {@link String} using {@link String#valueOf(Object)}.
     * @throws IllegalArgumentException If {@code expression} is false.
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (DEBUG_MODE && !expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * Ensures that an object reference is not null.
     *
     * @param reference An object reference.
     * @return The non-null reference that was validated.
     * @throws NullPointerException If {@code reference} is null.
     */
    public static <T> T checkNotNull(T reference) {
        if (DEBUG_MODE && reference == null)
            throw new NullPointerException();
        return reference;
    }

    /**
     * Ensures that an object reference is not null.
     *
     * @param reference    An object reference.
     * @param errorMessage The exception message to use if the check fails. The message is converted
     *                     to a string using {@link String#valueOf(Object)}.
     * @return The non-null reference that was validated.
     * @throws NullPointerException If {@code reference} is null.
     */
    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (DEBUG_MODE && reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression involving one or more arguments passed to the calling
     * method.
     *
     * @param expression A boolean expression.
     * @throws IllegalArgumentException If {@code expression} is false.
     */
    public static void checkArgument(boolean expression) {
        if (DEBUG_MODE && !expression)
            throw new IllegalArgumentException();
    }
}
