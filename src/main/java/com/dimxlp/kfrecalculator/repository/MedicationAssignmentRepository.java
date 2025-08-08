package com.dimxlp.kfrecalculator.repository;

import com.dimxlp.kfrecalculator.entity.MedicationAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicationAssignmentRepository extends JpaRepository<MedicationAssignment, UUID> {
}
