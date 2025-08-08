package com.dimxlp.kfrecalculator.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "settings")
public class Settings extends BaseEntity {

    private boolean autosave = true;
    private boolean notifications = true;
    private String exportFormat = "PDF";
    private boolean darkMode = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
