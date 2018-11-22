package de.christianuhl.protobufsample;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
class TrackingEventDto {
    private String eventId;
    private ZonedDateTime timestamp;
    private String category;
}
