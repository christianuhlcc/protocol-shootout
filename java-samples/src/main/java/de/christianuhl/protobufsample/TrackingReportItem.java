package de.christianuhl.protobufsample;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TrackingReportItem extends AbstractPersistable<Long> {
    @Column
    private String eventId;
    @Column
    private LocalDate timestamp;
    @Column
    private String category;

    public interface Counted {
        LocalDate getTimestamp();

        String getCategory();

        Long getCount();
    }
}
