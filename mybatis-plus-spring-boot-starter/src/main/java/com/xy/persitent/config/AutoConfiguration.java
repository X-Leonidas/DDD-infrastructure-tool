package com.xy.persitent.config;

import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.xy.persitent.adapter.DefaultEnvironmentAdapter;
import com.xy.persitent.adapter.EnvironmentAdapter;
import com.xy.persitent.extension.injector.CustomerSqlInjector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiangyu
 * @date 2025-06-24 21:39
 */

@Configuration
public class AutoConfiguration {
    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    @ConditionalOnMissingBean
    public EnvironmentAdapter environmentAdapter() {
        return new DefaultEnvironmentAdapter(profile);
    }

    @Bean
    @ConditionalOnMissingBean
    public AbstractSqlInjector sqlInjector() {
        return new CustomerSqlInjector();
    }
}
