package com.srdu001.sswf.bd;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer day;
    @JoinColumn(name = "weather_condition", referencedColumnName = "name")
    @ManyToOne
    private WeatherCondition weatherCondition;
    private Double perimeter;
    private String planet1Position;
    private String planet2Position;
    private String planet3Position;

}
