package de.christianuhl.protobufsample;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
class TrackingEventServiceImpl implements TrackingEventService {
    private final TrackingReportItemRepository repository;
    private final TrackingEventMapper mapper;

    @Override
    public void publish(TrackingEventDto dto) {
        repository.save(mapper.map(dto));
    }

    @Override
    public Page<TrackingReportItemDto> query(LocalDate from, LocalDate to, Pageable pageable) {
        return repository.findAllByTimestampBetween(from, to, pageable)
                .map(mapper::map);
    }
}
