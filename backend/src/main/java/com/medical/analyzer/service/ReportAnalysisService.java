package com.medical.analyzer.service;

import com.medical.analyzer.model.MedicalReport;
import com.medical.analyzer.model.NormalRange;
import com.medical.analyzer.model.TestResult;
import com.medical.analyzer.repository.NormalRangeRepository;
import com.medical.analyzer.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReportAnalysisService {

    private final OcrService ocrService;
    private final ReportRepository reportRepository;
    private final NormalRangeRepository normalRangeRepository;
    private final ExplanationService explanationService;

    public MedicalReport processReport(MultipartFile file, String patientName) {
        // 1. Extract Text
        String extractedText = ocrService.extractText(file);
        
        System.out.println("=== EXTRACTED TEXT START ===");
        System.out.println(extractedText);
        System.out.println("=== EXTRACTED TEXT END ===");

        // 2. Create Report Entity
        MedicalReport report = new MedicalReport();
        report.setPatientName(patientName);
        report.setReportDate(LocalDateTime.now());
        report.setRawText(extractedText);
        report.setStatus("PROCESSED");
        
        // 3. Dynamically Extract ALL Tests
        List<TestResult> results = extractAllTests(extractedText, report);
        report.setTestResults(results);
        
        System.out.println("=== TOTAL TESTS FOUND: " + results.size() + " ===");

        // 4. Save
        return reportRepository.save(report);
    }
    
    public MedicalReport getReport(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    private List<TestResult> extractAllTests(String text, MedicalReport report) {
        List<TestResult> results = new ArrayList<>();
        Set<String> foundTests = new HashSet<>();
        
        // Normalize the text - replace multiple spaces, handle OCR quirks
        String normalizedText = text.toUpperCase()
            .replaceAll("[\\r\\n]+", " ")
            .replaceAll("\\s+", " ");
        
        System.out.println("=== NORMALIZED TEXT ===");
        System.out.println(normalizedText);
        
        // Define test extraction patterns with flexible matching
        // Format: TestName patterns -> extract number after it
        Map<String, TestConfig> testConfigs = new LinkedHashMap<>();
        
        // Hematology
        testConfigs.put("Hemoglobin", new TestConfig(
            Arrays.asList("HAEMOGLOBIN", "HEMOGLOBIN", "HGB", "HB "),
            "g/dL", 5, 20, 12.0, 17.0, true)); // needsDecimal for values like 130 -> 13.0
        
        testConfigs.put("RBC Count", new TestConfig(
            Arrays.asList("RBC COUNT", "RBC  COUNT", "R.B.C"),
            "million/cumm", 2, 10, 4.0, 5.5, false));
        
        testConfigs.put("WBC Total Count", new TestConfig(
            Arrays.asList("WBC TOTAL COUNT", "WBC COUNT", "TOTAL COUNT TC", "W.B.C", "WBC  TOTAL"),
            "cells/cumm", 1000, 50000, 4000, 11000, false));
        
        testConfigs.put("Platelet Count", new TestConfig(
            Arrays.asList("PLATELET COUNT", "PLATELETS", "PLT COUNT"),
            "lakhs/cumm", 0.5, 10, 1.5, 4.0, false));
        
        testConfigs.put("ESR", new TestConfig(
            Arrays.asList("ESR ", "E.S.R", "ERYTHROCYTE SEDIMENTATION"),
            "mm/hr", 0, 150, 0, 20, false));
        
        testConfigs.put("PCV", new TestConfig(
            Arrays.asList("PCV", "HEMATOCRIT", "HCT ", "PACKED CELL"),
            "%", 20, 60, 37, 47, false));
        
        testConfigs.put("MCV", new TestConfig(
            Arrays.asList("MCV "),
            "fL", 50, 120, 80, 100, false));
        
        testConfigs.put("MCH", new TestConfig(
            Arrays.asList("MCH "),
            "pg", 15, 40, 27, 32, false));
        
        testConfigs.put("MCHC", new TestConfig(
            Arrays.asList("MCHC"),
            "g/dL", 25, 40, 32, 36, false));
        
        // WBC Differential
        testConfigs.put("Neutrophils", new TestConfig(
            Arrays.asList("NEUTROPHIL", "DC-NEUTROPHIL", "DC NEUTROPHIL"),
            "%", 0, 100, 50, 70, false));
        
        testConfigs.put("Lymphocytes", new TestConfig(
            Arrays.asList("LYMPHOCYTE", "DC-LYMPHOCYTE", "DC LYMPHOCYTE"),
            "%", 0, 100, 20, 40, false));
        
        testConfigs.put("Eosinophils", new TestConfig(
            Arrays.asList("EOSINOPHIL", "DC-EOSINOPHIL", "DC EOSINOPHIL"),
            "%", 0, 15, 1, 4, false));
        
        testConfigs.put("Monocytes", new TestConfig(
            Arrays.asList("MONOCYTE", "DC-MONOCYTE", "DC MONOCYTE"),
            "%", 0, 20, 2, 8, false));
        
        testConfigs.put("Basophils", new TestConfig(
            Arrays.asList("BASOPHIL", "DC-BASOPHIL", "DC BASOPHIL"),
            "%", 0, 5, 0, 1, false));
        
        // Extract tests
        for (Map.Entry<String, TestConfig> entry : testConfigs.entrySet()) {
            String testName = entry.getKey();
            TestConfig config = entry.getValue();
            
            if (foundTests.contains(testName.toUpperCase())) {
                continue;
            }
            
            Double value = findValueForTest(normalizedText, config.patterns, config.minValid, config.maxValid, config.needsDecimalConversion);
            
            if (value != null) {
                TestResult result = new TestResult();
                result.setTestName(testName);
                result.setResultValue(String.format("%.2f", value));
                result.setNumericValue(value);
                result.setUnit(config.unit);
                result.setMedicalReport(report);
                
                if (value < config.refMin) {
                    result.setStatus("LOW");
                } else if (value > config.refMax) {
                    result.setStatus("HIGH");
                } else {
                    result.setStatus("NORMAL");
                }
                
                result.setExplanation(explanationService.generateExplanation(testName, result.getStatus(), result.getResultValue()));
                
                results.add(result);
                foundTests.add(testName.toUpperCase());
                System.out.println("✓ Found: " + testName + " = " + value + " " + config.unit + " [" + result.getStatus() + "]");
            }
        }
        
        return results;
    }
    
    private Double findValueForTest(String text, List<String> patterns, double minValid, double maxValid, boolean needsDecimalConversion) {
        for (String pattern : patterns) {
            int idx = text.indexOf(pattern);
            if (idx >= 0) {
                // Look for a number after the pattern (within next 50 characters)
                String afterPattern = text.substring(idx + pattern.length(), Math.min(idx + pattern.length() + 50, text.length()));
                
                // Find the first number
                Pattern numPattern = Pattern.compile("([0-9]+\\.?[0-9]*)");
                Matcher matcher = numPattern.matcher(afterPattern);
                
                while (matcher.find()) {
                    try {
                        String numStr = matcher.group(1);
                        if (numStr == null || numStr.trim().isEmpty()) continue;
                        
                        double value = Double.parseDouble(numStr.trim());
                        
                        // Handle cases like 130 for hemoglobin (should be 13.0)
                        if (needsDecimalConversion && value > maxValid && value < maxValid * 100) {
                            value = value / 10.0;
                        }
                        
                        // Validate range
                        if (value >= minValid && value <= maxValid) {
                            System.out.println("  Pattern '" + pattern + "' matched value: " + value);
                            return value;
                        }
                    } catch (NumberFormatException e) {
                        // Try next match
                    }
                }
            }
        }
        return null;
    }
    
    // Helper class for test configuration
    private static class TestConfig {
        List<String> patterns;
        String unit;
        double minValid;
        double maxValid;
        double refMin;
        double refMax;
        boolean needsDecimalConversion;
        
        TestConfig(List<String> patterns, String unit, double minValid, double maxValid, double refMin, double refMax, boolean needsDecimalConversion) {
            this.patterns = patterns;
            this.unit = unit;
            this.minValid = minValid;
            this.maxValid = maxValid;
            this.refMin = refMin;
            this.refMax = refMax;
            this.needsDecimalConversion = needsDecimalConversion;
        }
    }
}
