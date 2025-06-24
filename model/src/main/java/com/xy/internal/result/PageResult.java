package com.xy.internal.result;

import lombok.Data;

import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-22 19:56
 */

@Data
public class PageResult<T> {
    private Long totalCount;

    private Long pageSize;

    private Long pageIndex;

    private List<T> data;

    public static <T> PageResult<T> of(List<T> t, Long pageIndex, Long pageSize, Long totalCount) {
        PageResult<T> response = new PageResult<>();
        response.setData(t);
        response.setPageIndex(pageIndex);
        response.setPageSize(pageSize);
        response.setTotalCount(totalCount);
        return response;
    }

    public static <T> PageResult<T> of(Long pageIndex, Long pageSize) {
        PageResult<T> response = new PageResult<>();
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}
