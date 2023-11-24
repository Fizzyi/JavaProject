package com.hmall.cart.client;

import com.hmall.cart.domain.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/24
 */
@FeignClient("item-service")
public interface ItemClient {

    /**
     * - @FeignClient("item-service") ：声明服务名称
     * - @GetMapping ：声明请求方式
     * - @GetMapping("/items") ：声明请求路径
     * - @RequestParam("ids") Collection<Long> ids ：声明请求参数
     * - List<ItemDTO> ：返回值类型
     * @param ids
     * @return
     */
    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);
}
