package com.srdu001.sswf.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SolarSystem {
    private List<Planet> planets;
}
