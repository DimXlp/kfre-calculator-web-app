package com.dimxlp.kfrecalculator.entity;

import com.dimxlp.kfrecalculator.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kfre_calculation")
public class KfreCalculation extends BaseEntity {

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender sex;

    @Column(nullable = false)
    private double egfr;

    @Column(nullable = false)
    private double acr; // Albumin-to-Creatinine Ratio

    private double risk2Yr;
    private double risk5Yr;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // We can also track which user performed the calculation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
