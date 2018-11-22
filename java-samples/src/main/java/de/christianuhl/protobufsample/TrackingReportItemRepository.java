package de.christianuhl.protobufsample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
@Repository
public interface TrackingReportItemRepository extends JpaRepository<TrackingReportItem, Long> {
    @Query("select e.timestamp as timestamp, e.category as category, count(e) as count " +
            "from TrackingReportItem e " +
            "where e.timestamp between :from and :to " +
            "group by e.timestamp, e.category")
    Page<TrackingReportItem.Counted> findAllByTimestampBetween(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            Pageable pageable);
}
