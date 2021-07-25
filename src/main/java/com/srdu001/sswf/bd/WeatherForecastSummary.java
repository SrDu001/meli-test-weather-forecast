package com.srdu001.sswf.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private Integer maxRainyDay;

    @Builder.Default
    private Integer rainyPeriods = 0;

    @Builder.Default
    private Integer normalPeriods = 0;

    @Builder.Default
    private Integer droughtPeriods = 0;

    @Builder.Default
    private Integer optimalPeriods = 0;

    public void addRainyPeriod() {
        this.rainyPeriods++;
    }

    public void addNormalPeriod() {
        this.normalPeriods++;
    }

    public void addDroughtPeriod() {
        this.droughtPeriods++;
    }

    public void addOptimalPeriod() {
        this.optimalPeriods++;
    }
}
