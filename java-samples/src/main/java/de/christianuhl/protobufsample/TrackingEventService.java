package de.christianuhl.protobufsample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TrackingEventService {
    void publish(TrackingEventDto dto);

    Page<TrackingReportItemDto> query(LocalDate from, LocalDate to, Pageable pageable);
}
