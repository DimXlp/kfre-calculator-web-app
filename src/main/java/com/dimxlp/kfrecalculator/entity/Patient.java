package com.dimxlp.kfrecalculator.entity;

import com.dimxlp.kfrecalculator.enumeration.Gender;
import com.dimxlp.kfrecalculator.enumeration.Risk;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private boolean active = true;

    @Column(columnDefinition = "TEXT")
    private String generalHistoryNote;

    private double lastRisk2Yr;
    private double lastRisk5Yr;

    @Enumerated(EnumType.STRING)
    private Risk lastRisk;

    private Instant lastAssessmentDate;
    private double lastEgfrResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Disease> history;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<KfreCalculation> kfreCalculations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CkdEpiCalculation> ckdEpiCalculations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Report> reports;
}
