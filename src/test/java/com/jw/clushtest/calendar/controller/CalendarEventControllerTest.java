package com.jw.clushtest.calendar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.service.CalendarEventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalendarEventController.class)
@DisplayName("캘린더 일정 Controller 테스트")
public class CalendarEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CalendarEventService calendarEventService;

    @Test
    @DisplayName("모든 일정 조회 테스트")
    public void testGetAllEvents() throws Exception {

        CalendarEventDTO event1 = CalendarEventDTO.builder()
                .id(UUID.fromString("1efe625b-1b71-69c6-a237-611b6a789114"))
                .title("eventTitle1")
                .description("eventDescription1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        CalendarEventDTO event2 = CalendarEventDTO.builder()
                .id(UUID.fromString("1efe625b-1b71-69c6-a237-611b6a789114"))
                .title("eventTitle2")
                .description("eventDescription2")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(2))
                .build();


        List<CalendarEventDTO> events = Arrays.asList(event1,event2);

        Mockito.when(calendarEventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/api/calendar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(event1.getId().toString()))
                .andExpect(jsonPath("$[0].title").value("eventTitle1"))
                .andExpect(jsonPath("$[1].id").value(event2.getId().toString()))
                .andExpect(jsonPath("$[1].title").value("eventTitle2"))
                .andDo(print());
    }

    @Test
    @DisplayName("단일 일정 조회 테스트")
    public void testGetEventById() throws Exception {

        CalendarEventDTO event1 = CalendarEventDTO.builder()
                .id(UUID.fromString("1efe625b-1b71-69c6-a237-611b6a789114"))
                .title("eventTitle1")
                .description("eventDescription1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        Mockito.when(calendarEventService.getEventById(event1.getId())).thenReturn(event1);

        mockMvc.perform(get("/api/calendar/{id}", event1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(event1.getId().toString()))
                .andExpect(jsonPath("$.title").value("eventTitle1"))
                .andExpect(jsonPath("$.description").value("eventDescription1"))
                .andDo(print());
        Mockito.verify(calendarEventService, times(1)).getEventById(event1.getId());
    }

    @Test
    @DisplayName("일정 생성 테스트")
    public void testCreateEvent() throws Exception {

        CalendarEventDTO eventDTO = CalendarEventDTO.builder()
                .title("eventTitle1")
                .description("eventDescription1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        CalendarEventDTO responseDTO = CalendarEventDTO.builder()
                .id(UUID.randomUUID()) // 서비스에서 생성된 UUID 가정
                .title(eventDTO.getTitle())
                .description(eventDTO.getDescription())
                .startDate(eventDTO.getStartDate())
                .endDate(eventDTO.getEndDate())
                .build();

        Mockito.when(calendarEventService.createEvent(any(CalendarEventDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/calendar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId().toString()))
                .andExpect(jsonPath("$.title").value(responseDTO.getTitle()))
                .andExpect(jsonPath("$.description").value(responseDTO.getDescription()))
                .andDo(print());
    }

    @Test
    @DisplayName("일정 수정 테스트")
    public void testUpdateEvent() throws Exception {
        //일정 생성
        CalendarEventDTO createDto = CalendarEventDTO.builder()
                .id(UUID.randomUUID())
                .title("Original Event Title")
                .description("Original Event Description")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        Mockito.when(calendarEventService.createEvent(any(CalendarEventDTO.class))).thenReturn(createDto);

        mockMvc.perform(post("/api/calendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createDto.getId().toString()))
                .andExpect(jsonPath("$.title").value(createDto.getTitle()))
                .andExpect(jsonPath("$.description").value(createDto.getDescription()))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andDo(print());

        //일정 수정
        CalendarEventDTO updateDto = CalendarEventDTO.builder()
                .id(createDto.getId())
                .title("Update Event Title")
                .description("Update Event Description")
                .startDate(createDto.getStartDate().plusDays(1))
                .endDate(createDto.getEndDate().plusDays(1))
                .build();

        Mockito.when(calendarEventService.updateEvent(eq(createDto.getId()), any(CalendarEventDTO.class))).thenReturn(updateDto);

        mockMvc.perform(put("/api/calendar/{id}" , createDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updateDto.getId().toString()))
                .andExpect(jsonPath("$.title").value(updateDto.getTitle()))
                .andExpect(jsonPath("$.description").value(updateDto.getDescription()))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("일정 삭제")
    public void testDeleteEvent() throws Exception {
        //일정 생성
        CalendarEventDTO createDto = CalendarEventDTO.builder()
                .id(UUID.randomUUID())
                .title("Event Title")
                .description("Event Description")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        Mockito.doNothing().when(calendarEventService).deleteEvent(createDto.getId());

        mockMvc.perform(delete("/api/calendar/{id}" , createDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());

        Mockito.verify(calendarEventService, times(1)).deleteEvent(createDto.getId());
    }
}