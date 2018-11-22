package de.christianuhl.protobufsample;

import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
class TrackingEventMapper {

    TrackingReportItemDto map(TrackingReportItem.Counted entity) {
        return TrackingReportItemDto.builder()
                .timestamp(entity.getTimestamp())
                .category(entity.getCategory())
                .count(entity.getCount())
                .build();
    }

    TrackingReportItem map(TrackingEventDto dto) {
        return TrackingReportItem.builder()
                .eventId(dto.getEventId())
                .timestamp(dto.getTimestamp().truncatedTo(ChronoUnit.DAYS).toLocalDate())
                .category(dto.getCategory())
                .build();
    }
}
