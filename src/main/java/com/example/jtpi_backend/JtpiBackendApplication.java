package com.example.jtpi_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EnableJpaRepositories(basePackages = "com.example.jtpi_backend.repository")
public class JtpiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtpiBackendApplication.class, args);
    }

}
