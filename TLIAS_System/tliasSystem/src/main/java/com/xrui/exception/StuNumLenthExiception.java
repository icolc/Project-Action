package com.xrui.exception;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/27 11:10
 * @description:
 */
public class StuNumLenthExiception extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public StuNumLenthExiception() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public StuNumLenthExiception(String message) {
        super(message);
    }
}
