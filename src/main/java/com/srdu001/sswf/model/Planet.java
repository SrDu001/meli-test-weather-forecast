package com.srdu001.sswf.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Planet {

    private String name;
    private Integer angularVelocity;
    private Integer distanceToSun;
    private boolean clockWise;

}

