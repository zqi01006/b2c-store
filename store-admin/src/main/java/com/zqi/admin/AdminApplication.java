package com.zqi.admin;

import com.zqi.clients.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.zqi.admin.mapper")
@SpringBootApplication
@EnableCaching
@EnableFeignClients(clients = {UserClient.class, CategoryClient.class,
        SearchClient.class, ProductClient.class, OrderClient.class})
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class,args);
    }
}
