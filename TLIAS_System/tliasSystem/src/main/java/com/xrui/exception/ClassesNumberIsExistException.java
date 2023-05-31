package com.xrui.exception;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/29 9:35
 * @description: 班级号已经存在
 */
public class ClassesNumberIsExistException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ClassesNumberIsExistException() {
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
    public ClassesNumberIsExistException(String message) {
        super(message);
    }
}
