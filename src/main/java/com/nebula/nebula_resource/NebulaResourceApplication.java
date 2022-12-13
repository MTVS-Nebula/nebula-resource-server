package com.nebula.nebula_resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NebulaResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NebulaResourceApplication.class, args);
    }

}
