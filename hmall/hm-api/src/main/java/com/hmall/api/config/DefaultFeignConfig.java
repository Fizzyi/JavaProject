package com.hmall.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;



/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/24
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
