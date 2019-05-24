package de.christianuhl.protobufsample;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static de.christianuhl.proto.TrackingEventOuterClass.TrackingEvent;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
class TrackingEventController {
    private final TrackingEventService service;

    private final TrackingEventDtoMapper mapper;

    @PostMapping
    public void publish(@RequestBody byte[] rawEvent) throws InvalidProtocolBufferException {
        val event = TrackingEvent.parseFrom(rawEvent);
        service.publish(mapper.map(event));
    }


    @GetMapping
    public Page<TrackingReportItemDto> query(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @PageableDefault(sort = {"timestamp", "category"}) Pageable pageable) {
        return service.query(from, to, pageable);
    }

    @GetMapping("/demo")
    public byte[] demo() {
        val event =  TrackingEvent
                .newBuilder()
                .setCategory("category2")
                .setEventId("3")
                .setDate( LocalDateTime.now().toString())
                .build();

        return event.toByteArray();
    }
}
