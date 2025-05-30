package com.kochhealth.vitals_service.service;

import com.kochhealth.vitals_service.dto.VitalRequest;
import com.kochhealth.vitals_service.model.Vital;
import com.kochhealth.vitals_service.repository.VitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VitalService {
    private final VitalRepository vitalRepository;

    public Vital logVital(Long userId, VitalRequest request){
        Vital vital = Vital.builder()
                .userId(userId)
                .type(request.getType())
                .value(request.getValue())
                .timestamp(LocalDateTime.now())
                .build();
        return vitalRepository.save(vital);
    }

    public List<Vital> getUserVitals(Long userId){
        return vitalRepository.findByUserId(userId);
    }

}
