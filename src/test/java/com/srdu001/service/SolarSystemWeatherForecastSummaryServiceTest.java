package com.srdu001.service;

import com.srdu001.sswf.bd.*;
import com.srdu001.sswf.domain.model.Planet;
import com.srdu001.sswf.domain.model.SolarSystem;
import com.srdu001.sswf.service.SSWFService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class SolarSystemWeatherForecastSummaryServiceTest {

    @Mock
    private IForecastDAO IForecastDAO;

    @Mock
    private IWeatherForecastSummaryDAO weatherForecastSummaryDAO;

    @Mock
    private IWeatherConditionDAO weatherConditionDAO;

    @InjectMocks
    private SSWFService service;

    private SolarSystem solarSystem;

    @BeforeEach
    void init() {
        solarSystem = SolarSystem.builder().planets(Arrays.asList(
                Planet.builder().name("Ferengi").angularVelocity(1).distanceToSun(500).clockWise(true).build(),
                Planet.builder().name("Betasoide").angularVelocity(3).distanceToSun(2000).clockWise(true).build(),
                Planet.builder().name("Vulcano").angularVelocity(5).distanceToSun(1000).clockWise(false).build()))
                .build();
        Mockito.when(weatherConditionDAO.findByName("Rainy")).thenReturn(WeatherCondition.builder().name("Rainy").build());
        Mockito.when(weatherConditionDAO.findByName("Normal")).thenReturn(WeatherCondition.builder().name("Normal").build());
        Mockito.when(weatherConditionDAO.findByName("Drought")).thenReturn(WeatherCondition.builder().name("Drought").build());
        Mockito.when(weatherConditionDAO.findByName("Optimal")).thenReturn(WeatherCondition.builder().name("Optimal").build());
        service = new SSWFService(solarSystem, IForecastDAO, weatherForecastSummaryDAO, weatherConditionDAO);

    }

    @Test
    void generateWeatherForecast() {
        WeatherForecastSummary expectedForecastSummary = WeatherForecastSummary.builder()
                .droughtPeriods(40)
                .maxRainyDay(2808)
                .rainyPeriods(81)
                .normalPeriods(102)
                .optimalPeriods(20).build();
        final WeatherForecastSummary actualForecastSummary = service.generateWeatherForecast(3650);

        Assertions.assertEquals(expectedForecastSummary, actualForecastSummary);
    }

}