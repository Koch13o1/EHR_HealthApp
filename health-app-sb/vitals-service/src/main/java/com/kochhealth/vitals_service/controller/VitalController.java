package com.kochhealth.vitals_service.controller;

import com.kochhealth.vitals_service.dto.VitalRequest;
import com.kochhealth.vitals_service.model.Vital;
import com.kochhealth.vitals_service.service.VitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vitals")
@RequiredArgsConstructor
public class VitalController {
    private final VitalService vitalService;

    // Mocking user ID until JWT integration
    private Long getUserIdFromEmail(String email){
        return email.hashCode() & 0xfffffffL;
    }

    @PostMapping
    public ResponseEntity<Vital> logVital(@RequestBody VitalRequest request, Authentication auth){
        Long userId = getUserIdFromEmail(auth.getName());
        return ResponseEntity.ok(vitalService.logVital(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<Vital>> getVitals(Authentication auth){
        Long userId = getUserIdFromEmail(auth.getName());
        return ResponseEntity.ok(vitalService.getUserVitals(userId));
    }

}
