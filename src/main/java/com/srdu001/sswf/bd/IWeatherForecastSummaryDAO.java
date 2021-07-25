package com.srdu001.sswf.bd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeatherForecastSummaryDAO extends JpaRepository<WeatherForecastSummary, Integer> {
}
