package com.xy.expection;

/**
 * @author xiangyu
 * @date 2025-06-22 22:13
 */
public class IllegalArgumentException extends BaseException {
    public IllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "Illegal_Argument";
    }
}
