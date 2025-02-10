package com.jw.clushtest.calendar.controller;

import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.service.CalendarEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarEventController {

    private final CalendarEventService eventService;

    //전체 일정 조회
    @GetMapping
    public ResponseEntity<List<CalendarEventDTO>> getAllEvent() {
        List<CalendarEventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    //단일 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalendarEventDTO> getEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<CalendarEventDTO> createEvent(@RequestBody CalendarEventDTO dto) {
        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    //일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<CalendarEventDTO> updateEvent(@PathVariable UUID id, @RequestBody CalendarEventDTO dto) {
        return ResponseEntity.ok(eventService.updateEvent(id,dto));
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
