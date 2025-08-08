package com.dimxlp.kfrecalculator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass // Specifies this is a base class and its fields should be mapped to the columns of its subclasses.
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp // Automatically set the creation timestamp when an entity is first saved.
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp // Automatically update the timestamp whenever an entity is updated.
    @Column(nullable = false)
    private Instant updatedAt;
}