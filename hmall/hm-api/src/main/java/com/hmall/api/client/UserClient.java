package com.hmall.api.client;

import com.hmall.api.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/28
 */

@FeignClient(value = "user-service", configuration = DefaultFeignConfig.class)
public interface UserClient {

    @PutMapping("/user/money/deduct")
    void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);


}
