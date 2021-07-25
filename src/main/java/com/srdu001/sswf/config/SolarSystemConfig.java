package com.srdu001.sswf.config;

import com.srdu001.sswf.model.SolarSystem;
import com.srdu001.sswf.model.Planet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SolarSystemConfig {

    @Bean
    public SolarSystem starTrekSolarSystem() {
        return SolarSystem.builder().planets(Arrays.asList(
                Planet.builder().name("Ferengi").angularVelocity(1).distanceToSun(500).clockWise(true).build(),
                Planet.builder().name("Betasoide").angularVelocity(3).distanceToSun(2000).clockWise(true).build(),
                Planet.builder().name("Vulcano").angularVelocity(5).distanceToSun(1000).clockWise(false).build()))
                .build();
    }

}
