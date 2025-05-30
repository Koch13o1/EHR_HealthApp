package com.kochhealth.vitals_service.repository;

import com.kochhealth.vitals_service.model.Vital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalRepository extends JpaRepository<Vital, Long> {
    List<Vital> findByUserId(Long userId);
}
