package com.hmall.cart.config;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Demo class
 * 读取 nacos 中的配置
 * @author Zhaohangyi
 * @time 2023/11/29
 */



@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxAmount;
}
