package com.kochhealth.vitals_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vital {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // This will come from JWT Token

    private String type; // Could be blood_pressure, glucose, etc
    private String value; // 120/80, 100 mg/dL

    private LocalDateTime timestamp;

}
