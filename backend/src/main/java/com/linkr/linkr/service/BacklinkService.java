package com.linkr.linkr.service;

import com.linkr.linkr.dto.BacklinkRequest;
import com.linkr.linkr.model.Backlink;
import com.linkr.linkr.model.Domain;
import com.linkr.linkr.model.User;
import com.linkr.linkr.repository.BacklinkRepository;
import com.linkr.linkr.repository.DomainRepository;
import com.linkr.linkr.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklinkService {

    private final BacklinkRepository backlinkRepository;
    private final DomainRepository domainRepository;
    private final UserRepository userRepository;

    public BacklinkService(
            BacklinkRepository backlinkRepository,
            DomainRepository domainRepository,
            UserRepository userRepository
    ) {
        this.backlinkRepository = backlinkRepository;
        this.domainRepository = domainRepository;
        this.userRepository = userRepository;
    }

    public Backlink addBacklink(BacklinkRequest request, String userEmail) {
        Domain domain = domainRepository.findById(request.domainId())
                .orElseThrow(() -> new RuntimeException("Domain not found"));

        if (!domain.getOwner().getEmail().equals(userEmail)) {
            throw new RuntimeException("You do not own this domain");
        }

        Backlink backlink = new Backlink();
        backlink.setSourceUrl(request.sourceUrl());
        backlink.setAnchorText(request.anchorText());
        backlink.setTargetDomain(domain);

        return backlinkRepository.save(backlink);
    }

    public List<Backlink> getBacklinksForUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Domain> userDomains = domainRepository.findAllByOwner(user);
        return backlinkRepository.findAllByTargetDomainIn(userDomains);
    }
}
