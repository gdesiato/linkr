package com.linkr.linkr.repository;

import com.linkr.linkr.model.Backlink;
import com.linkr.linkr.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BacklinkRepository extends JpaRepository<Backlink, Long> {

    List<Backlink> findAllByTargetDomain(Domain domain);
    List<Backlink> findAllByTargetDomainIn(List<Domain> domains); // For all domains owned by a user
}
