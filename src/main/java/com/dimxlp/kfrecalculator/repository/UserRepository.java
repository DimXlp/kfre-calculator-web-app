package com.dimxlp.kfrecalculator.repository;

import com.dimxlp.kfrecalculator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // This method is crucial for Spring Security to find a user by their login identifier (email).
    Optional<User> findByEmail(String email);

    Optional<User> findByAuthProviderAndAuthProviderId(String provider, String providerId);

    Optional<User> findByEmailIgnoreCase(String email);

}
