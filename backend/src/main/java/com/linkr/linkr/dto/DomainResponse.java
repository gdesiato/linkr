package com.linkr.linkr.dto;

import java.time.LocalDateTime;

public record DomainResponse(
        Long id,
        String url,
        LocalDateTime createdAt
) {}
