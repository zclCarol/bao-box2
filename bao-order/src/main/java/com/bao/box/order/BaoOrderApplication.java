package com.bao.box.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.bao.box.order.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class BaoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoOrderApplication.class, args);
    }

}
