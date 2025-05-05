package com.linkr.linkr.controller;

import com.linkr.linkr.dto.BacklinkRequest;
import com.linkr.linkr.dto.BacklinkResponse;
import com.linkr.linkr.mapper.BacklinkMapper;
import com.linkr.linkr.model.Backlink;
import com.linkr.linkr.service.BacklinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlinks")
public class BacklinkController {

    private final BacklinkService backlinkService;

    public BacklinkController(BacklinkService backlinkService) {
        this.backlinkService = backlinkService;
    }

    @PostMapping
    public ResponseEntity<BacklinkResponse> addBacklink(@RequestBody BacklinkRequest request) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Backlink b = backlinkService.addBacklink(request, userEmail);
        return ResponseEntity.ok(BacklinkMapper.toDto(b));
    }

    @GetMapping
    public ResponseEntity<List<BacklinkResponse>> listBacklinks() {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BacklinkResponse> response = backlinkService.getBacklinksForUser(userEmail).stream()
                .map(BacklinkMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }
}
