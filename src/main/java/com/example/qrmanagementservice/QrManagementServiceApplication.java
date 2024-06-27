package com.example.qrmanagementservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebFlux
@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "/qr",
                        description = "Gateway Server Url"
                )
        },
        security = @SecurityRequirement(name = "Authorization")
)
public class QrManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrManagementServiceApplication.class, args);
    }

}
