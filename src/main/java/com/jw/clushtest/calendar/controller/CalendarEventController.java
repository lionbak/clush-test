package com.jw.clushtest.calendar.controller;

import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.service.CalendarEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarEventController {

    private final CalendarEventService eventService;

    //전체 일정 조회
    @GetMapping("/all")
    public ResponseEntity<List<CalendarEventDTO>> getAllEvent() {
        List<CalendarEventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}
