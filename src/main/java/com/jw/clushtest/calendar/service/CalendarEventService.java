package com.jw.clushtest.calendar.service;

import com.jw.clushtest.calendar.config.CalendarEventMapper;
import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;
    private final CalendarEventMapper mapper = CalendarEventMapper.INSTANCE;

    //일정 전체 조회
    @Transactional
    public List<CalendarEventDTO> getAllEvents() {
        return calendarEventRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

}
