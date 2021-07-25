package com.srdu001.sswf.service;

import com.srdu001.sswf.bd.*;
import com.srdu001.sswf.bd.IForecastDAO;
import com.srdu001.sswf.domain.model.Planet;
import com.srdu001.sswf.domain.model.SolarSystem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.srdu001.sswf.util.Geometry.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SSWFService {

    private final SolarSystem solarSystem;
    private final IForecastDAO forecastDAO;
    private final IWeatherForecastSummaryDAO weatherForecastSummaryDAO;
    private final IWeatherConditionDAO weatherConditionDAO;
    private final Point2D.Double SUN_POS = new Point2D.Double(0.0, 0.0);

    public WeatherForecastSummary generateWeatherForecast(int daysToCalculate) {

        WeatherForecastSummary weatherForecastSummary = WeatherForecastSummary.builder().build();
        String lastForecastCondition = "";
        Double maxPerimeter = 0d;
        List<Forecast> forecastList = new ArrayList<>();
        for (int d = 1; d <= daysToCalculate; d++) {
            Forecast currentForecast = getWeatherByDay(d);
            forecastList.add(currentForecast);
            //first forecast
            if (d == 1) {
                lastForecastCondition = currentForecast.getWeatherCondition().getName();
                addPeriod(weatherForecastSummary, lastForecastCondition);
            }
            //next forecasts
            if (!currentForecast.getWeatherCondition().getName().equals(lastForecastCondition)) {
                lastForecastCondition = currentForecast.getWeatherCondition().getName();
                addPeriod(weatherForecastSummary, currentForecast.getWeatherCondition().getName());
            }
            //if its rainy, maxRainyDay is calculated
            if (currentForecast.getWeatherCondition().getName().equals("Rainy")) {
                if (currentForecast.getPerimeter() >= maxPerimeter) {
                    maxPerimeter = currentForecast.getPerimeter();
                    weatherForecastSummary.setMaxRainyDay(currentForecast.getDay());
                }
            }
        }
        forecastDAO.saveAll(forecastList);
        weatherForecastSummaryDAO.save(weatherForecastSummary);
        return weatherForecastSummary;
    }

    private WeatherForecastSummary addPeriod(WeatherForecastSummary weatherForecastSummary, String name) {
        switch (name) {
            case "Rainy":
                weatherForecastSummary.addRainyPeriod();
                break;
            case "Normal":
                weatherForecastSummary.addNormalPeriod();
                break;
            case "Drought":
                weatherForecastSummary.addDroughtPeriod();
                break;
            case "Optimal":
                weatherForecastSummary.addOptimalPeriod();
                break;
        }
        return weatherForecastSummary;
    }

    private Forecast getWeatherByDay(int day) {

        Point2D p1 = getPlanetPosition(solarSystem.getPlanets().get(0), day);
        Point2D p2 = getPlanetPosition(solarSystem.getPlanets().get(1), day);
        Point2D p3 = getPlanetPosition(solarSystem.getPlanets().get(2), day);
        Forecast result = Forecast.builder()
                .day(day)
                .planet1Position(getStringPoint(p1))
                .planet2Position(getStringPoint(p2))
                .planet3Position(getStringPoint(p3))
                .build();
        //are planets aligned
        if (areAligned(p1, p2, p3)) {
            //is sun aligned
            result.setWeatherCondition(areAligned(p1, p3, SUN_POS) ? weatherConditionDAO.findByName("Drought") : weatherConditionDAO.findByName("Optimal"));
            result.setPerimeter(0d);
        } else {
            //is sun in triangle
            result.setWeatherCondition(isInsideTriangle(p1, p2, p3, SUN_POS) ? weatherConditionDAO.findByName("Rainy") : weatherConditionDAO.findByName("Normal"));
            result.setPerimeter(trianglePerimeter(p1, p2, p3));
        }
        return result;
    }

    private Point2D getPlanetPosition(Planet planet, Integer day) {
        Integer degrees = planet.isClockWise() ? day * planet.getAngularVelocity() : day * -planet.getAngularVelocity();
        return radialPosition(planet.getDistanceToSun(), degrees);
    }

    private String getStringPoint(Point2D p) {
        return "(" + simplify(p.getX(), 100d) + "," + simplify(p.getY(), 100d) + ")";
    }
}