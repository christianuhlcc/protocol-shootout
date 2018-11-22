package de.christianuhl.protobufsample;

import de.christianuhl.proto.TrackingEventOuterClass;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class TrackingEventDtoMapper {

    public TrackingEventDto map (TrackingEventOuterClass.TrackingEvent event) {
        return TrackingEventDto.builder()
                .eventId(event.getEventId())
                .category(event.getCategory())
                .timestamp(ZonedDateTime.parse(event.getDate()))
                .build();
    }

}
