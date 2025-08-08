package com.dimxlp.kfrecalculator.repository;

import com.dimxlp.kfrecalculator.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, UUID> {
}
