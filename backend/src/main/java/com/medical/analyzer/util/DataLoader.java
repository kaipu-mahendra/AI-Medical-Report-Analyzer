package com.medical.analyzer.util;

import com.medical.analyzer.model.NormalRange;
import com.medical.analyzer.repository.NormalRangeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final NormalRangeRepository repository;

    public DataLoader(NormalRangeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                new NormalRange(null, "Hemoglobin", 12.0, 16.0, "g/dL"),
                new NormalRange(null, "WBC", 4000.0, 11000.0, "/mcL"),
                new NormalRange(null, "RBC", 4.0, 6.0, "M/mcL"),
                new NormalRange(null, "Platelets", 150000.0, 450000.0, "/mcL"),
                new NormalRange(null, "Cholesterol", 0.0, 200.0, "mg/dL"),
                new NormalRange(null, "Glucose", 70.0, 100.0, "mg/dL")
            ));
            System.out.println("Default normal ranges loaded.");
        }
    }
}
