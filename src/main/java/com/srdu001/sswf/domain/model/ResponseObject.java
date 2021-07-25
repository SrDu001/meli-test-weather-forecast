package com.srdu001.sswf.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObject {
    private Integer code;
    private String description;
    private Object data;
}
