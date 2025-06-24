package com.xy.external.response;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-22 19:34
 */

@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class MultiResponse<T> extends AbstractResponse {
    private List<T> data;

    public List<T> getData() {
        return null == data ? Collections.emptyList() : new ArrayList<>(data);
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public static <T> MultiResponse<T> success(List<T> t) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(true);
        response.setData(t);
        return response;
    }

    public static <T> MultiResponse<T> failure(List<T> t, String errorCode, String errorMsg) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setData(t);
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
