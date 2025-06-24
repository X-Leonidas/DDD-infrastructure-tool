package com.xy.expection;

/**
 * @author xiangyu
 * @date 2025-06-22 20:51
 */
public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable throwable, String message) {
        super(message, throwable);
    }

    /**
     * definition error code
     *
     * @return error code
     */
    public abstract String getErrorCode();

    @Override
    public String toString() {
        String className = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (className + ":" + getErrorCode() + "," + message) : className;
    }
}


