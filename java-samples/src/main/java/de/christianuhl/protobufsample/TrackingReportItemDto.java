package de.christianuhl.protobufsample;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
class TrackingReportItemDto {
    private LocalDate timestamp;
    private String category;
    private long count;
}
