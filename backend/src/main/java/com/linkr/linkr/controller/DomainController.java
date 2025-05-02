package com.linkr.linkr.controller;

import com.linkr.linkr.dto.DomainRequest;
import com.linkr.linkr.model.Domain;
import com.linkr.linkr.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping
    public ResponseEntity<Domain> addDomain(@RequestBody DomainRequest request) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Domain domain = domainService.addDomain(request.url(), userEmail);
        return ResponseEntity.ok(domain);
    }

    @GetMapping
    public ResponseEntity<List<Domain>> listDomains() {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(domainService.getDomainsForUser(userEmail));
    }
}
