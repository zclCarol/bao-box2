package com.bao.box.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BaoProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoProductApplication.class, args);
    }

}
