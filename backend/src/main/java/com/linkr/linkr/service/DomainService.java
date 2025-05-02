package com.linkr.linkr.service;

import com.linkr.linkr.model.Domain;
import com.linkr.linkr.model.User;
import com.linkr.linkr.repository.DomainRepository;
import com.linkr.linkr.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    private final DomainRepository domainRepository;
    private final UserRepository userRepository;

    public DomainService(DomainRepository domainRepository, UserRepository userRepository) {
        this.domainRepository = domainRepository;
        this.userRepository = userRepository;
    }

    public Domain addDomain(String url, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Domain domain = new Domain();
        domain.setUrl(url);
        domain.setOwner(user);

        return domainRepository.save(domain);
    }

    public List<Domain> getDomainsForUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return domainRepository.findAllByOwner(user);
    }
}
