package com.hmall.api.config;

import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;


/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/24
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new RequestInterceptor() {
            // 利用RequestTemplate类来添加请求头，将用户信息保存到请求头中。这样以来，每次OpenFeign发起请求的时候都会调用该方法，传递用户信息。
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 获取登录用户
                Long userId = UserContext.getUser();
                if (userId == null) {
                    // 如果为空直接跳过
                    return;
                }
                // 如果不为空则放入请求头中，传递给下游的微服务
                requestTemplate.header("user-info", userId.toString());
            }
        };
    }
}
