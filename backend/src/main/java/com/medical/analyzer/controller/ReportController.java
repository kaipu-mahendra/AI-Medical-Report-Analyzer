package com.medical.analyzer.controller;

import com.medical.analyzer.model.MedicalReport;
import com.medical.analyzer.service.ReportAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportAnalysisService reportAnalysisService;

    @PostMapping("/upload")
    public ResponseEntity<MedicalReport> uploadReport(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "patientName", defaultValue = "Unknown") String patientName) {
        
        MedicalReport report = reportAnalysisService.processReport(file, patientName);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{id}/analysis")
    public ResponseEntity<MedicalReport> getAnalysis(@PathVariable Long id) {
        return ResponseEntity.ok(reportAnalysisService.getReport(id));
    }
}
