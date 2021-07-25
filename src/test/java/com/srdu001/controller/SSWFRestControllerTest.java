package com.srdu001.controller;

import com.srdu001.sswf.api.controller.SSWFRestController;
import com.srdu001.sswf.bd.Forecast;
import com.srdu001.sswf.bd.IForecastDAO;
import com.srdu001.sswf.bd.IWeatherForecastSummaryDAO;
import com.srdu001.sswf.bd.WeatherForecastSummary;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {SSWFRestController.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class SSWFRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IForecastDAO IForecastDAO;

    @MockBean
    private IWeatherForecastSummaryDAO weatherForecastSummaryDAO;


    @Test
    void findWeatherByDay() throws Exception {
        Forecast forecast = Forecast.builder().build();
        Mockito.when(IForecastDAO.findByDay(Mockito.any())).thenReturn(forecast);
        mockMvc.perform(get("/api/sswf/weather?day=1")).andExpect(status().isOk());
    }

    @Test
    public void findSummary() throws Exception {
        List<WeatherForecastSummary> forecastSummary = Arrays.asList(WeatherForecastSummary.builder().build());
        Mockito.when(weatherForecastSummaryDAO.findAll()).thenReturn(forecastSummary);
        mockMvc.perform(get("/api/sswf/summary")).andExpect(status().isOk());
    }

    @Test
    void findWeatherByDayNotFound() throws Exception {
        Mockito.when(IForecastDAO.findByDay(Mockito.any())).thenReturn(null);
        mockMvc.perform(get("/api/sswf/weather?day=1")).andExpect(status().isNotFound());
    }

    @Test
    public void findSummaryNotFound() throws Exception {
        Mockito.when(weatherForecastSummaryDAO.findAll()).thenReturn(null);
        mockMvc.perform(get("/api/sswf/summary")).andExpect(status().isNotFound());
    }
}
