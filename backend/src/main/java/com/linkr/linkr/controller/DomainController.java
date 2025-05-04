package com.linkr.linkr.controller;

import com.linkr.linkr.dto.DomainRequest;
import com.linkr.linkr.dto.DomainResponse;
import com.linkr.linkr.model.Domain;
import com.linkr.linkr.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping
    public ResponseEntity<DomainResponse> addDomain(@RequestBody DomainRequest request) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Domain domain = domainService.addDomain(request.url(), userEmail);
        DomainResponse response = new DomainResponse(
                domain.getId(),
                domain.getUrl(),
                domain.getCreatedAt());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DomainResponse>> listDomains() {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<DomainResponse> response = domainService.getDomainsForUser(userEmail).stream()
                .map(domain -> new DomainResponse(
                        domain.getId(),
                        domain.getUrl(),
                        domain.getCreatedAt()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        domainService.deleteDomain(id, userEmail);
        return ResponseEntity.noContent().build();
    }
}
