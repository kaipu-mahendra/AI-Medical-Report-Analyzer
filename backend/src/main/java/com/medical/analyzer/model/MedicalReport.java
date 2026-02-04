package com.medical.analyzer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medical_reports")
public class MedicalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private LocalDateTime reportDate;

    @Lob
    private String rawText; // Extracted text from OCR

    private String status; // PROCESSED, FAILED

    @OneToMany(mappedBy = "medicalReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestResult> testResults;
}
