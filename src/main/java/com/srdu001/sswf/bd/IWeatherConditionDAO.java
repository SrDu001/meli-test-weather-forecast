package com.srdu001.sswf.bd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeatherConditionDAO extends JpaRepository<WeatherCondition, Integer> {
WeatherCondition findByName(String name);
}
