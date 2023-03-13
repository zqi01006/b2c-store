package com.zqi.clients;

import com.zqi.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 购物车调用的feign客户端
 */
@FeignClient("cart-service")
public interface CartClient {

    @PostMapping("/cart/remove/check")
    R check(@RequestBody Integer productId);
}
