package com.medical.analyzer.repository;

import com.medical.analyzer.model.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<MedicalReport, Long> {
    // List<MedicalReport> findByUserId(Long userId); // If user system is added
}
