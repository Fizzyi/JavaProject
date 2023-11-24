package com.hmall.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/24
 */

@Configuration
public class RemoteCallConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
