package com.srdu001.sswf.config;

import com.srdu001.sswf.bd.IWeatherConditionDAO;
import com.srdu001.sswf.bd.WeatherCondition;
import com.srdu001.sswf.service.SSWFService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitializationJob {
private final IWeatherConditionDAO weatherConditionDAO;
    private final SSWFService SSWFService;

    @Value("${weather.forecast.days}")
    private Integer days;


    @PostConstruct
    public void initializationJob() {
        weatherConditionDAO.save(WeatherCondition.builder().name("Rainy").build());
        weatherConditionDAO.save(WeatherCondition.builder().name("Optimal").build());
        weatherConditionDAO.save(WeatherCondition.builder().name("Normal").build());
        weatherConditionDAO.save(WeatherCondition.builder().name("Drought").build());
        SSWFService.generateWeatherForecast(days);
    }

}