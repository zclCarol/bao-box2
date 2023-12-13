package com.bao.box.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BaoThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoThirdApplication.class, args);
    }

}
