package com.srdu001.sswf.api.controller;

import com.srdu001.sswf.bd.IForecastDAO;
import com.srdu001.sswf.bd.IWeatherForecastSummaryDAO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sswf")
public class ForecastRestController {

    private final IForecastDAO IForecastDAO;
    private final IWeatherForecastSummaryDAO weatherForecastSummaryDAO;

    @GetMapping(value = "/weather")
    public ResponseEntity weatherForecast(@RequestParam(value = "day") Integer day) {
            return ResponseEntity.ok().body(IForecastDAO.findByDay(day));
    }

    @GetMapping(value = "/summary")
    public ResponseEntity forecastSummary() {
        return ResponseEntity.ok().body(weatherForecastSummaryDAO.findAll().get(0));
    }
}
