package com.linkr.linkr.mapper;

import com.linkr.linkr.dto.BacklinkResponse;
import com.linkr.linkr.model.Backlink;

public class BacklinkMapper {
    public static BacklinkResponse toDto(Backlink b) {
        return new BacklinkResponse(
                b.getId(),
                b.getSourceUrl(),
                b.getAnchorText(),
                b.getDiscoveredAt(),
                b.getTargetDomain().getId(),
                b.getTargetDomain().getUrl()
        );
    }
}
