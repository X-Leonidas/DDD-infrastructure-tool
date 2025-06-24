package com.xy.external.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiangyu
 * @date 2025-06-22 17:01
 */


@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractResponse {
    private boolean success;

    private String errorCode;

    private String errorMsg;
}
