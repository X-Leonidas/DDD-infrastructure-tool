package com.xy.external.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiangyu
 * @date 2025-06-22 19:25
 */


@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class SingleResponse<T> extends AbstractResponse {

    private T data;

    public static SingleResponse<Void> success() {
        SingleResponse<Void> response = new SingleResponse<>();
        response.setSuccess(true);
        return response;
    }

    public static SingleResponse<Void> failure(String errorCode, String errorMsg) {
        SingleResponse<Void> response = new SingleResponse<>();
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }

    public static <T> SingleResponse<T> success(T t) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setData(t);
        return response;
    }


    public static <T> SingleResponse<T> failure(T t, String errorCode, String errorMsg) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setData(t);
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
