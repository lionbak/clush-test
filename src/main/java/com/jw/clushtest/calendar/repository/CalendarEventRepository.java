package com.jw.clushtest.calendar.repository;

import com.jw.clushtest.calendar.entity.CalendarEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarEventRepository extends JpaRepository<CalendarEventEntity, String> {
}
