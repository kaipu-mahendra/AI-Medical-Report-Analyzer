package com.medical.analyzer.repository;

import com.medical.analyzer.model.NormalRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NormalRangeRepository extends JpaRepository<NormalRange, Long> {
    Optional<NormalRange> findByTestName(String testName);
    // Optional<NormalRange> findByTestNameAndGender(String testName, String gender);
}
