package com.medical.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;

    @Column(name = "result_value")
    private String resultValue; // Original string value

    private Double numericValue; // Parsed numeric value for comparison

    private String unit;

    private String status; // NORMAL, HIGH, LOW

    @Column(length = 2000) // Allow longer explanations with food recommendations
    private String explanation; // Generated explanation

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    @JsonIgnore
    private MedicalReport medicalReport;
}
