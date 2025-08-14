package com.dimxlp.kfrecalculator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.dimxlp.kfrecalculator.enumeration.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(nullable = false)
    private String tenantId;

    private String profileImageUrl;

    private String clinic;

    private Instant lastLogin;

    @Column(name = "android_last_login")
    private Instant androidLastLogin;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "android_created_at")
    private Instant androidCreatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Settings settings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Patient> patients;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Report> reports;

    @Column(name = "auth_provider", nullable = false)
    private String authProvider;

    @Column(name = "auth_provider_id", nullable = false, unique = true)
    private String authProviderId;


    // -- UserDetails Methods --
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}