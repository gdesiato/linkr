package com.linkr.linkr.dto;

public record BacklinkRequest(
        String sourceUrl,
        String anchorText,
        Long domainId
) {}
