package com.linkr.linkr.repository;

import com.linkr.linkr.model.Domain;
import com.linkr.linkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    List<Domain> findAllByOwner(User owner);
}
