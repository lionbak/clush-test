package com.jw.clushtest.calendar.repository;

import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.entity.CalendarEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CalendarEventRepository extends JpaRepository<CalendarEventEntity, UUID> {
}
