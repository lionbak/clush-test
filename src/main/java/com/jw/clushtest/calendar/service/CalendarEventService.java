package com.jw.clushtest.calendar.service;

import com.jw.clushtest.calendar.config.CalendarEventMapper;
import com.jw.clushtest.calendar.config.UUIDConfig;
import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
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

    //특정 일정 조회
    @Transactional
    public CalendarEventDTO getEventById(UUID id) {
        return calendarEventRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
    }

    //일정 생성
    @Transactional
    public CalendarEventDTO createEvent(CalendarEventDTO eventDTO) {
        if(eventDTO.getId() == null) {
            eventDTO.setId(UUIDConfig.generateUUID());
        }
        return mapper.toDTO(calendarEventRepository.save(mapper.toEntity(eventDTO)));
    }

    //일정 수정
    @Transactional
    public CalendarEventDTO updateEvent(UUID id, CalendarEventDTO calendarEventDTO) {
        CalendarEventDTO eventDTO = calendarEventRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        eventDTO.setTitle(calendarEventDTO.getTitle());
        eventDTO.setDescription(calendarEventDTO.getDescription());
        eventDTO.setStartDate(calendarEventDTO.getStartDate());
        eventDTO.setEndDate(calendarEventDTO.getEndDate());
        mapper.toDTO(calendarEventRepository.save(mapper.toEntity(eventDTO)));

        return eventDTO;
    }

    //일정 삭제
    @Transactional
    public void deleteEvent(UUID id) {
        calendarEventRepository.deleteById(id);
    }
}
