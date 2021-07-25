package com.srdu001.sswf.api.controller;

import com.srdu001.sswf.bd.Forecast;
import com.srdu001.sswf.bd.IForecastDAO;
import com.srdu001.sswf.bd.IWeatherForecastSummaryDAO;
import com.srdu001.sswf.bd.WeatherForecastSummary;
import com.srdu001.sswf.domain.model.ResponseObject;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sswf")
public class SSWFRestController {

    private final IForecastDAO IForecastDAO;
    private final IWeatherForecastSummaryDAO weatherForecastSummaryDAO;

    @GetMapping(value = "/weather")
    public ResponseEntity<ResponseObject> weatherForecast(@RequestParam(value = "day") String day) {
        ResponseObject response = ResponseObject.builder().code(200).description("success").build();
        Forecast forecast = IForecastDAO.findByDay(Integer.valueOf(day));
        response.setData(forecast);
        if (Objects.isNull(forecast)) {
            response.setCode(404);
            response.setDescription("forecast not found");
            return new ResponseEntity<ResponseObject>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ResponseObject>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/summary")
    public ResponseEntity forecastSummary() {
        List<WeatherForecastSummary> forecastSummary = weatherForecastSummaryDAO.findAll();
        ResponseObject response = ResponseObject.builder()
                .code(200)
                .description("success")
                .build();
        if (Objects.isNull(forecastSummary)) {
            response.setCode(404);
            response.setDescription("forecast summary not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setData(forecastSummary.get(0));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
