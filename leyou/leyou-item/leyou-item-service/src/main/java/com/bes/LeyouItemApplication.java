package com.bes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.bes.item.mapper")
public class LeyouItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouItemApplication.class);
    }
}
