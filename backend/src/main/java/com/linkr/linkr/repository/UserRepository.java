package com.linkr.linkr.repository;

import com.linkr.linkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Optional: check if email already exists (for signup validation)
    boolean existsByEmail(String email);
}
