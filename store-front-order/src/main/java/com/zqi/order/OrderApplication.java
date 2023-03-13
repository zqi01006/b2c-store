package com.zqi.order;

import com.zqi.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.zqi.order.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {
    public static void main(String[] args)  {
        SpringApplication.run(OrderApplication.class, args);
    }
}
