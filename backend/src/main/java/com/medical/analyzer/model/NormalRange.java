package com.medical.analyzer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "normal_ranges")
public class NormalRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String testName;

    private Double minValue;

    private Double maxValue;

    private String unit;

    // Optional: for age groups or gender specific ranges
    // private String gender; 
    // private Integer minAge;
    // private Integer maxAge;
}
