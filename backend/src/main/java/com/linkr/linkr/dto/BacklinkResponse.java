package com.linkr.linkr.dto;

import com.linkr.linkr.model.Backlink;

import java.time.LocalDateTime;

public record BacklinkResponse(
        Long id,
        String sourceUrl,
        String anchorText,
        LocalDateTime discoveredAt,
        Long domainId,
        String domainUrl
){}
