package com.dimxlp.kfrecalculator.repository;

import com.dimxlp.kfrecalculator.entity.Patient;
import com.dimxlp.kfrecalculator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    // Finds all patients associated with a specific user.
    List<Patient> findByUser(User user);
}
