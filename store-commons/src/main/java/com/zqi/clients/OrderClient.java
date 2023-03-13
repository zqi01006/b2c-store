package com.zqi.clients;

import com.zqi.param.PageParam;
import com.zqi.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单对应的feign客户端
 */
@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("/order/admin/list")
    R list(@RequestBody PageParam pageParam);
}
