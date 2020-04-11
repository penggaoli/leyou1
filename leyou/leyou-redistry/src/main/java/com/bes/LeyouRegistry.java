package com.bes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LeyouRegistry {
    public static void main(String[] args) {
        SpringApplication.run(LeyouRegistry.class);
    }
}
