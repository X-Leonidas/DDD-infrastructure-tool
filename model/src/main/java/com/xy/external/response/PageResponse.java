package com.xy.external.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-22 19:39
 */

@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends AbstractResponse {
    @Getter
    private Long totalCount;
    @Getter
    private Long pageSize;
    @Getter
    private Long pageIndex;

    private List<T> data;

    public List<T> getData() {
        return null == data ? Collections.emptyList() : new ArrayList<>(data);
    }

    public static <T> PageResponse<T> success(List<T> t, Long pageIndex, Long pageSize, Long totalCount) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(t);
        response.setPageIndex(pageIndex);
        response.setPageSize(pageSize);
        response.setTotalCount(totalCount);
        return response;
    }

    public static <T> PageResponse<T> failure(String errorCode, String errorMsg) {
        PageResponse<T> response = new PageResponse<>();
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }

    public static <T> PageResponse<T> failure(String errorCode, String errorMsg, Long pageIndex, Long pageSize) {
        PageResponse<T> response = new PageResponse<>();
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}
