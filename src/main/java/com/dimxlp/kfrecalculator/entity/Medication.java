package com.dimxlp.kfrecalculator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medication")
public class Medication extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String dosage;

    // The user who added this medication to the system (not who assigned it to a patient)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_user_id")
    private User addedBy;

    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicationAssignment> medicationAssignments;
}
