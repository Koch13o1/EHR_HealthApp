package com.kochhealth.vitals_service.dto;

import lombok.Data;

@Data
public class VitalRequest {
    private String type;
    private String value;
}
