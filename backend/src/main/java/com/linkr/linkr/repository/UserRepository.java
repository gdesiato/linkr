package com.linkr.linkr.repository;

import com.linkr.linkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // check if email already exists (for signup validation)
    boolean existsByEmail(String email);
}
