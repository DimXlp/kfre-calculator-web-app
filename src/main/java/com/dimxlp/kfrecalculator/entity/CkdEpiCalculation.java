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
@Table(name = "ckd_epi_calculation")
public class CkdEpiCalculation extends BaseEntity {

    private double creatinine;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender sex;

    private double result;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
