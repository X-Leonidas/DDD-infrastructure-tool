package com.xy.internal.query;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author xiangyu
 * @date 2025-06-22 20:05
 */


@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseQuery {
    private static final Long MAX_PAGE_SIZE = 1000L;

    private static final Long DEFAULT_PAGE_SIZE = 10L;

    private static final Long DEFAULT_PAGE_INDEX = 1L;

    @Builder.Default
    private Long pageIndex = DEFAULT_PAGE_INDEX;

    @Builder.Default
    private Long pageSize = DEFAULT_PAGE_SIZE;


    public Long getPageIndex() {
        if (pageIndex < 1) {
            throw new IllegalArgumentException("page index can not less than 1;");
        }
        return pageIndex;
    }

    public Long getPageSize() {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("page size can not less than 1;");
        } else if (pageSize > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("page size can not bigger than " + MAX_PAGE_SIZE);
        }
        return pageSize;
    }


    public void setPageIndex(Long pageIndex) {
        if (pageIndex == null) {
            this.pageIndex = DEFAULT_PAGE_INDEX;
        } else if (pageIndex < 1) {
            throw new IllegalArgumentException("page index can not less than 1;");
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public void stePageSize(Long pageSize) {
        if (pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else if (pageSize <= 0) {
            throw new IllegalArgumentException("page size can not less than 1;");
        } else if (pageSize > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("page size can not bigger than " + MAX_PAGE_SIZE);
        } else {
            this.pageSize = pageSize;
        }
    }

}
