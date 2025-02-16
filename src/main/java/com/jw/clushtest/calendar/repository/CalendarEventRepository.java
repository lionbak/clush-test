package com.jw.clushtest.calendar.repository;

import com.jw.clushtest.calendar.entity.CalendarEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CalendarEventRepository extends JpaRepository<CalendarEventEntity, UUID> {
    List<CalendarEventEntity> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
