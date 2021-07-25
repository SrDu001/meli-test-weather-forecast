package com.srdu001.sswf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(
        title = "Solar System Weather Forecast API",
        version = "1.0",
        description = "Weather forecast for the fictional star trek-based Solar system"))
public class SSWFApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSWFApplication.class, args);
    }
}
