package com.jw.clushtest.calendar.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalendarEventController.class)
@DisplayName("캘린더 일정 Controller 테스트")
public class CalendarEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalendarEventService calendarEventService;

    @Test
    @DisplayName("모든 일정 조회 테스트")
    public void testGetAllEvents() throws Exception {
        CalendarEventDTO event1 = new CalendarEventDTO(
                "11d4-e29b-550e8400-a716-446655440000",
                "eventTitle1",
                "eventDescription1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );
        CalendarEventDTO event2 = new CalendarEventDTO(
                "11d4-e29b-550e8401-a716-446655440000",
                "eventTitle2",
                "eventDescription2",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );

        List<CalendarEventDTO> events = Arrays.asList(event1,event2);

        Mockito.when(calendarEventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/api/calendar/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("11d4-e29b-550e8400-a716-446655440000"))
                .andExpect(jsonPath("$[0].title").value("eventTitle1"))
                .andExpect(jsonPath("$[1].id").value("11d4-e29b-550e8401-a716-446655440000"))
                .andExpect(jsonPath("$[1].title").value("eventTitle2"))
                .andDo(print());
    }
}