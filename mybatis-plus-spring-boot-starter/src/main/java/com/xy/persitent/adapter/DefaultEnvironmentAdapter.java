package com.xy.persitent.adapter;

import lombok.RequiredArgsConstructor;

/**
 * @author xiangyu
 * @date 2025-06-24 21:35
 */

@RequiredArgsConstructor
public class DefaultEnvironmentAdapter implements EnvironmentAdapter{

    private final String env;

    @Override
    public String currentEnvironment() {
        return env;
    }
}
